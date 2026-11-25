package use_case.create_recurring_activity;

import entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Input data for Use Case 4 (Create a recurring event).
 * A fixed activity uses startHour and endHour; a flexible activity uses durationHours.
 */
public class CreateRecurringActivityInputData {

    private final String name;
    private final String category;
    private final boolean isFixed;
    private final int startHour;
    private final int endHour;
    private final int durationHours;
    private final List<String> daysOfWeek;
    private final City city;
    private final boolean overlapConfirmed;

    /**
     * Constructs a CreateRecurringActivityInputData bundle.
     *
     * @param name             the activity name
     * @param category         a category constant from Activity (INDOOR, OUTDOOR_SPORT, OUTDOOR_LEISURE)
     * @param isFixed          true for a fixed time range, false for a flexible duration
     * @param startHour        the start hour (0-23); ignored when isFixed is false
     * @param endHour          the end hour (1-24); ignored when isFixed is false
     * @param durationHours    the duration in hours; ignored when isFixed is true
     * @param daysOfWeek       the days the activity repeats on, e.g. ["Monday", "Wednesday"]
     * @param city             the optional location, or null
     * @param overlapConfirmed true if the user already confirmed keeping an overlapping event
     */
    public CreateRecurringActivityInputData(String name, String category, boolean isFixed,
                                            int startHour, int endHour, int durationHours,
                                            List<String> daysOfWeek, City city,
                                            boolean overlapConfirmed) {
        this.name = name;
        this.category = category;
        this.isFixed = isFixed;
        this.startHour = startHour;
        this.endHour = endHour;
        this.durationHours = durationHours;
        if (daysOfWeek != null) {
            this.daysOfWeek = new ArrayList<>(daysOfWeek);
        } else {
            this.daysOfWeek = new ArrayList<>();
        }
        this.city = city;
        this.overlapConfirmed = overlapConfirmed;
    }

    /**
     * Returns the activity name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category constant.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns whether the activity has a fixed time range.
     *
     * @return true if fixed, false if flexible
     */
    public boolean isFixed() {
        return isFixed;
    }

    /**
     * Returns the start hour of a fixed activity.
     *
     * @return the start hour
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Returns the end hour of a fixed activity.
     *
     * @return the end hour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * Returns the duration of a flexible activity.
     *
     * @return the duration in hours
     */
    public int getDurationHours() {
        return durationHours;
    }

    /**
     * Returns the days of the week the activity repeats on.
     *
     * @return the day names
     */
    public List<String> getDaysOfWeek() {
        return new ArrayList<>(daysOfWeek);
    }

    /**
     * Returns the optional location.
     *
     * @return the City, or null if none was chosen
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns whether the user already confirmed keeping an overlapping event.
     *
     * @return true if the overlap check should be skipped
     */
    public boolean isOverlapConfirmed() {
        return overlapConfirmed;
    }
}
