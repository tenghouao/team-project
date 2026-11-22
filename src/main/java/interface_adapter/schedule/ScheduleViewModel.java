package interface_adapter.schedule;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the weather and daily schedule screen.
 * Implements the Observer Pattern via PropertyChangeSupport so Swing Views auto-refresh.
 */
public class ScheduleViewModel {

    /** View identifier constant used by ViewManager navigation. */
    public static final String VIEW_NAME = "schedule";

    /** Property event name fired when weather data is updated. */
    public static final String WEATHER_PROPERTY = "weather";

    /** Property event name fired when a daily schedule is generated. */
    public static final String SCHEDULE_PROPERTY = "schedule";

    /** Property event name fired when an error occurs. */
    public static final String ERROR_PROPERTY = "error";

    private final PropertyChangeSupport support;
    private ScheduleState state;

    /**
     * Constructs a ScheduleViewModel with a default initial ScheduleState.
     */
    public ScheduleViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new ScheduleState();
    }

    /**
     * Returns the current ScheduleState held by this ViewModel.
     *
     * @return the current ScheduleState
     */
    public ScheduleState getState() {
        return state;
    }

    /**
     * Replaces the current ScheduleState held by this ViewModel.
     *
     * @param state the new ScheduleState to set
     */
    public void setState(ScheduleState state) {
        if (state != null) {
            this.state = state;
        }
    }

    /**
     * Registers a PropertyChangeListener (typically a Swing View) to observe state updates.
     *
     * @param listener the listener to notify when properties change
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Notifies all registered listeners that the specified property has changed.
     *
     * @param propertyName the name of the updated property constant
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }
}