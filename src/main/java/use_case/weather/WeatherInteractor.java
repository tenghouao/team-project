package use_case.weather;

import entity.City;
import entity.WeatherData;

/**
 * Interactor for Use Case 6: Checks local cached weather first, queries the external API
 * when needed, persists newly fetched weather locally, and calculates the suitability score.
 */
public class WeatherInteractor implements WeatherInputBoundary {

    private final WeatherAPIDataAccessInterface weatherApiDAO;
    private final WeatherLocalDataAccessInterface weatherLocalDAO;
    private final WeatherOutputBoundary weatherPresenter;
    private final WeatherScoreCalculator scoreCalculator;

    /**
     * Constructs a WeatherInteractor with its required data access and presentation dependencies.
     *
     * @param weatherApiDAO    the external API data access interface
     * @param weatherLocalDAO  the local weather cache data access interface
     * @param weatherPresenter the output boundary presenter
     * @param scoreCalculator  the calculator used to score outdoor suitability
     */
    public WeatherInteractor(WeatherAPIDataAccessInterface weatherApiDAO,
                             WeatherLocalDataAccessInterface weatherLocalDAO,
                             WeatherOutputBoundary weatherPresenter,
                             WeatherScoreCalculator scoreCalculator) {
        this.weatherApiDAO = weatherApiDAO;
        this.weatherLocalDAO = weatherLocalDAO;
        this.weatherPresenter = weatherPresenter;
        this.scoreCalculator = scoreCalculator;
    }

    /**
     * Executes Use Case 6 using a cache-first strategy and evaluates the suitability score.
     *
     * @param inputData the input data containing city, date, and forceRefresh flag
     */
    @Override
    public void execute(WeatherInputData inputData) {
        if (inputData == null) {
            weatherPresenter.prepareFailView("Weather request cannot be null.");
            return;
        }

        City city = inputData.getCity();
        String date = inputData.getDate();

        if (city == null || date.isEmpty()) {
            weatherPresenter.prepareFailView("City and date are required to check weather.");
            return;
        }

        WeatherData weatherData = null;
        boolean loadedFromCache = false;

        // Step 1: Check local cache first unless the user requested a live refresh
        if (!inputData.isForceRefresh()) {
            weatherData = weatherLocalDAO.getSavedWeather(city, date);
            if (weatherData != null) {
                loadedFromCache = true;
            }
        }

        // Step 2: Query API on cache miss or forced refresh, then persist locally (UC 7)
        if (weatherData == null) {
            weatherData = weatherApiDAO.fetchWeatherFromApi(city, date);
            if (weatherData == null) {
                weatherPresenter.prepareFailView(
                        "Unable to fetch weather for " + city.getDisplayName()
                                + " on " + date + ". Check your network connection.");
                return;
            }
            weatherLocalDAO.saveWeather(weatherData);
        }

        // Step 3: Evaluate outdoor suitability score and present results
        int score = scoreCalculator.calculateScore(weatherData);
        boolean suitableForOutdoor = scoreCalculator.isSuitableForOutdoor(score);

        WeatherOutputData outputData = new WeatherOutputData(
                weatherData, score, suitableForOutdoor, loadedFromCache);
        weatherPresenter.prepareSuccessView(outputData);
    }
}