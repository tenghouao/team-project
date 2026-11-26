package use_case.weather;

/**
 * Output boundary interface for presenting Use Case 6 results.
 */
public interface WeatherOutputBoundary {

    /**
     * Presents the retrieved weather data and its evaluated suitability score.
     *
     * @param outputData the evaluated weather output bundle
     */
    void prepareSuccessView(WeatherOutputData outputData);

    /**
     * Presents an error message when input is invalid or the weather service is unreachable.
     *
     * @param errorMessage a human-readable error description
     */
    void prepareFailView(String errorMessage);
}