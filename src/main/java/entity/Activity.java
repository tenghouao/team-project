package entity;

/**
 * Represents a user activity to be scheduled (either fixed-time or flexible).
 */
public class Activity {

    // Fixed constants for activity categories to avoid spelling mismatches across the team
    public static final String OUTDOOR_SPORT = "OUTDOOR_SPORT";
    public static final String LIGHT_OUTDOOR = "LIGHT_OUTDOOR";
    public static final String INDOOR = "INDOOR";

    private final String name;
    private final String category;
    private final int durationHours;      // Whole hours: e.g., 1, 2, or 3
    private final boolean isFixed;        // true = fixed time, false = flexible time
    private final int fixedStartHour;     // 8 to 21 (24-hour format), or -1 if flexible

    public Activity(String name, String category, int durationHours,
                    boolean isFixed, int fixedStartHour) {
        this.name = name;
        this.category = category;
        this.durationHours = durationHours;
        this.isFixed = isFixed;
        if (isFixed) {      // If the duration is not fix, assign a default -1 value
            this.fixedStartHour = fixedStartHour;
        } else {
            this.fixedStartHour = -1;
        }
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public int getFixedStartHour() {
        return fixedStartHour;
    }
}