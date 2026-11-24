package use_case.edit_activity;

/**
 * Output boundary for Use Case 2 (Edit an event).
 * Implemented by Rock's presenter.
 */
public interface EditActivityOutputBoundary {

    /**
     * Called by loadActivity with the activity's current details,
     * so the UI can show a pre-filled edit form.
     *
     * @param outputData the current details of the activity
     */
    void prepareEditFormView(EditActivityOutputData outputData);

    /**
     * Called when the activity was updated and saved.
     *
     * @param outputData the updated details of the activity
     */
    void prepareSuccessView(EditActivityOutputData outputData);

    /**
     * Called when the activity does not exist, the new details are invalid,
     * or the change could not be saved.
     *
     * @param errorMessage an explanation of what went wrong
     */
    void prepareFailView(String errorMessage);

    /**
     * Called when the new time overlaps an existing fixed-time activity.
     * The UI should let the user change the time, or confirm and resubmit
     * with overlapConfirmed set to true.
     *
     * @param warningMessage a description of the overlapping activities
     */
    void prepareOverlapWarningView(String warningMessage);
}
