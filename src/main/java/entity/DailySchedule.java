package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the complete generated schedule for a specific date and city.
 */
public class DailySchedule {

    private final String date;      // e.g., "2026-11-16"
    private final String city;      // e.g., "Toronto"
    private final List<ScheduledActivity> scheduledActivities;
    private final List<Activity> unscheduledActivities;      // Activities that could not fit

    public DailySchedule(String date, String city, List<ScheduledActivity> scheduledActivities,
                         List<Activity> unscheduledActivities) {
        this.date = date;
        this.city = city;
        this.scheduledActivities = new ArrayList<>(scheduledActivities);
        this.unscheduledActivities = new ArrayList<>(unscheduledActivities);
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public List<ScheduledActivity> getScheduledActivities() {
        return scheduledActivities;
    }

    public List<Activity> getUnscheduledActivities() {
        return unscheduledActivities;
    }
}