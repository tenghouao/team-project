package use_case.weather;

import entity.City;
import entity.WeatherData;

/**
 * Data access interface for fetching live daily weather forecasts from an external API.
 */
public interface WeatherAPIDataAccessInterface {

    /**
     * Fetches daily weather metrics from an external weather service for a city and date.
     *
     * @param city the target City enum providing geographic coordinates
     * @param date the target date in "YYYY-MM-DD" format
     * @return a WeatherData entity if the request succeeds, or null if the API call fails
     */
    WeatherData fetchWeatherFromApi(City city, String date);
}