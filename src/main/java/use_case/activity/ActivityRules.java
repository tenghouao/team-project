package use_case.activity;

import entity.Activity;
import entity.RecurringRoutine;
import use_case.schedule.EventDataAccessInterface;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shared validation and overlap-checking rules used by the activity interactors
 * (Use Cases 1, 2, and 4), so the same rules are not copied into each interactor.
 */
public final class ActivityRules {

    /** Number of hours in a day; the latest allowed end hour. */
    public static final int HOURS_IN_DAY = 24;

    /** Valid lowercase day names, in calendar order. */
    public static final List<String> DAYS_OF_WEEK = List.of(
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday");

    private ActivityRules() {
        // Utility class; not meant to be instantiated.
    }

    /**
     * Checks the name, category, and time details of an activity.
     *
     * @param name          the activity name
     * @param category      the category constant from Activity
     * @param isFixed       true for a fixed-time activity, false for a flexible one
     * @param startHour     the start hour (0-23); only checked when isFixed is true
     * @param endHour       the end hour (1-24); only checked when isFixed is true
     * @param durationHours the duration in hours; only checked when isFixed is false
     * @return a list of error messages, empty if every detail is valid
     */
    public static List<String> validateDetails(String name, String category, boolean isFixed,
                                               int startHour, int endHour, int durationHours) {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Name is required.");
        }

        if (!isValidCategory(category)) {
            errors.add("Please choose a category.");
        }

        if (isFixed) {
            if (startHour < 0 || startHour >= HOURS_IN_DAY) {
                errors.add("Start time must be between 0:00 and 23:00.");
            }
            if (endHour <= startHour) {
                errors.add("End time must be after the start time.");
            }
            if (endHour > HOURS_IN_DAY) {
                errors.add("End time must be no later than 24:00.");
            }
        } else {
            if (durationHours < 1 || durationHours > HOURS_IN_DAY) {
                errors.add("Duration must be between 1 and 24 hours.");
            }
        }

        return errors;
    }

