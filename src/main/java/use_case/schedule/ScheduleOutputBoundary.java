package use_case.schedule;

/**
 * Output boundary interface for presenting Use Case 8 results.
 */
public interface ScheduleOutputBoundary {

    /**
     * Presents the generated daily schedule.
     *
     * @param outputData the generated schedule output bundle
     */
    void prepareSuccessView(ScheduleOutputData outputData);

    /**
     * Presents an error message when input is invalid or no activities exist for the day.
     *
     * @param errorMessage a human-readable explanation of the failure
     */
    void prepareFailView(String errorMessage);
}