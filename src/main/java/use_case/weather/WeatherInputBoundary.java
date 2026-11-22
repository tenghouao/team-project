package use_case.weather;

/**
 * Input boundary interface for invoking Use Case 6 (Fetch and Evaluate Daily Weather).
 */
public interface WeatherInputBoundary {

    /**
     * Executes cache-first weather retrieval, persistence, and suitability scoring.
     *
     * @param inputData the requested city, date, and refresh flag
     */
    void execute(WeatherInputData inputData);
}