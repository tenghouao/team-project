package use_case.activity;

import entity.Activity;
import use_case.schedule.EventDataAccessInterface;

import java.util.List;

/**
 * Data access interface for creating, editing, and deleting activities
 * (Use Cases 1-4). Extends Tobey's read-only EventDataAccessInterface so that
 * a single data access class (implemented by Teng) owns all activity storage.
 *
 * One-time activities have a date in "YYYY-MM-DD" format.
 * Recurring activities have an empty date ("") and are stored in the
 * RecurringRoutine of each day of the week they repeat on.
 */
public interface ActivityDataAccessInterface extends EventDataAccessInterface {

    /**
     * Saves a new one-time activity (Use Case 1).
     *
     * @param activity the activity to save; its date must not be empty
     * @return true if the activity was saved, false if saving failed
     */
    boolean saveActivity(Activity activity);

    /**
     * Saves a new recurring activity on each of the given days of the week (Use Case 4).
     * The same Activity (same ID) is added to the routine of every listed day.
     *
     * @param activity   the activity to save; its date should be ""
     * @param daysOfWeek lowercase day names, e.g. ["monday", "wednesday"]
     * @return true if the activity was saved, false if saving failed
     */
    boolean saveRecurringActivity(Activity activity, List<String> daysOfWeek);

    /**
     * Replaces the stored activity that has the same ID as the given activity (Use Case 2).
     * For a recurring activity, every occurrence is replaced and the activity is
     * moved to exactly the given days of the week.
     *
     * @param activity   the updated activity; its ID must match an existing activity
     * @param daysOfWeek the days a recurring activity repeats on, or an empty list
     *                   for a one-time activity
     * @return true if the activity was updated, false if updating failed
     */
    boolean updateActivity(Activity activity, List<String> daysOfWeek);

    /**
     * Deletes the activity with the given ID, including every occurrence
     * of a recurring activity (Use Case 3).
     *
     * @param activityId the ID of the activity to delete
     * @return true if the activity was deleted, false if deleting failed
     */
    boolean deleteActivityById(String activityId);

    /**
     * Returns the activity with the given ID.
     *
     * @param activityId the ID of the activity
     * @return the Activity, or null if no activity has this ID
     */
    Activity getActivityById(String activityId);

    /**
     * Returns every one-time activity (every activity whose date is not empty).
     * Used to find one-time events that fall on the weekdays of a recurring activity.
     *
     * @return all one-time activities, or an empty list if there are none
     */
    List<Activity> getAllOneTimeActivities();

    /**
     * Returns the days of the week a recurring activity repeats on.
     *
     * @param activityId the ID of the activity
     * @return lowercase day names, or an empty list if the activity is one-time or not found
     */
    List<String> getRecurringDays(String activityId);
}
