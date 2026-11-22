package use_case.schedule;

import entity.City;

/**
 * Input data bundle for Use Case 8 (Generate Weather-Aware Daily Schedule).
 */
public class ScheduleInputData {

    private final City city;
    private final String date;
    private final String dayOfWeek;

    /**
     * Constructs a ScheduleInputData bundle.
     *
     * @param city      the City enum whose weather governs outdoor eligibility
     * @param date      the target calendar date in "YYYY-MM-DD" format
     * @param dayOfWeek the day of the week (normalized to lowercase, e.g., "thursday")
     */
    public ScheduleInputData(City city, String date, String dayOfWeek) {
        this.city = city;
        if (date != null) {
            this.date = date.trim();
        } else {
            this.date = "";
        }
        if (dayOfWeek != null) {
            this.dayOfWeek = dayOfWeek.trim().toLowerCase();
        } else {
            this.dayOfWeek = "";
        }
    }

    /**
     * Returns the selected City enum.
     *
     * @return the City enum
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns the target calendar date.
     *
     * @return the date string in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the normalized lowercase day of the week.
     *
     * @return the day of the week string
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }
}