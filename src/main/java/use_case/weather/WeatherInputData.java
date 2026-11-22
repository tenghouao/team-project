package use_case.weather;

import entity.City;

/**
 * Input data bundle for Use Case 6 (Fetch and Evaluate Daily Weather Data).
 */
public class WeatherInputData {

    private final City city;
    private final String date;
    private final boolean forceRefresh;

    /**
     * Constructs a WeatherInputData bundle.
     *
     * @param city         the target City enum
     * @param date         the target date in "YYYY-MM-DD" format
     * @param forceRefresh true to bypass local cache and fetch live data, false for cache-first
     */
    public WeatherInputData(City city, String date, boolean forceRefresh) {
        this.city = city;
        if (date != null) {
            this.date = date.trim();
        } else {
            this.date = "";
        }
        this.forceRefresh = forceRefresh;
    }

    /**
     * Returns the target City enum.
     *
     * @return the requested City
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns the normalized target date string.
     *
     * @return the date in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Indicates whether local weather caching should be bypassed.
     *
     * @return true if a live API refresh was requested, false otherwise
     */
    public boolean isForceRefresh() {
        return forceRefresh;
    }
}