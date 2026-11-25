package use_case.create_recurring_activity;

/**
 * Input boundary for Use Case 4 (Create a recurring event).
 */
public interface CreateRecurringActivityInputBoundary {

    /**
     * Validates the details and creates an activity that repeats every week
     * on the selected days.
     *
     * @param inputData the details entered by the user
     */
    void execute(CreateRecurringActivityInputData inputData);
}