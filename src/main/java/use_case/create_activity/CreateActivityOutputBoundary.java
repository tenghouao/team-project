package use_case.create_activity;

/**
 * Output boundary for Use Case 1 (Create an event).
 * Implemented by Rock's presenter.
 */
public interface CreateActivityOutputBoundary {

    /**
     * Called when the activity was created and saved.
     *
     * @param outputData the details of the created activity
     */
    void prepareSuccessView(CreateActivityOutputData outputData);

    /**
     * Called when the details are invalid or the activity could not be saved.
     *
     * @param errorMessage an explanation of what went wrong
     */
    void prepareFailView(String errorMessage);

    /**
     * Called when the new activity overlaps an existing fixed-time activity.
     * The UI should let the user change the time, or confirm and resubmit
     * with overlapConfirmed set to true.
     *
     * @param warningMessage a description of the overlapping activities
     */
    void prepareOverlapWarningView(String warningMessage);
}
