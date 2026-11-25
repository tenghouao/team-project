package use_case.create_recurring_activity;

import entity.Activity;
import use_case.activity.ActivityDataAccessInterface;
import use_case.activity.ActivityRules;

import java.util.List;

/**
 * Interactor for Use Case 4 (Create a recurring event).
 */
public class CreateRecurringActivityInteractor implements CreateRecurringActivityInputBoundary {

    private final ActivityDataAccessInterface activityDataAccess;
    private final CreateRecurringActivityOutputBoundary presenter;

    /**
     * Constructs a CreateRecurringActivityInteractor.
     *
     * @param activityDataAccess the data access object used to read and save activities
     * @param presenter          the output boundary that receives the result
     */
    public CreateRecurringActivityInteractor(ActivityDataAccessInterface activityDataAccess,
                                             CreateRecurringActivityOutputBoundary presenter) {
        this.activityDataAccess = activityDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateRecurringActivityInputData inputData) {
        // Step 4 / Extension 4a: check that the details and selected days are valid.
        List<String> errors = ActivityRules.validateDetails(
                inputData.getName(), inputData.getCategory(), inputData.isFixed(),
                inputData.getStartHour(), inputData.getEndHour(), inputData.getDurationHours());
        String daysError = ActivityRules.validateDaysOfWeek(inputData.getDaysOfWeek());
        if (daysError != null) {
            errors.add(daysError);
        }
        if (!errors.isEmpty()) {
            presenter.prepareFailView(String.join("\n", errors));
            return;
        }

        List<String> days = ActivityRules.normalizeDays(inputData.getDaysOfWeek());
        int duration = ActivityRules.durationOf(inputData.isFixed(), inputData.getStartHour(),
                inputData.getEndHour(), inputData.getDurationHours());

        // Extension 4b: warn and list the days where the event overlaps a fixed-time event.
        if (inputData.isFixed() && !inputData.isOverlapConfirmed()) {
            String warning = ActivityRules.checkOverlapsOnDays(activityDataAccess, days,
                    inputData.getStartHour(), duration, null);
            if (warning != null) {
                presenter.prepareOverlapWarningView(warning);
                return;
            }
        }

        // Step 5 / Extension 5a: record the recurring activity (date "" means recurring).
        Activity activity = new Activity(inputData.getName(), inputData.getCategory(),
                inputData.isFixed(), inputData.getStartHour(), duration, "", inputData.getCity());
        if (!activityDataAccess.saveRecurringActivity(activity, days)) {
            presenter.prepareFailView("The recurring event could not be created. Please try again.");
            return;
        }

        // Step 6: show the event on each matching day.
        presenter.prepareSuccessView(new CreateRecurringActivityOutputData(
                activity.getId(), activity.getName(), activity.getCategory(),
                activity.isFixed(), activity.getFixedStartHour(), activity.getDurationHours(),
                days, activity.getCity()));
    }
}
