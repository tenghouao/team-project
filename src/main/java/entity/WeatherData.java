package entity;

/**
 * Represents hourly weather forecast data for a specific hour of the day.
 */
public class WeatherData {

    private final int hour;                         // Hour of the day (0 to 23)
    private final double temperatureCelsius;        // Temperature in Celsius
    private final int precipitationProbability;     // Rain/snow probability (0 to 100%)
    private final double windSpeedKmh;              // Wind speed in km/h
    private final String conditionDescription;      // e.g., "Clear", "Rain", "Cloudy"

    public WeatherData(int hour, double temperatureCelsius, int precipitationProbability,
                       double windSpeedKmh, String conditionDescription) {
        this.hour = hour;
        this.temperatureCelsius = temperatureCelsius;
        this.precipitationProbability = precipitationProbability;
        this.windSpeedKmh = windSpeedKmh;
        this.conditionDescription = conditionDescription;
    }

    public int getHour() {
        return hour;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public double getWindSpeedKmh() {
        return windSpeedKmh;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }
}