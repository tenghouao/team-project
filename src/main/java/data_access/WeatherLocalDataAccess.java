package data_access;

import entity.City;
import entity.WeatherData;
import use_case.weather.WeatherLocalDataAccessInterface;

public class WeatherLocalDataAccess implements WeatherLocalDataAccessInterface {
    /**
     * Retrieves previously cached weather data for the specified city and date.
     *
     * @param city the target City enum
     * @param date the target date in "YYYY-MM-DD" format
     * @return the cached WeatherData entity, or null if no local record exists
     */
    @Override
    public WeatherData getSavedWeather(City city, String date) {
        return null;
    }

    /**
     * Persists or overwrites a WeatherData record in local storage for future reuse.
     *
     * @param weatherData the WeatherData entity to save
     */
    @Override
    public void saveWeather(WeatherData weatherData) {
    }
}
