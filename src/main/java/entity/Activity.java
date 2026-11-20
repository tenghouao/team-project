package entity;

import java.util.UUID;

/**
 * Core entity representing a single user activity (either one-time or recurring).
 */
public class Activity {

    /** Category constant for outdoor sports activities. */
    public static final String OUTDOOR_SPORT = "OUTDOOR_SPORT";

    /** Category constant for outdoor leisure activities. */
    public static final String OUTDOOR_LEISURE = "OUTDOOR_LEISURE";

    /** Category constant for indoor activities. */
    public static final String INDOOR = "INDOOR";

    /** Sentinel value indicating that a flexible activity has no fixed start hour. */
    public static final int FLEXIBLE_START_HOUR = -1;

    private final String id;
    private final String name;
    private final String category;
    private final boolean isFixed;
    private final int fixedStartHour;
    private final int durationHours;
    private final String date;
    private final City city;

    /**
     * Constructs an Activity with an explicit ID (for editing or loading from storage).
     *
     * @param id             unique identifier; auto-generated if null or blank
     * @param name           the display name of the activity
     * @param category       category constant (OUTDOOR_SPORT, OUTDOOR_LEISURE, or INDOOR)
     * @param isFixed        true if locked to a fixed start hour, false if flexible
     * @param fixedStartHour fixed start hour (0-23); set to -1 when isFixed is false
     * @param durationHours  duration of the activity in whole hours (must be >= 1)
     * @param date           "YYYY-MM-DD" for one-time events, or "" for recurring routines
     * @param city           the City associated with this activity
     */
    public Activity(String id, String name, String category, boolean isFixed,
                    int fixedStartHour, int durationHours, String date, City city) {
        if (id != null && !id.trim().isEmpty()) {
            this.id = id.trim();
        } else {
            this.id = UUID.randomUUID().toString();
        }

        if (name != null) {
            this.name = name.trim();
        } else {
            this.name = "";
        }

        if (category != null) {
            this.category = category.trim();
        } else {
            this.category = INDOOR;
        }

        this.isFixed = isFixed;
        if (isFixed) {
            this.fixedStartHour = fixedStartHour;
        } else {
            this.fixedStartHour = FLEXIBLE_START_HOUR;
        }

        this.durationHours = durationHours;

        if (date != null) {
            this.date = date.trim();
        } else {
            this.date = "";
        }

        this.city = city;
    }

    /**
     * Constructs a brand-new Activity with an automatically generated unique ID.
     *
     * @param name           the display name of the activity
     * @param category       category constant (OUTDOOR_SPORT, OUTDOOR_LEISURE, or INDOOR)
     * @param isFixed        true if locked to a fixed start hour, false if flexible
     * @param fixedStartHour fixed start hour (0-23); set to -1 when isFixed is false
     * @param durationHours  duration of the activity in whole hours (must be >= 1)
     * @param date           "YYYY-MM-DD" for one-time events, or "" for recurring routines
     * @param city           the City associated with this activity
     */
    public Activity(String name, String category, boolean isFixed,
                    int fixedStartHour, int durationHours, String date, City city) {
        this(UUID.randomUUID().toString(), name, category, isFixed,
                fixedStartHour, durationHours, date, city);
    }

    /**
     * Returns the unique identifier of this activity.
     *
     * @return the activity ID string
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this activity.
     *
     * @return the activity name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category of this activity.
     *
     * @return the category constant string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Indicates whether this activity is locked to a fixed start time.
     *
     * @return true if the activity has a fixed start hour, false if it is flexible
     */
    public boolean isFixed() {
        return isFixed;
    }

    /**
     * Returns the fixed start hour of this activity.
     *
     * @return the fixed start hour (0-23), or -1 if this activity is flexible
     */
    public int getFixedStartHour() {
        return fixedStartHour;
    }

    /**
     * Returns the duration of this activity in whole hours.
     *
     * @return the duration in hours
     */
    public int getDurationHours() {
        return durationHours;
    }

    /**
     * Returns the specific calendar date for a one-time activity.
     *
     * @return the date in "YYYY-MM-DD" format, or "" for recurring routine activities
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the City associated with this activity.
     *
     * @return the City enum constant
     */
    public City getCity() {
        return city;
    }
}