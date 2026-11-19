package entity;

/**
 * Core entity storing daily weather forecast metrics for a specific City and date.
 */
public class WeatherData {

    private final String date;
    private final City city;
    private final double temperature;
    private final double precipitation;
    private final double windSpeed;
    private final String condition;

    /**
     * Constructs a WeatherData entity for a given date and city.
     *
     * @param date          the forecast date in "YYYY-MM-DD" format
     * @param city          the City enum for this weather record
     * @param temperature   the daily maximum temperature in degrees Celsius
     * @param precipitation the daily total precipitation in millimeters
     * @param windSpeed     the daily maximum wind speed in km/h
     * @param condition     a short text description of the weather condition
     */
    public WeatherData(String date, City city, double temperature,
                       double precipitation, double windSpeed, String condition) {
        if (date != null) {
            this.date = date.trim();
        } else {
            this.date = "";
        }
        this.city = city;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        if (condition != null) {
            this.condition = condition;
        } else {
            this.condition = "Unknown";
        }
    }

    /**
     * Returns the date of this weather forecast.
     *
     * @return the date string in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the City for this weather forecast.
     *
     * @return the City enum constant
     */
    public City getCity() {
        return city;
    }

    /**
     * Returns the daily maximum temperature.
     *
     * @return the temperature in degrees Celsius
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Returns the daily total precipitation.
     *
     * @return the precipitation in millimeters
     */
    public double getPrecipitation() {
        return precipitation;
    }

    /**
     * Returns the daily maximum wind speed.
     *
     * @return the wind speed in km/h
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Returns the human-readable weather condition summary.
     *
     * @return the weather condition description
     */
    public String getCondition() {
        return condition;
    }
}