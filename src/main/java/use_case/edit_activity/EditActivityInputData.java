package use_case.edit_activity;

import entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Input data for Use Case 2 (Edit an event).
 * A fixed activity uses startHour and endHour; a flexible activity uses durationHours.
 * A one-time activity uses date; a recurring activity uses daysOfWeek.
 */
public class EditActivityInputData {

    private final String activityId;
    private final String name;
    private final String category;
    private final boolean isFixed;
    private final int startHour;
    private final int endHour;
    private final int durationHours;
    private final String date;
    private final List<String> daysOfWeek;
    private final City city;
    private final boolean overlapConfirmed;

    /**
     * Constructs an EditActivityInputData bundle.
     *
     * @param activityId       the ID of the activity being edited
     * @param name             the new activity name
     * @param category         a category constant from Activity
     * @param isFixed          true for a fixed time range, false for a flexible duration
     * @param startHour        the start hour (0-23); ignored when isFixed is false
     * @param endHour          the end hour (1-24); ignored when isFixed is false
     * @param durationHours    the duration in hours; ignored when isFixed is true
     * @param date             the new date in "YYYY-MM-DD" format; ignored for a recurring activity
     * @param daysOfWeek       the new days of the week; ignored for a one-time activity
     * @param city             the optional location, or null
     * @param overlapConfirmed true if the user already confirmed keeping an overlapping time
     */
    public EditActivityInputData(String activityId, String name, String category,
                                 boolean isFixed, int startHour, int endHour,
                                 int durationHours, String date, List<String> daysOfWeek,
                                 City city, boolean overlapConfirmed) {
        this.activityId = activityId;
        this.name = name;
        this.category = category;
        this.isFixed = isFixed;
        this.startHour = startHour;
        this.endHour = endHour;
        this.durationHours = durationHours;
        this.date = date;
        if (daysOfWeek != null) {
            this.daysOfWeek = new ArrayList<>(daysOfWeek);
        } else {
            this.daysOfWeek = new ArrayList<>();
        }
        this.city = city;
        this.overlapConfirmed = overlapConfirmed;
    }

    /**
     * Returns the ID of the activity being edited.
     *
     * @return the activity ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Returns the new activity name.
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
     * Returns the new date of a one-time activity.
     *
     * @return the date in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the new days of the week of a recurring activity.
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
     * Returns whether the user already confirmed keeping an overlapping time.
     *
     * @return true if the overlap check should be skipped
     */
    public boolean isOverlapConfirmed() {
        return overlapConfirmed;
    }
}
