package interface_adapter.schedule;

import entity.WeatherData;
import use_case.schedule.ScheduleOutputBoundary;
import use_case.schedule.ScheduleOutputData;
import use_case.weather.WeatherOutputBoundary;
import use_case.weather.WeatherOutputData;

/**
 * Presenter implementing both WeatherOutputBoundary (UC 6) and ScheduleOutputBoundary (UC 8).
 * Formats results into ScheduleState and notifies observing Views via ScheduleViewModel.
 */
public class SchedulePresenter implements WeatherOutputBoundary, ScheduleOutputBoundary {

    private final ScheduleViewModel viewModel;

    /**
     * Constructs a SchedulePresenter bound to the given ScheduleViewModel.
     *
     * @param viewModel the ViewModel updated by this Presenter
     */
    public SchedulePresenter(ScheduleViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Updates the ViewModel state with evaluated weather metrics and fires a weather event.
     *
     * @param outputData the evaluated weather output bundle from Use Case 6
     */
    @Override
    public void prepareSuccessView(WeatherOutputData outputData) {
        ScheduleState state = viewModel.getState();
        WeatherData weather = outputData.getWeatherData();

        state.setCity(weather.getCity());
        state.setDate(weather.getDate());
        state.setWeatherData(weather);
        state.setSuitabilityScore(outputData.getSuitabilityScore());
        state.setSuitableForOutdoor(outputData.isSuitableForOutdoor());
        state.setLoadedFromCache(outputData.isLoadedFromCache());
        state.setWeatherAvailable(true);
        state.setWeatherSummary(buildWeatherSummary(outputData));
        state.setWarningMessage(null);
        state.setErrorMessage(null);

        viewModel.firePropertyChanged(ScheduleViewModel.WEATHER_PROPERTY);
    }

    /**
     * Updates the ViewModel state with the generated schedule and fires a schedule event.
     *
     * @param outputData the generated schedule output bundle from Use Case 8
     */
    @Override
    public void prepareSuccessView(ScheduleOutputData outputData) {
        ScheduleState state = viewModel.getState();

        state.setCity(outputData.getDailySchedule().getCity());
        state.setDate(outputData.getDailySchedule().getDate());
        state.setDailySchedule(outputData.getDailySchedule());
        state.setSuitabilityScore(outputData.getSuitabilityScore());
        state.setSuitableForOutdoor(outputData.isOutdoorAllowed());
        state.setWeatherAvailable(outputData.isWeatherAvailable());
        state.setErrorMessage(null);

        if (!outputData.isWeatherAvailable()) {
            state.setWarningMessage(
                    "Weather service unavailable. Schedule generated in indoor-only safe mode.");
        } else {
            state.setWarningMessage(null);
        }

        viewModel.firePropertyChanged(ScheduleViewModel.SCHEDULE_PROPERTY);
    }

    /**
     * Records an error message in the ViewModel state and fires an error event.
     *
     * @param errorMessage the human-readable error message to present
     */
    @Override
    public void prepareFailView(String errorMessage) {
        ScheduleState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        viewModel.firePropertyChanged(ScheduleViewModel.ERROR_PROPERTY);
    }

    private String buildWeatherSummary(WeatherOutputData outputData) {
        WeatherData weather = outputData.getWeatherData();
        String sourceTag;
        if (outputData.isLoadedFromCache()) {
            sourceTag = "Cached";
        } else {
            sourceTag = "Live";
        }

        String recommendation;
        if (outputData.isSuitableForOutdoor()) {
            recommendation = "Outdoor OK";
        } else {
            recommendation = "Indoor Recommended";
        }

        return weather.getCity().getDisplayName()
                + " (" + weather.getDate() + "): "
                + weather.getCondition()
                + ", " + weather.getTemperature() + " C, "
                + "Precip " + weather.getPrecipitation() + " mm, "
                + "Wind " + weather.getWindSpeed() + " km/h | Score: "
                + outputData.getSuitabilityScore() + "/100 ("
                + recommendation + ") [" + sourceTag + "]";
    }
}