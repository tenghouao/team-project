package use_case.create_recurring_activity;

/**
 * Output boundary for Use Case 4 (Create a recurring event).
 * Implemented by Rock's presenter.
 */
public interface CreateRecurringActivityOutputBoundary {

    /**
     * Called when the recurring activity was created and saved.
     *
     * @param outputData the details of the created activity
     */
    void prepareSuccessView(CreateRecurringActivityOutputData outputData);

    /**
     * Called when the details are invalid or the activity could not be saved.
     *
     * @param errorMessage an explanation of what went wrong
     */
    void prepareFailView(String errorMessage);

    /**
     * Called when the activity overlaps an existing fixed-time activity on one or
     * more of the selected days. The UI should let the user change the details,
     * or confirm and resubmit with overlapConfirmed set to true.
     *
     * @param warningMessage the conflicting days and activities, one day per line
     */
    void prepareOverlapWarningView(String warningMessage);
}
