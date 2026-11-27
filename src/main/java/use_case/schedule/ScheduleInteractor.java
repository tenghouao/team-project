package use_case.schedule;

import entity.Activity;
import entity.City;
import entity.DailySchedule;
import entity.RecurringRoutine;
import entity.ScheduledActivity;
import entity.UnscheduledActivity;
import entity.WeatherData;
import use_case.weather.WeatherLocalDataAccessInterface;
import use_case.weather.WeatherScoreCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for Use Case 8: Combines recurring routines and date-specific one-time events,
 * evaluates outdoor eligibility against cached weather data, locks fixed activities
 * (placing overlapping fixed activities into the unscheduled list), and allocates
 * flexible activities into conflict-free hourly slots (8:00 to 22:00).
 */
public class ScheduleInteractor implements ScheduleInputBoundary {

    /** Earliest hour of the day considered for flexible activity placement (8:00). */
    public static final int DAY_START_HOUR = 8;

    /** Latest end hour of the day considered for flexible activity placement (22:00). */
    public static final int DAY_END_HOUR = 22;

    private static final int HOURS_IN_DAY = 24;

    private final EventDataAccessInterface eventDAO;
    private final WeatherLocalDataAccessInterface weatherLocalDAO;
    private final ScheduleOutputBoundary schedulePresenter;
    private final WeatherScoreCalculator scoreCalculator;

    /**
     * Constructs a ScheduleInteractor with its required dependencies.
     *
     * @param eventDAO          the data access interface for recurring and one-time events
     * @param weatherLocalDAO   the local weather cache data access interface
     * @param schedulePresenter the output boundary presenter
     * @param scoreCalculator   the calculator used to score outdoor suitability
     */
    public ScheduleInteractor(EventDataAccessInterface eventDAO,
                              WeatherLocalDataAccessInterface weatherLocalDAO,
                              ScheduleOutputBoundary schedulePresenter,
                              WeatherScoreCalculator scoreCalculator) {
        this.eventDAO = eventDAO;
        this.weatherLocalDAO = weatherLocalDAO;
        this.schedulePresenter = schedulePresenter;
        this.scoreCalculator = scoreCalculator;
    }

