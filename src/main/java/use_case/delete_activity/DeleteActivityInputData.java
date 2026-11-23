package use_case.delete_activity;

/**
 * Input data for Use Case 3 (Delete an event).
 */
public class DeleteActivityInputData {

    private final String activityId;
    private final boolean confirmed;

    /**
     * Constructs a DeleteActivityInputData bundle.
     *
     * @param activityId the ID of the activity to delete
     * @param confirmed  false to ask the user for confirmation first,
     *                   true once the user has confirmed the deletion
     */
    public DeleteActivityInputData(String activityId, boolean confirmed) {
        this.activityId = activityId;
        this.confirmed = confirmed;
    }

    /**
     * Returns the ID of the activity to delete.
     *
     * @return the activity ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Returns whether the user has confirmed the deletion.
     *
     * @return true if confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}