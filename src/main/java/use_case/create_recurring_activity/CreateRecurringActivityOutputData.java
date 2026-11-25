package use_case.create_recurring_activity;

import entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Output data for Use Case 4 (Create a recurring event), passed to the presenter on success.
 */
public class CreateRecurringActivityOutputData {

    private final String activityId;
    private final String name;
    private final String category;
    private final boolean isFixed;
    private final int startHour;
    private final int durationHours;
    private final List<String> daysOfWeek;
    private final City city;

    /**
     * Constructs a CreateRecurringActivityOutputData bundle.
     *
     * @param activityId    the ID of the created activity
     * @param name          the activity name
     * @param category      the category constant
     * @param isFixed       true if the activity has a fixed time
     * @param startHour     the start hour, or Activity.FLEXIBLE_START_HOUR if flexible
     * @param durationHours the duration in hours
     * @param daysOfWeek    the lowercase days the activity repeats on, Monday first
     * @param city          the location, or null
     */
    public CreateRecurringActivityOutputData(String activityId, String name, String category,
                                             boolean isFixed, int startHour, int durationHours,
                                             List<String> daysOfWeek, City city) {
        this.activityId = activityId;
        this.name = name;
        this.category = category;
        this.isFixed = isFixed;
        this.startHour = startHour;
        this.durationHours = durationHours;
        if (daysOfWeek != null) {
            this.daysOfWeek = new ArrayList<>(daysOfWeek);
        } else {
            this.daysOfWeek = new ArrayList<>();
        }
        this.city = city;
    }

    /**
     * Returns the ID of the created activity.
     *
     * @return the activity ID
     */
    public String getActivityId() {
        return activityId;
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
     * Returns whether the activity has a fixed time.
     *
     * @return true if fixed, false if flexible
     */
    public boolean isFixed() {
        return isFixed;
    }

    /**
     * Returns the start hour.
     *
     * @return the start hour, or Activity.FLEXIBLE_START_HOUR if flexible
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Returns the duration.
     *
     * @return the duration in hours
     */
    public int getDurationHours() {
        return durationHours;
    }

    /**
     * Returns the days of the week the activity repeats on.
     *
     * @return lowercase day names, Monday first
     */
    public List<String> getDaysOfWeek() {
        return new ArrayList<>(daysOfWeek);
    }

    /**
     * Returns the location.
     *
     * @return the City, or null
     */
    public City getCity() {
        return city;
    }
}