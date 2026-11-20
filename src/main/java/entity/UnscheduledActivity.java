package entity;

/**
 * Wrapper entity pairing an unscheduled Activity with the reason it was not scheduled.
 */
public class UnscheduledActivity {

    private final Activity activity;
    private final String reason;

    /**
     * Constructs an UnscheduledActivity with the given activity and explanation.
     *
     * @param activity the activity that could not be placed into the daily schedule
     * @param reason   a human-readable explanation for why the activity was unscheduled
     */
    public UnscheduledActivity(Activity activity, String reason) {
        this.activity = activity;
        if (reason != null) {
            this.reason = reason;
        } else {
            this.reason = "Unable to schedule.";
        }
    }

    /**
     * Returns the activity that was not scheduled.
     *
     * @return the unscheduled Activity entity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Returns the reason why this activity could not be scheduled.
     *
     * @return the unscheduled reason text
     */
    public String getReason() {
        return reason;
    }
}