package use_case.delete_activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Output data for Use Case 3 (Delete an event).
 */
public class DeleteActivityOutputData {

    private final String activityId;
    private final String name;
    private final String date;
    private final List<String> recurringDays;

    /**
     * Constructs a DeleteActivityOutputData bundle.
     *
     * @param activityId    the ID of the activity
     * @param name          the activity name
     * @param date          the date in "YYYY-MM-DD" format, or "" for a recurring activity
     * @param recurringDays the days a recurring activity repeats on, or an empty list
     */
    public DeleteActivityOutputData(String activityId, String name, String date,
                                    List<String> recurringDays) {
        this.activityId = activityId;
        this.name = name;
        this.date = date;
        if (recurringDays != null) {
            this.recurringDays = new ArrayList<>(recurringDays);
        } else {
            this.recurringDays = new ArrayList<>();
        }
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
}
