package use_case.delete_activity;

import entity.Activity;
import use_case.activity.ActivityDataAccessInterface;

import java.util.List;

/**
 * Interactor for Use Case 3 (Delete an event).
 */
public class DeleteActivityInteractor implements DeleteActivityInputBoundary {

    private final ActivityDataAccessInterface activityDataAccess;
    private final DeleteActivityOutputBoundary presenter;

    /**
     * Constructs a DeleteActivityInteractor.
     *
     * @param activityDataAccess the data access object used to find and delete activities
     * @param presenter          the output boundary that receives the result
     */
    public DeleteActivityInteractor(ActivityDataAccessInterface activityDataAccess,
                                    DeleteActivityOutputBoundary presenter) {
        this.activityDataAccess = activityDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(DeleteActivityInputData inputData) {
        String activityId = inputData.getActivityId();
        if (activityId == null || activityId.trim().isEmpty()) {
            presenter.prepareFailView("No event is selected.");
            return;
        }

        // Precondition: the event already exists.
        Activity activity = activityDataAccess.getActivityById(activityId);
        if (activity == null) {
            presenter.prepareFailView("This event no longer exists.");
            return;
        }

        List<String> recurringDays = activityDataAccess.getRecurringDays(activityId);
        DeleteActivityOutputData outputData = new DeleteActivityOutputData(
                activity.getId(), activity.getName(), activity.getDate(), recurringDays);

        // Step 2 / Extension 2a: ask the user to confirm before deleting.
        if (!inputData.isConfirmed()) {
            presenter.prepareConfirmationView(outputData, buildConfirmationMessage(outputData));
            return;
        }

        // Step 4 / Extension 4a: remove the event.
        if (!activityDataAccess.deleteActivityById(activityId)) {
            presenter.prepareFailView("The event could not be deleted. Please try again.");
            return;
        }

        // Step 5: show the updated schedule.
        presenter.prepareSuccessView(outputData);
    }

    private String buildConfirmationMessage(DeleteActivityOutputData outputData) {
        String question = "Delete \"" + outputData.getName() + "\"?";
        if (outputData.isRecurring()) {
            return question + " Every occurrence of this event ("
                    + String.join(", ", outputData.getRecurringDays()) + ") will be deleted.";
        }
        return question;
    }
}
