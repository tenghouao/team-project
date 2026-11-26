package use_case.weather;

import entity.WeatherData;

/**
 * Output data bundle produced by Use Case 6 after retrieving and evaluating weather data.
 */
public class WeatherOutputData {

    private final WeatherData weatherData;
    private final int suitabilityScore;
    private final boolean suitableForOutdoor;
    private final boolean loadedFromCache;

    /**
     * Constructs a WeatherOutputData bundle.
     *
     * @param weatherData        the retrieved WeatherData entity
     * @param suitabilityScore   the evaluated 0-100 outdoor suitability score
     * @param suitableForOutdoor true if the score meets the safe outdoor threshold
     * @param loadedFromCache    true if loaded from local storage, false if fetched live
     */
    public WeatherOutputData(WeatherData weatherData, int suitabilityScore,
                             boolean suitableForOutdoor, boolean loadedFromCache) {
        this.weatherData = weatherData;
        this.suitabilityScore = suitabilityScore;
        this.suitableForOutdoor = suitableForOutdoor;
        this.loadedFromCache = loadedFromCache;
    }

    /**
     * Returns the retrieved WeatherData entity.
     *
     * @return the WeatherData entity
     */
    public WeatherData getWeatherData() {
        return weatherData;
    }

    /**
     * Returns the evaluated outdoor suitability score.
     *
     * @return the suitability score from 0 to 100
     */
    public int getSuitabilityScore() {
        return suitabilityScore;
    }

    /**
     * Indicates whether the weather is suitable for outdoor activities.
     *
     * @return true if outdoor activities are recommended, false otherwise
     */
    public boolean isSuitableForOutdoor() {
        return suitableForOutdoor;
    }

    /**
     * Indicates whether the weather data came from local cache instead of a live API call.
     *
     * @return true if loaded from local cache, false if fetched from the API
     */
    public boolean isLoadedFromCache() {
        return loadedFromCache;
    }
}