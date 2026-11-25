package use_case.weather;

import entity.WeatherData;

/**
 * Pure domain calculator that evaluates a 0-100 outdoor suitability score
 * from daily WeatherData metrics. Shared cleanly by Use Case 6 and Use Case 8.
 */
public class WeatherScoreCalculator {

    /** Minimum suitability score required to recommend outdoor activities. */
    public static final int OUTDOOR_SAFE_THRESHOLD = 60;

    /** Maximum possible suitability score. */
    public static final int MAX_SCORE = 100;

    /** Minimum possible suitability score. */
    public static final int MIN_SCORE = 0;

    private static final double HEAVY_RAIN_MM = 5.0;
    private static final double LIGHT_RAIN_MM = 0.5;
    private static final int HEAVY_RAIN_PENALTY = 60;
    private static final int LIGHT_RAIN_PENALTY = 35;

    private static final double FREEZING_TEMP_C = 0.0;
    private static final double EXTREME_HEAT_C = 33.0;
    private static final double CHILLY_TEMP_C = 10.0;
    private static final double WARM_TEMP_C = 28.0;
    private static final int EXTREME_TEMP_PENALTY = 45;
    private static final int MODERATE_TEMP_PENALTY = 20;

    private static final double HIGH_WIND_KMH = 40.0;
    private static final double MODERATE_WIND_KMH = 25.0;
    private static final int HIGH_WIND_PENALTY = 30;
    private static final int MODERATE_WIND_PENALTY = 15;

    /**
     * Constructs a WeatherScoreCalculator instance.
     */
    public WeatherScoreCalculator() {
    }

    /**
     * Calculates a 0-100 outdoor suitability score from the provided daily weather data.
     *
     * @param weatherData the daily WeatherData entity to evaluate
     * @return an integer suitability score clamped between 0 and 100
     */
    public int calculateScore(WeatherData weatherData) {
        if (weatherData == null) {
            return MIN_SCORE;
        }

        int score = MAX_SCORE;
        double precipitation = weatherData.getPrecipitation();
        double temperature = weatherData.getTemperature();
        double windSpeed = weatherData.getWindSpeed();

        // Apply precipitation penalty (millimeters)
        if (precipitation > HEAVY_RAIN_MM) {
            score = score - HEAVY_RAIN_PENALTY;
        } else if (precipitation > LIGHT_RAIN_MM) {
            score = score - LIGHT_RAIN_PENALTY;
        }

        // Apply temperature penalty (degrees Celsius)
        if (temperature < FREEZING_TEMP_C || temperature > EXTREME_HEAT_C) {
            score = score - EXTREME_TEMP_PENALTY;
        } else if (temperature < CHILLY_TEMP_C || temperature > WARM_TEMP_C) {
            score = score - MODERATE_TEMP_PENALTY;
        }

        // Apply wind speed penalty (km/h)
        if (windSpeed > HIGH_WIND_KMH) {
            score = score - HIGH_WIND_PENALTY;
        } else if (windSpeed > MODERATE_WIND_KMH) {
            score = score - MODERATE_WIND_PENALTY;
        }

        if (score < MIN_SCORE) {
            score = MIN_SCORE;
        }
        if (score > MAX_SCORE) {
            score = MAX_SCORE;
        }

        return score;
    }

    /**
     * Determines whether a suitability score meets the safe threshold for outdoor activities.
     *
     * @param score the calculated 0-100 weather suitability score
     * @return true if outdoor activities are recommended, false otherwise
     */
    public boolean isSuitableForOutdoor(int score) {
        if (score >= OUTDOOR_SAFE_THRESHOLD) {
            return true;
        }
        return false;
    }
}