    /**
     * Executes Use Case 8 to build a conflict-free, weather-aware daily schedule.
     *
     * @param inputData the input data containing city, date, and day of the week
     */
    @Override
    public void execute(ScheduleInputData inputData) {
        if (inputData == null) {
            schedulePresenter.prepareFailView("Schedule request cannot be null.");
            return;
        }

        City city = inputData.getCity();
        String date = inputData.getDate();
        String dayOfWeek = inputData.getDayOfWeek();

        if (city == null || date.isEmpty() || dayOfWeek.isEmpty()) {
            schedulePresenter.prepareFailView(
                    "City, date, and day of the week are required to generate a schedule.");
            return;
        }

        // Step 1: Collect both recurring routine activities and date-specific one-time events
        List<Activity> allActivities = new ArrayList<>();

        RecurringRoutine routine = eventDAO.getRoutineByDay(dayOfWeek);
        if (routine != null) {
            allActivities.addAll(routine.getActivities());
        }

        List<Activity> oneTimeEvents = eventDAO.getEventsByDate(date);
        if (oneTimeEvents != null) {
            allActivities.addAll(oneTimeEvents);
        }

        if (allActivities.isEmpty()) {
            schedulePresenter.prepareFailView(
                    "No recurring routine or one-time events found for "
                            + date + " (" + dayOfWeek + ").");
            return;
        }

        // Step 2: Load cached weather; fall back safely to indoor-only mode if unavailable
        WeatherData weatherData = weatherLocalDAO.getSavedWeather(city, date);
        boolean weatherAvailable = false;
        int score = 0;
        boolean outdoorAllowed = false;

        if (weatherData != null) {
            weatherAvailable = true;
            score = scoreCalculator.calculateScore(weatherData);
            outdoorAllowed = scoreCalculator.isSuitableForOutdoor(score);
        }

        boolean[] occupiedHours = new boolean[HOURS_IN_DAY];
        List<ScheduledActivity> scheduledList = new ArrayList<>();
        List<UnscheduledActivity> unscheduledList = new ArrayList<>();

        // Step 3 (Pass 1): Lock fixed activities; move overlapping ones to unscheduledList
        for (Activity activity : allActivities) {
            if (activity != null && activity.isFixed()) {
                int start = activity.getFixedStartHour();
                int end = start + activity.getDurationHours();

                if (start < 0 || end > HOURS_IN_DAY || start >= end) {
                    schedulePresenter.prepareFailView(
                            "Invalid fixed time range for activity: " + activity.getName());
                    return;
                }

                int conflictHour = findConflictHour(occupiedHours, start, end);
                if (conflictHour != -1) {
                    String reason = "Time conflict with another fixed activity at "
                            + conflictHour + ":00.";
                    unscheduledList.add(new UnscheduledActivity(activity, reason));
                } else {
                    for (int h = start; h < end; h++) {
                        occupiedHours[h] = true;
                    }
                    scheduledList.add(new ScheduledActivity(activity, start, end));
                }
            }
        }

        // Step 4 (Pass 2): Filter and allocate flexible activities into free contiguous slots
        for (Activity activity : allActivities) {
            if (activity != null && !activity.isFixed()) {
                if (isOutdoorActivity(activity) && !outdoorAllowed) {
                    String reason;
                    if (weatherAvailable) {
                        reason = "Outdoor weather score (" + score
                                + "/100) is below the safe threshold ("
                                + WeatherScoreCalculator.OUTDOOR_SAFE_THRESHOLD + ").";
                    } else {
                        reason = "Weather data unavailable; "
                                + "outdoor activity skipped in safe mode.";
                    }
                    unscheduledList.add(new UnscheduledActivity(activity, reason));
                } else {
                    int duration = activity.getDurationHours();
                    int foundStartHour = findAvailableSlot(occupiedHours, duration);

                    if (foundStartHour != -1) {
                        int endHour = foundStartHour + duration;
                        for (int h = foundStartHour; h < endHour; h++) {
                            occupiedHours[h] = true;
                        }
                        scheduledList.add(
                                new ScheduledActivity(activity, foundStartHour, endHour));
                    } else {
                        String reason = "No contiguous " + duration + "-hour free slot between "
                                + DAY_START_HOUR + ":00 and " + DAY_END_HOUR + ":00.";
                        unscheduledList.add(new UnscheduledActivity(activity, reason));
                    }
                }
            }
        }

        // Step 5: Sort scheduled activities chronologically by start hour
        sortScheduledActivities(scheduledList);

        DailySchedule dailySchedule = new DailySchedule(
                date, city, scheduledList, unscheduledList);
        ScheduleOutputData outputData = new ScheduleOutputData(
                dailySchedule, score, outdoorAllowed, weatherAvailable);
        schedulePresenter.prepareSuccessView(outputData);
    }

    private int findConflictHour(boolean[] occupiedHours, int start, int end) {
        for (int h = start; h < end; h++) {
            if (occupiedHours[h]) {
                return h;
            }
        }
        return -1;
    }

    private boolean isOutdoorActivity(Activity activity) {
        String category = activity.getCategory();
        return category != null && category.startsWith("OUTDOOR");
    }

    private int findAvailableSlot(boolean[] occupiedHours, int duration) {
        if (duration <= 0) {
            return -1;
        }
        for (int start = DAY_START_HOUR; start + duration <= DAY_END_HOUR; start++) {
            boolean canFit = true;
            for (int h = start; h < start + duration; h++) {
                if (occupiedHours[h]) {
                    canFit = false;
                    break;
                }
            }
            if (canFit) {
                return start;
            }
        }
        return -1;
    }

    private void sortScheduledActivities(List<ScheduledActivity> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j).getStartHour() > list.get(j + 1).getStartHour()) {
                    ScheduledActivity temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}