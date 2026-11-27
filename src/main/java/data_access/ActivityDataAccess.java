package data_access;

import entity.Activity;
import entity.RecurringRoutine;
import use_case.activity.ActivityDataAccessInterface;
import org.json.*;

import java.util.List;

public class ActivityDataAccess implements ActivityDataAccessInterface {
    @Override
    public boolean saveActivity(Activity activity) {
        return false;
    }

    @Override
    public boolean saveRecurringActivity(Activity activity, List<String> daysOfWeek) {
        return false;
    }

    @Override
    public boolean updateActivity(Activity activity, List<String> daysOfWeek) {
        return false;
    }

    @Override
    public boolean deleteActivityById(String activityId) {
        return false;
    }

    @Override
    public Activity getActivityById(String activityId) {
        return null;
    }

    @Override
    public List<Activity> getAllOneTimeActivities() {
        return List.of();
    }

    @Override
    public List<String> getRecurringDays(String activityId) {
        return List.of();
    }

    @Override
    public RecurringRoutine getRoutineByDay(String dayOfWeek) {
        return null;
    }

    @Override
    public List<Activity> getEventsByDate(String date) {
        return List.of();
    }
}
