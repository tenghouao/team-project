package use_case.edit_activity;

import entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Output data for Use Case 2 (Edit an event).
 * Used both to pre-fill the edit form and to show the result of the edit.
 */
public class EditActivityOutputData {

    private final String activityId;
    private final String name;
    private final String category;
    private final boolean isFixed;
    private final int startHour;
    private final int durationHours;
    private final String date;
    private final List<String> recurringDays;
    private final City city;

    /**
     * Constructs an EditActivityOutputData bundle.
     *
     * @param activityId    the ID of the activity
     * @param name          the activity name
     * @param category      the category constant
     * @param isFixed       true if the activity has a fixed time
     * @param startHour     the start hour, or Activity.FLEXIBLE_START_HOUR if flexible
     * @param durationHours the duration in hours
     * @param date          the date in "YYYY-MM-DD" format, or "" for a recurring activity
     * @param recurringDays the days a recurring activity repeats on, or an empty list
     * @param city          the location, or null
     */
    public EditActivityOutputData(String activityId, String name, String category,
                                  boolean isFixed, int startHour, int durationHours,
                                  String date, List<String> recurringDays, City city) {
        this.activityId = activityId;
        this.name = name;
        this.category = category;
        this.isFixed = isFixed;
        this.startHour = startHour;
        this.durationHours = durationHours;
        this.date = date;
        if (recurringDays != null) {
            this.recurringDays = new ArrayList<>(recurringDays);
        } else {
            this.recurringDays = new ArrayList<>();
        }
        this.city = city;
    }

    /**
     * Returns the ID of the activity.
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
     * Returns the end hour of a fixed activity (start hour + duration).
     *
     * @return the end hour, or Activity.FLEXIBLE_START_HOUR if flexible
     */
    public int getEndHour() {
        if (isFixed) {
            return startHour + durationHours;
        }
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
     * Returns the date of a one-time activity.
     *
     * @return the date in "YYYY-MM-DD" format, or "" for a recurring activity
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the days a recurring activity repeats on.
     *
     * @return lowercase day names, or an empty list for a one-time activity
     */
    public List<String> getRecurringDays() {
        return new ArrayList<>(recurringDays);
    }

    /**
     * Returns whether the activity is recurring.
     *
     * @return true if it repeats on at least one day of the week
     */
    public boolean isRecurring() {
        return !recurringDays.isEmpty();
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
