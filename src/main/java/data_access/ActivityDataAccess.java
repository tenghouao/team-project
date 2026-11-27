package data_access;

import entity.Activity;
import entity.RecurringRoutine;
import use_case.activity.ActivityDataAccessInterface;
import org.json.*;

import java.util.List;

public class ActivityDataAccess implements ActivityDataAccessInterface {

    /**
     * Saves a new one-time activity (Use Case 1).
     *
     * @param activity the activity to save; its date must not be empty
     * @return true if the activity was saved, false if saving failed
     */
    @Override
    public boolean saveActivity(Activity activity) {
        return false;
    }

    /**
     * Saves a new recurring activity on each of the given days of the week (Use Case 4).
     * The same Activity (same ID) is added to the routine of every listed day.
     *
     * @param activity   the activity to save; its date should be ""
     * @param daysOfWeek lowercase day names, e.g. ["monday", "wednesday"]
     * @return true if the activity was saved, false if saving failed
     */
    @Override
    public boolean saveRecurringActivity(Activity activity, List<String> daysOfWeek) {
        return false;
    }

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
    @Override
    public boolean updateActivity(Activity activity, List<String> daysOfWeek) {
        return false;
    }

    /**
     * Deletes the activity with the given ID, including every occurrence
     * of a recurring activity (Use Case 3).
     *
     * @param activityId the ID of the activity to delete
     * @return true if the activity was deleted, false if deleting failed
     */
    @Override
    public boolean deleteActivityById(String activityId) {
        return false;
    }

    /**
     * Returns the activity with the given ID.
     *
     * @param activityId the ID of the activity
     * @return the Activity, or null if no activity has this ID
     */
    @Override
    public Activity getActivityById(String activityId) {
        return null;
    }

    /**
     * Returns every one-time activity (every activity whose date is not empty).
     * Used to find one-time events that fall on the weekdays of a recurring activity.
     *
     * @return all one-time activities, or an empty list if there are none
     */
    @Override
    public List<Activity> getAllOneTimeActivities() {
        return List.of();
    }

    /**
     * Returns the days of the week a recurring activity repeats on.
     *
     * @param activityId the ID of the activity
     * @return lowercase day names, or an empty list if the activity is one-time or not found
     */
    @Override
    public List<String> getRecurringDays(String activityId) {
        return List.of();
    }

    /**
     * Retrieves the saved weekly recurring routine for a given day of the week.
     *
     * @param dayOfWeek the lowercase day name (e.g., "monday", "thursday")
     * @return the RecurringRoutine for that day, or null if none exists
     */
    @Override
    public RecurringRoutine getRoutineByDay(String dayOfWeek) {
        return null;
    }

    /**
     * Retrieves all saved one-time events scheduled for a specific calendar date.
     *
     * @param date the target date in "YYYY-MM-DD" format
     * @return a list of one-time Activity entities for that date, or an empty list if none
     */
    @Override
    public List<Activity> getEventsByDate(String date) {
        return List.of();
    }
}
