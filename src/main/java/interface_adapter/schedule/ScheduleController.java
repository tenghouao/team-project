package interface_adapter.schedule;

import entity.City;
import use_case.schedule.ScheduleInputBoundary;
import use_case.schedule.ScheduleInputData;
import use_case.weather.WeatherInputBoundary;
import use_case.weather.WeatherInputData;

/**
 * Controller triggered by UI actions for Use Case 6 (Check Weather)
 * and Use Case 8 (Generate Weather-Aware Daily Schedule).
 */
public class ScheduleController {

    private final WeatherInputBoundary weatherInteractor;
    private final ScheduleInputBoundary scheduleInteractor;

    /**
     * Constructs a ScheduleController wired to the weather and schedule input boundaries.
     *
     * @param weatherInteractor  the input boundary for Use Case 6
     * @param scheduleInteractor the input boundary for Use Case 8
     */
    public ScheduleController(WeatherInputBoundary weatherInteractor,
                              ScheduleInputBoundary scheduleInteractor) {
        this.weatherInteractor = weatherInteractor;
        this.scheduleInteractor = scheduleInteractor;
    }

    /**
     * Triggers Use Case 6 alone to fetch (or load cached) weather and calculate its score.
     *
     * @param city         the selected City enum from the UI dropdown
     * @param date         the target date in "YYYY-MM-DD" format
     * @param forceRefresh true to force a fresh API query, false to check local cache first
     */
    public void checkWeather(City city, String date, boolean forceRefresh) {
        WeatherInputData weatherInput = new WeatherInputData(city, date, forceRefresh);
        weatherInteractor.execute(weatherInput);
    }

    /**
     * Triggers Use Case 6 followed by Use Case 8 to ensure weather is evaluated
     * and then generate the conflict-free daily schedule.
     *
     * @param city         the selected City enum from the UI dropdown
     * @param date         the target date in "YYYY-MM-DD" format
     * @param dayOfWeek    the day of the week (e.g., "Thursday")
     * @param forceRefresh true to force a fresh API query, false to check local cache first
     */
    public void generateDailySchedule(City city, String date, String dayOfWeek,
                                      boolean forceRefresh) {
        // Step 1: Run Use Case 6 (fetches weather and persists it in local cache)
        WeatherInputData weatherInput = new WeatherInputData(city, date, forceRefresh);
        weatherInteractor.execute(weatherInput);

        // Step 2: Run Use Case 8 (reads cached weather + events and builds the schedule)
        ScheduleInputData scheduleInput = new ScheduleInputData(city, date, dayOfWeek);
        scheduleInteractor.execute(scheduleInput);
    }
}