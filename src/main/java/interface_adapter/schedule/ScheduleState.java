package interface_adapter.schedule;

import entity.City;
import entity.DailySchedule;
import entity.WeatherData;

/**
 * State object holding all UI-ready weather and daily schedule data for ScheduleViewModel.
 */
public class ScheduleState {

    private City city;
    private String date;
    private WeatherData weatherData;
    private String weatherSummary;
    private int suitabilityScore;
    private boolean suitableForOutdoor;
    private boolean loadedFromCache;
    private boolean weatherAvailable;
    private DailySchedule dailySchedule;
    private String warningMessage;
    private String errorMessage;

    /**
     * Constructs an empty initial ScheduleState with safe defaults.
     */
    public ScheduleState() {
        this.city = City.TORONTO;
        this.date = "";
        this.weatherSummary = "";
        this.suitabilityScore = 0;
        this.suitableForOutdoor = false;
        this.loadedFromCache = false;
        this.weatherAvailable = false;
    }

    /**
     * Returns the currently selected City.
     *
     * @return the City enum
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the currently selected City.
     *
     * @param city the City enum to store
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Returns the currently selected date string.
     *
     * @return the date in "YYYY-MM-DD" format
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the currently selected date string.
     *
     * @param date the date string in "YYYY-MM-DD" format
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the latest retrieved WeatherData entity.
     *
     * @return the WeatherData entity, or null if none is loaded
     */
    public WeatherData getWeatherData() {
        return weatherData;
    }

    /**
     * Sets the latest retrieved WeatherData entity.
     *
     * @param weatherData the WeatherData entity to store
     */
    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    /**
     * Returns a formatted one-line summary of the latest weather report for easy UI display.
     *
     * @return the formatted weather summary string
     */
    public String getWeatherSummary() {
        return weatherSummary;
    }

    /**
     * Sets the formatted one-line weather summary string.
     *
     * @param weatherSummary the formatted weather summary text
     */
    public void setWeatherSummary(String weatherSummary) {
        this.weatherSummary = weatherSummary;
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
     * Sets the evaluated outdoor suitability score.
     *
     * @param suitabilityScore the suitability score from 0 to 100
     */
    public void setSuitabilityScore(int suitabilityScore) {
        this.suitabilityScore = suitabilityScore;
    }

    /**
     * Indicates whether the current weather is suitable for outdoor activities.
     *
     * @return true if outdoor activities are recommended, false otherwise
     */
    public boolean isSuitableForOutdoor() {
        return suitableForOutdoor;
    }

    /**
     * Sets whether the current weather is suitable for outdoor activities.
     *
     * @param suitableForOutdoor true if outdoor activities are recommended
     */
    public void setSuitableForOutdoor(boolean suitableForOutdoor) {
        this.suitableForOutdoor = suitableForOutdoor;
    }

    /**
     * Indicates whether the weather data was loaded from local cache.
     *
     * @return true if loaded from cache, false if fetched live from the API
     */
    public boolean isLoadedFromCache() {
        return loadedFromCache;
    }

    /**
     * Sets whether the weather data was loaded from local cache.
     *
     * @param loadedFromCache true if loaded from cache
     */
    public void setLoadedFromCache(boolean loadedFromCache) {
        this.loadedFromCache = loadedFromCache;
    }

    /**
     * Indicates whether valid weather data was available for the latest operation.
     *
     * @return true if weather data is available, false if indoor-only fallback mode is active
     */
    public boolean isWeatherAvailable() {
        return weatherAvailable;
    }

    /**
     * Sets whether valid weather data is available.
     *
     * @param weatherAvailable true if weather data is available
     */
    public void setWeatherAvailable(boolean weatherAvailable) {
        this.weatherAvailable = weatherAvailable;
    }

    /**
     * Returns the latest generated DailySchedule entity.
     *
     * @return the DailySchedule entity, or null if no schedule has been generated yet
     */
    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    /**
     * Sets the latest generated DailySchedule entity.
     *
     * @param dailySchedule the DailySchedule entity to store
     */
    public void setDailySchedule(DailySchedule dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    /**
     * Returns any non-blocking warning message (such as indoor-only fallback notice).
     *
     * @return the warning message, or null if there is no active warning
     */
    public String getWarningMessage() {
        return warningMessage;
    }

    /**
     * Sets a non-blocking warning message for the UI.
     *
     * @param warningMessage the warning message text, or null to clear
     */
    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    /**
     * Returns the latest error message if an operation failed.
     *
     * @return the error message, or null if there is no error
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets an error message for the UI to display.
     *
     * @param errorMessage the error message text, or null to clear
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}