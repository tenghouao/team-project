package use_case.weather;

import entity.City;
import entity.WeatherData;

/**
 * Data access interface for local weather caching (Use Case 7).
 * Implemented by the persistence layer to store and reuse previously fetched weather.
 */
public interface WeatherLocalDataAccessInterface {

    /**
     * Retrieves previously cached weather data for the specified city and date.
     *
     * @param city the target City enum
     * @param date the target date in "YYYY-MM-DD" format
     * @return the cached WeatherData entity, or null if no local record exists
     */
    WeatherData getSavedWeather(City city, String date);

    /**
     * Persists or overwrites a WeatherData record in local storage for future reuse.
     *
     * @param weatherData the WeatherData entity to save
     */
    void saveWeather(WeatherData weatherData);
}