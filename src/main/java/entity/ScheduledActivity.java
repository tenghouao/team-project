package entity;

/**
 * Core entity representing an Activity assigned to a concrete hourly time slot.
 */
public class ScheduledActivity {

    private final Activity activity;
    private final int startHour;
    private final int endHour;

    /**
     * Constructs a ScheduledActivity with its assigned start and end hours.
     *
     * @param activity  the Activity entity being scheduled
     * @param startHour the assigned start hour (0-23)
     * @param endHour   the assigned end hour (1-24)
     */
    public ScheduledActivity(Activity activity, int startHour, int endHour) {
        this.activity = activity;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    /**
     * Returns the Activity entity assigned to this slot.
     *
     * @return the scheduled Activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Returns the assigned start hour of this activity.
     *
     * @return the start hour (0-23)
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Returns the assigned end hour of this activity.
     *
     * @return the end hour (1-24)
     */
    public int getEndHour() {
        return endHour;
    }
}