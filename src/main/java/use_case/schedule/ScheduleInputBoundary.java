package use_case.schedule;

/**
 * Input boundary interface for invoking Use Case 8 (Generate Weather-Aware Daily Schedule).
 */
public interface ScheduleInputBoundary {

    /**
     * Generates a conflict-free, weather-aware daily schedule.
     *
     * @param inputData the target city, date, and day of the week
     */
    void execute(ScheduleInputData inputData);
}