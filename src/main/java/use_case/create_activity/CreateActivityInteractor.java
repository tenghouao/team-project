package use_case.create_activity;

import entity.Activity;
import use_case.activity.ActivityDataAccessInterface;
import use_case.activity.ActivityRules;

import java.util.List;

/**
 * Interactor for Use Case 1 (Create an event).
 */
public class CreateActivityInteractor implements CreateActivityInputBoundary {

    private final ActivityDataAccessInterface activityDataAccess;
    private final CreateActivityOutputBoundary presenter;

    /**
     * Constructs a CreateActivityInteractor.
     *
     * @param activityDataAccess the data access object used to read and save activities
     * @param presenter          the output boundary that receives the result
     */
    public CreateActivityInteractor(ActivityDataAccessInterface activityDataAccess,
                                    CreateActivityOutputBoundary presenter) {
        this.activityDataAccess = activityDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateActivityInputData inputData) {
        // Step 3 / Extension 3a: check that the details are valid.
        List<String> errors = ActivityRules.validateDetails(
                inputData.getName(), inputData.getCategory(), inputData.isFixed(),
                inputData.getStartHour(), inputData.getEndHour(), inputData.getDurationHours());
        String dateError = ActivityRules.validateDate(inputData.getDate());
        if (dateError != null) {
            errors.add(dateError);
        }
        if (!errors.isEmpty()) {
            presenter.prepareFailView(String.join("\n", errors));
            return;
        }

        String date = inputData.getDate().trim();
        int duration = ActivityRules.durationOf(inputData.isFixed(), inputData.getStartHour(),
                inputData.getEndHour(), inputData.getDurationHours());

        // Extension 3b: warn about overlaps with existing fixed-time activities.
        if (inputData.isFixed() && !inputData.isOverlapConfirmed()) {
            List<Activity> overlaps = ActivityRules.findOverlaps(activityDataAccess, date,
                    ActivityRules.dayOfWeekOf(date), inputData.getStartHour(), duration, null);
            if (!overlaps.isEmpty()) {
                presenter.prepareOverlapWarningView(ActivityRules.describeOverlaps(overlaps));
                return;
            }
        }

        // Step 4 / Extension 4a: record the new activity.
        Activity activity = new Activity(inputData.getName(), inputData.getCategory(),
                inputData.isFixed(), inputData.getStartHour(), duration, date, inputData.getCity());
        if (!activityDataAccess.saveActivity(activity)) {
            presenter.prepareFailView("The event could not be created. Please try again.");
            return;
        }

        // Step 5: show the new activity.
        presenter.prepareSuccessView(new CreateActivityOutputData(
                activity.getId(), activity.getName(), activity.getCategory(),
                activity.isFixed(), activity.getFixedStartHour(), activity.getDurationHours(),
                activity.getDate(), activity.getCity()));
    }
}

