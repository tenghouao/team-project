package data_access;

import entity.City;
import entity.WeatherData;
import use_case.weather.WeatherLocalDataAccessInterface;

public class WeatherLocalDataAccess implements WeatherLocalDataAccessInterface {
    @Override
    public WeatherData getSavedWeather(City city, String date) {
        return null;
    }

    @Override
    public void saveWeather(WeatherData weatherData) {
    }
}