    /**
     * Checks that a date is present and in "YYYY-MM-DD" format.
     *
     * @param date the date string to check
     * @return an error message, or null if the date is valid
     */
    public static String validateDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return "Date is required.";
        }
        try {
            LocalDate.parse(date.trim());
            return null;
        } catch (DateTimeParseException e) {
            return "Date must be in YYYY-MM-DD format.";
        }
    }

    /**
     * Returns the duration of an activity in hours.
     *
     * @param isFixed       true for a fixed-time activity
     * @param startHour     the start hour of a fixed activity
     * @param endHour       the end hour of a fixed activity
     * @param durationHours the duration of a flexible activity
     * @return endHour - startHour for a fixed activity, otherwise durationHours
     */
    public static int durationOf(boolean isFixed, int startHour, int endHour, int durationHours) {
        if (isFixed) {
            return endHour - startHour;
        }
        return durationHours;
    }

    /**
     * Returns the lowercase day of the week for a valid "YYYY-MM-DD" date.
     *
     * @param date a valid date string
     * @return the day of the week, e.g. "monday"
     */
    public static String dayOfWeekOf(String date) {
        return LocalDate.parse(date.trim()).getDayOfWeek().name().toLowerCase();
    }

    /**
     * Finds fixed-time activities that overlap the given time range.
     * Checks one-time events on the date (if given) and the recurring routine
     * for the day of the week.
     *
     * @param dataAccess  the data access object to read activities from
     * @param date        a "YYYY-MM-DD" date, or null to check only the routine
     * @param dayOfWeek   the lowercase day of the week to check the routine of
     * @param startHour   the start hour of the new or edited activity
     * @param durationHrs the duration of the new or edited activity
     * @param excludeId   the ID of the activity being edited (ignored in the check), or null
     * @return the overlapping activities, without duplicates; empty if there are none
     */
    public static List<Activity> findOverlaps(EventDataAccessInterface dataAccess, String date,
                                              String dayOfWeek, int startHour, int durationHrs,
                                              String excludeId) {
        List<Activity> candidates = new ArrayList<>();
        if (date != null) {
            List<Activity> events = dataAccess.getEventsByDate(date);
            if (events != null) {
                candidates.addAll(events);
            }
        }
        RecurringRoutine routine = dataAccess.getRoutineByDay(dayOfWeek);
        if (routine != null) {
            candidates.addAll(routine.getActivities());
        }

        int endHour = startHour + durationHrs;
        List<Activity> overlaps = new ArrayList<>();
        List<String> seenIds = new ArrayList<>();
        for (Activity other : candidates) {
            if (other == null || !other.isFixed()) {
                continue;
            }
            if (other.getId().equals(excludeId) || seenIds.contains(other.getId())) {
                continue;
            }
            int otherStart = other.getFixedStartHour();
            int otherEnd = otherStart + other.getDurationHours();
            if (startHour < otherEnd && otherStart < endHour) {
                overlaps.add(other);
                seenIds.add(other.getId());
            }
        }
        return overlaps;
    }

    /**
     * Builds a readable warning listing overlapping activities.
     *
     * @param overlaps the overlapping activities
     * @return a message such as "This overlaps with: Gym (9:00-11:00)."
     */
    public static String describeOverlaps(List<Activity> overlaps) {
        List<String> parts = new ArrayList<>();
        for (Activity a : overlaps) {
            int start = a.getFixedStartHour();
            int end = start + a.getDurationHours();
            parts.add(a.getName() + " (" + start + ":00-" + end + ":00)");
        }
        return "This overlaps with: " + String.join(", ", parts) + ".";
    }

    /**
     * Checks that at least one day of the week is selected and every day name is valid.
     *
     * @param daysOfWeek the selected day names (any letter case)
     * @return an error message, or null if the days are valid
     */
    public static String validateDaysOfWeek(List<String> daysOfWeek) {
        if (daysOfWeek == null || daysOfWeek.isEmpty()) {
            return "Please choose at least one day of the week.";
        }
        for (String day : daysOfWeek) {
            if (day == null || !DAYS_OF_WEEK.contains(day.trim().toLowerCase())) {
                return "Unknown day of the week: " + day + ".";
            }
        }
        return null;
    }

    /**
     * Converts valid day names to lowercase, removes duplicates, and sorts them
     * from Monday to Sunday.
     *
     * @param daysOfWeek the selected day names; must already pass validateDaysOfWeek
     * @return the normalized day names
     */
    public static List<String> normalizeDays(List<String> daysOfWeek) {
        List<String> lower = new ArrayList<>();
        for (String day : daysOfWeek) {
            lower.add(day.trim().toLowerCase());
        }
        List<String> result = new ArrayList<>();
        for (String day : DAYS_OF_WEEK) {
            if (lower.contains(day)) {
                result.add(day);
            }
        }
        return result;
    }

    /**
     * Checks a recurring time range against the routine of each given day.
     *
     * @param dataAccess  the data access object to read routines from
     * @param daysOfWeek  normalized day names to check
     * @param startHour   the start hour of the recurring activity
     * @param durationHrs the duration of the recurring activity
     * @param excludeId   the ID of the activity being edited, or null
     * @return a warning listing the conflicting days and activities, or null if none overlap
     */
    public static String checkOverlapsOnDays(EventDataAccessInterface dataAccess,
                                             List<String> daysOfWeek, int startHour,
                                             int durationHrs, String excludeId) {
        List<String> lines = new ArrayList<>();
        for (String day : daysOfWeek) {
            List<Activity> overlaps = findOverlaps(dataAccess, null, day,
                    startHour, durationHrs, excludeId);
            if (!overlaps.isEmpty()) {
                lines.add(capitalize(day) + ": " + describeOverlaps(overlaps));
            }
        }
        if (lines.isEmpty()) {
            return null;
        }
        return String.join("\n", lines);
    }

    private static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }

    private static boolean isValidCategory(String category) {
        return Activity.INDOOR.equals(category)
                || Activity.OUTDOOR_SPORT.equals(category)
                || Activity.OUTDOOR_LEISURE.equals(category);
    }
}
