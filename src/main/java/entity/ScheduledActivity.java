package entity;

/**
 * Represents an activity that has been assigned a specific time slot and weather suitability score.
 */
public class ScheduledActivity {

    private final Activity activity;
    private final int assignedStartHour;     // e.g., 10 (meaning 10:00)
    private final int assignedEndHour;       // e.g., 12 (meaning 12:00)
    private final int suitabilityScore;      // 0 to 100
    private final String explanation;        // e.g., "Score 85: Dry and mild (18°C)"

    public ScheduledActivity(Activity activity, int assignedStartHour, int assignedEndHour,
                             int suitabilityScore, String explanation) {
        this.activity = activity;
        this.assignedStartHour = assignedStartHour;
        this.assignedEndHour = assignedEndHour;
        this.suitabilityScore = suitabilityScore;
        this.explanation = explanation;
    }

    public Activity getActivity() {
        return activity;
    }

    public int getAssignedStartHour() {
        return assignedStartHour;
    }

    public int getAssignedEndHour() {
        return assignedEndHour;
    }

    public int getSuitabilityScore() {
        return suitabilityScore;
    }

    public String getExplanation() {
        return explanation;
    }
}