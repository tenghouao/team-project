package use_case.edit_activity;

import entity.Activity;
import use_case.activity.ActivityDataAccessInterface;
import use_case.activity.ActivityRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for Use Case 2 (Edit an event).
 */
public class EditActivityInteractor implements EditActivityInputBoundary {

    private final ActivityDataAccessInterface activityDataAccess;
    private final EditActivityOutputBoundary presenter;

    /**
     * Constructs an EditActivityInteractor.
     *
     * @param activityDataAccess the data access object used to read and update activities
     * @param presenter          the output boundary that receives the result
     */
    public EditActivityInteractor(ActivityDataAccessInterface activityDataAccess,
                                  EditActivityOutputBoundary presenter) {
        this.activityDataAccess = activityDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void loadActivity(String activityId) {
        // Step 2 / Extension 2a: show the current details (and recurrence settings).
        Activity activity = findExisting(activityId);
        if (activity == null) {
            return;
        }
        List<String> days = activityDataAccess.getRecurringDays(activity.getId());
        presenter.prepareEditFormView(toOutputData(activity, days));
    }

    @Override
    public void execute(EditActivityInputData inputData) {
        // Precondition: the event already exists.
        Activity existing = findExisting(inputData.getActivityId());
        if (existing == null) {
            return;
        }
        boolean recurring = !activityDataAccess.getRecurringDays(existing.getId()).isEmpty();

        // Step 4 / Extension 4a: check that the new details are valid.
        List<String> errors = ActivityRules.validateDetails(
                inputData.getName(), inputData.getCategory(), inputData.isFixed(),
                inputData.getStartHour(), inputData.getEndHour(), inputData.getDurationHours());
        String whenError;
        if (recurring) {
            whenError = ActivityRules.validateDaysOfWeek(inputData.getDaysOfWeek());
        } else {
            whenError = ActivityRules.validateDate(inputData.getDate());
        }
        if (whenError != null) {
            errors.add(whenError);
        }
        if (!errors.isEmpty()) {
            presenter.prepareFailView(String.join("\n", errors));
            return;
        }

        int duration = ActivityRules.durationOf(inputData.isFixed(), inputData.getStartHour(),
                inputData.getEndHour(), inputData.getDurationHours());
        String date;
        List<String> days;
        if (recurring) {
            date = "";
            days = ActivityRules.normalizeDays(inputData.getDaysOfWeek());
        } else {
            date = inputData.getDate().trim();
            days = new ArrayList<>();
        }

        // Extension 4b: warn about overlaps, ignoring the activity being edited.
        if (inputData.isFixed() && !inputData.isOverlapConfirmed()) {
            String warning = findOverlapWarning(existing.getId(), recurring, date, days,
                    inputData.getStartHour(), duration);
            if (warning != null) {
                presenter.prepareOverlapWarningView(warning);
                return;
            }
        }

        // Step 5 / Extension 5a: record the updated activity, keeping the same ID.
        Activity updated = new Activity(existing.getId(), inputData.getName(),
                inputData.getCategory(), inputData.isFixed(), inputData.getStartHour(),
                duration, date, inputData.getCity());
        if (!activityDataAccess.updateActivity(updated, days)) {
            presenter.prepareFailView("The event could not be updated. Please try again.");
            return;
        }

        // Step 6: show the updated activity.
        presenter.prepareSuccessView(toOutputData(updated, days));
    }

    private Activity findExisting(String activityId) {
        if (activityId == null || activityId.trim().isEmpty()) {
            presenter.prepareFailView("No event is selected.");
            return null;
        }
        Activity activity = activityDataAccess.getActivityById(activityId);
        if (activity == null) {
            presenter.prepareFailView("This event no longer exists.");
        }
        return activity;
    }

    private String findOverlapWarning(String activityId, boolean recurring, String date,
                                      List<String> days, int startHour, int duration) {
        if (recurring) {
            return ActivityRules.checkOverlapsOnDays(activityDataAccess, days,
                    startHour, duration, activityId);
        }
        List<Activity> overlaps = ActivityRules.findOverlaps(activityDataAccess, date,
                ActivityRules.dayOfWeekOf(date), startHour, duration, activityId);
        if (overlaps.isEmpty()) {
            return null;
        }
        return ActivityRules.describeOverlaps(overlaps);
    }

    private EditActivityOutputData toOutputData(Activity activity, List<String> days) {
        return new EditActivityOutputData(activity.getId(), activity.getName(),
                activity.getCategory(), activity.isFixed(), activity.getFixedStartHour(),
                activity.getDurationHours(), activity.getDate(), days, activity.getCity());
    }
}