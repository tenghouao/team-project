package use_case.delete_activity;

/**
 * Input boundary for Use Case 3 (Delete an event).
 */
public interface DeleteActivityInputBoundary {

    /**
     * Asks the user to confirm the deletion, or deletes the activity if already confirmed.
     *
     * @param inputData the ID of the activity and whether the deletion is confirmed
     */
    void execute(DeleteActivityInputData inputData);
}