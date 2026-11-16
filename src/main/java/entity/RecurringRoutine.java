package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a saved weekly routine template (e.g., for "Monday") containing preset activities.
 */
public class RecurringRoutine {

    private final String dayOfWeek;      // e.g., "Monday", "Tuesday"
    private final List<Activity> activities;

    public RecurringRoutine(String dayOfWeek, List<Activity> activities) {
        this.dayOfWeek = dayOfWeek;
        this.activities = new ArrayList<>(activities);
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}