package use_case.schedule;

import entity.Activity;
import entity.RecurringRoutine;

import java.util.List;

/**
 * Data access interface for loading saved recurring routines and one-time events (Use Case 5).
 * Explicitly separates weekly recurring templates from date-specific one-time events.
 */
public interface EventDataAccessInterface {

    /**
     * Retrieves the saved weekly recurring routine for a given day of the week.
     *
     * @param dayOfWeek the lowercase day name (e.g., "monday", "thursday")
     * @return the RecurringRoutine for that day, or null if none exists
     */
    RecurringRoutine getRoutineByDay(String dayOfWeek);

    /**
     * Retrieves all saved one-time events scheduled for a specific calendar date.
     *
     * @param date the target date in "YYYY-MM-DD" format
     * @return a list of one-time Activity entities for that date, or an empty list if none
     */
    List<Activity> getEventsByDate(String date);
}