package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Core entity representing a weekly recurring routine template for a day of the week.
 */
public class RecurringRoutine {

    private final String dayOfWeek;
    private final List<Activity> activities;

    /**
     * Constructs a RecurringRoutine for a specific day of the week.
     *
     * @param dayOfWeek  the day of the week (normalized to lowercase, e.g., "monday")
     * @param activities the initial list of recurring activities for this day
     */
    public RecurringRoutine(String dayOfWeek, List<Activity> activities) {
        if (dayOfWeek != null) {
            this.dayOfWeek = dayOfWeek.trim().toLowerCase();
        } else {
            this.dayOfWeek = "";
        }

        if (activities != null) {
            this.activities = new ArrayList<>(activities);
        } else {
            this.activities = new ArrayList<>();
        }
    }

    /**
     * Returns the lowercase day of the week for this routine.
     *
     * @return the normalized day of the week string
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Returns a defensive copy of the activities in this recurring routine.
     *
     * @return a list of Activity entities in this routine
     */
    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

    /**
     * Adds a non-null activity to this recurring routine.
     *
     * @param activity the Activity entity to add
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            this.activities.add(activity);
        }
    }

    /**
     * Removes the first activity matching the specified ID from this routine.
     *
     * @param activityId the unique ID of the activity to remove
     * @return true if an activity was found and removed, false otherwise
     */
    public boolean removeActivityById(String activityId) {
        if (activityId == null) {
            return false;
        }
        for (int i = 0; i < activities.size(); i++) {
            if (activityId.equals(activities.get(i).getId())) {
                activities.remove(i);
                return true;
            }
        }
        return false;
    }
}