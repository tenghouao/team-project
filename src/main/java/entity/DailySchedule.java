package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Core entity holding the generated hourly timeline and unscheduled activities for a day.
 */
public class DailySchedule {

    private final String date;
    private final City city;
    private final List<ScheduledActivity> scheduledActivities;
    private final List<UnscheduledActivity> unscheduledActivities;

    /**
     * Constructs a DailySchedule for a specific date and city.
     *
     * @param date                  the schedule date in "YYYY-MM-DD" format
     * @param city                  the City whose weather was used to build this schedule
     * @param scheduledActivities   the list of activities assigned to hourly slots
     * @param unscheduledActivities the list of activities that could not be scheduled
     */
    public DailySchedule(String date, City city,
                         List<ScheduledActivity> scheduledActivities,
                         List<UnscheduledActivity> unscheduledActivities) {
        if (date != null) {
            this.date = date.trim();
        } else {
            this.date = "";
        }
        this.city = city;

        if (scheduledActivities != null) {
            this.scheduledActivities = new ArrayList<>(scheduledActivities);
        } else {
            this.scheduledActivities = new ArrayList<>();
        }

        if (unscheduledActivities != null) {
            this.unscheduledActivities = new ArrayList<>(unscheduledActivities);
        } else {
            this.unscheduledActivities = new ArrayList<>();
        }
    }

    /**
     * Returns the calendar date of this daily schedule.
     *
     * @return the date string in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the City associated with this daily schedule.
     *
     * @return the City enum constant
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns a defensive copy of the scheduled activities list.
     *
     * @return a list of ScheduledActivity items in this schedule
     */
    public List<ScheduledActivity> getScheduledActivities() {
        return new ArrayList<>(scheduledActivities);
    }

    /**
     * Returns a defensive copy of the unscheduled activities list with reasons.
     *
     * @return a list of UnscheduledActivity items
     */
    public List<UnscheduledActivity> getUnscheduledActivities() {
        return new ArrayList<>(unscheduledActivities);
    }
}