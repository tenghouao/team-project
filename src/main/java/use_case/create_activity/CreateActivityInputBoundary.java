package use_case.create_activity;

/**
 * Input boundary for Use Case 1 (Create an event).
 */
public interface CreateActivityInputBoundary {

    /**
     * Validates the details and creates a new one-time activity.
     *
     * @param inputData the details entered by the user
     */
    void execute(CreateActivityInputData inputData);
}
