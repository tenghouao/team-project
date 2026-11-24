package use_case.delete_activity;

/**
 * Output boundary for Use Case 3 (Delete an event).
 * Implemented by Rock's presenter.
 */
public interface DeleteActivityOutputBoundary {

    /**
     * Called when the activity was deleted.
     *
     * @param outputData the details of the deleted activity
     */
    void prepareSuccessView(DeleteActivityOutputData outputData);

    /**
     * Called when the activity does not exist or could not be deleted.
     *
     * @param errorMessage an explanation of what went wrong
     */
    void prepareFailView(String errorMessage);

    /**
     * Called before deleting, so the UI can ask the user to confirm.
     * If the user confirms, the UI should call the input boundary again
     * with confirmed set to true; if the user cancels, nothing else is needed.
     *
     * @param outputData the details of the activity about to be deleted
     * @param message    the confirmation question to show, e.g. "Delete \"Gym\"?"
     */
    void prepareConfirmationView(DeleteActivityOutputData outputData, String message);
}
