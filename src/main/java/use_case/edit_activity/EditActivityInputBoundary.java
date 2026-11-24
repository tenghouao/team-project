package use_case.edit_activity;

/**
 * Input boundary for Use Case 2 (Edit an event).
 */
public interface EditActivityInputBoundary {

    /**
     * Loads the current details of an activity so the UI can show the edit form (Step 2).
     *
     * @param activityId the ID of the activity to edit
     */
    void loadActivity(String activityId);

    /**
     * Validates the new details and updates the activity (Steps 3-6).
     *
     * @param inputData the new details entered by the user
     */
    void execute(EditActivityInputData inputData);
}
