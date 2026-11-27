package use_case.schedule;

import entity.DailySchedule;

/**
 * Output data bundle produced by Use Case 8 containing the generated daily schedule.
 */
public class ScheduleOutputData {

    private final DailySchedule dailySchedule;
    private final int suitabilityScore;
    private final boolean outdoorAllowed;
    private final boolean weatherAvailable;

    /**
     * Constructs a ScheduleOutputData bundle.
     *
     * @param dailySchedule    the generated DailySchedule entity
     * @param suitabilityScore the evaluated weather score (0 when weather is unavailable)
     * @param outdoorAllowed   true if flexible outdoor activities were permitted
     * @param weatherAvailable true if weather data was found, false if indoor fallback ran
     */
    public ScheduleOutputData(DailySchedule dailySchedule, int suitabilityScore,
                              boolean outdoorAllowed, boolean weatherAvailable) {
        this.dailySchedule = dailySchedule;
        this.suitabilityScore = suitabilityScore;
        this.outdoorAllowed = outdoorAllowed;
        this.weatherAvailable = weatherAvailable;
    }

    /**
     * Returns the generated DailySchedule entity.
     *
     * @return the DailySchedule containing scheduled and unscheduled activities
     */
    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    /**
     * Returns the weather suitability score used during scheduling.
     *
     * @return the 0-100 suitability score
     */
    public int getSuitabilityScore() {
        return suitabilityScore;
    }

    /**
     * Indicates whether outdoor flexible activities were allowed in this schedule.
     *
     * @return true if outdoor activities were allowed, false otherwise
     */
    public boolean isOutdoorAllowed() {
        return outdoorAllowed;
    }

    /**
     * Indicates whether weather data was available during schedule generation.
     *
     * @return true if weather data was present, false if indoor-only fallback mode was used
     */
    public boolean isWeatherAvailable() {
        return weatherAvailable;
    }
}