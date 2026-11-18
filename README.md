# WeatherWise

## Team Information
TUT0301 Group 28 - BA3175
### Team Members
- Chihiro Mori
- Teng Hou Ao
- Tobey Zhao
- Rock Qiu

---

## Project Description

### Productivity

WeatherWise is a Java to-do list/calendar application combined with weather forecasts functionality to allow users to plan/schedule better with the provided weather information.

---

## User Stories

As a user, I want to see my events and the hourly weather forecast for a selected day so that I can plan that day.

As a user, I want to create an event with a name, a date, a category (indoor or outdoor), an optional location, and either a fixed time range or a flexible duration so that I can plan my schedule.

As a user, I want to edit the name, date, time, category, and location of an event so that I can change my schedule.

As a user, I want to delete an event so that I can remove plans that are no longer happening.

As a user, I want to create an event that repeats on selected days of the week so that I don't need to re-enter my routine.

As a user, I want my events to be saved so that I don't need to re-enter them every time I use the application.

As a user, I want to see whether the weather is suitable for an event based on its date, time, and location so that I can decide whether to keep or move it.

As a user, I want the application to suggest a daily schedule that places my flexible events in time slots with suitable weather so that I can avoid planning outdoor activities during bad weather.

---

## Use Cases

### Use Case 1: Create an event
**Primary actor:** User

**Preconditions:**
* None. The user can create an event at any time.

**Trigger:** The user asks the system to create a new event.

**Success outcome:** The new event is recorded with its name, date, time, category, and optional location, and it appears in the user's schedule for that date.

**Main success scenario:**
1. The user asks to create a new event.
2. The user enters the event's name, date, start and end time, category (indoor or outdoor), and optionally a location.
3. The system checks that the details are valid.
4. The system records the new event.
5. The system shows the new event in the user's schedule for that date.

**Extensions:**
* **2a.** The user does not have a fixed time for the event (a flexible event).
    1. The user enters a duration instead of a start and end time.
    2. The use case continues at step 3. The event will be placed in a time slot when a daily schedule is generated (Use Case 8).
* **3a.** A required field is missing, or the end time is not after the start time.
    1. The system explains which details are invalid.
    2. The user corrects them, and the use case continues at step 3.
* **3b.** The event's time overlaps with an existing fixed-time event.
    1. The system warns the user about the overlap.
    2. The user either changes the time (continue at step 3) or confirms that the event should be kept as is (continue at step 4).
* **4a.** The system cannot record the event.
    1. The system explains that the event was not created and that the user may try again.
    2. The use case ends with no new event.

### Use Case 2: Edit an event
**Primary actor:** User

**Preconditions:**
* The event already exists.
* The user is viewing the event in their schedule.

**Trigger:** The user asks the system to edit the selected event.

**Success outcome:** The event's updated details are recorded and the schedule shows the changes.

**Main success scenario:**
1. The user asks to edit the event.
2. The system shows the event's current details.
3. The user changes one or more of the name, date, time, category, or location.
4. The system checks that the new details are valid.
5. The system records the updated event.
6. The system shows the updated event in the schedule.

**Extensions:**
* **2a.** The event is a recurring event.
    1. The system shows the recurrence settings (Use Case 4) instead of a single date.
    2. Changes apply to every occurrence of the event, and the use case continues at step 3.
* **3a.** The user cancels the edit.
    1. The system keeps the original details, and the use case ends.
* **4a.** A required field is missing, or the end time is not after the start time.
    1. The system explains which details are invalid.
    2. The user corrects them, and the use case continues at step 4.
* **4b.** The new time overlaps with an existing fixed-time event.
    1. The system warns the user about the overlap.
    2. The user either changes the time (continue at step 4) or confirms the change (continue at step 5).
* **5a.** The system cannot record the change.
    1. The system explains that the event was not updated and that the user may try again.
    2. The use case ends with the original event unchanged.

### Use Case 3: Delete an event
**Primary actor:** User

**Preconditions:**
* The event already exists.
* The user is viewing the event in their schedule.

**Trigger:** The user asks the system to delete the selected event.

**Success outcome:** The event is removed and no longer appears in the user's schedule.

**Main success scenario:**
1. The user asks to delete the event.
2. The system asks the user to confirm the deletion.
3. The user confirms.
4. The system removes the event.
5. The system shows the updated schedule without the event.

**Extensions:**
* **2a.** The event is a recurring event.
    1. The system tells the user that every occurrence of the event will be deleted.
    2. The use case continues at step 3.
* **3a.** The user cancels the deletion.
    1. The system keeps the event, and the use case ends.
* **4a.** The system cannot remove the event.
    1. The system explains that the event was not deleted and that the user may try again.
    2. The use case ends with the event unchanged.

### Use Case 4: Create a recurring event
**Primary actor:** User

**Preconditions:**
* None. The user can create a recurring event at any time.

**Trigger:** The user asks the system to create an event that repeats every week.

**Success outcome:** The recurring event is recorded and appears in the user's schedule on every selected day of the week, so the user does not need to re-enter it.

**Main success scenario:**
1. The user asks to create a recurring event.
2. The user enters the event's name, start and end time, category (indoor or outdoor), and optionally a location.
3. The user selects the days of the week the event repeats on, a start date, and optionally an end date.
4. The system checks that the details are valid.
5. The system records the recurring event.
6. The system shows the event on each matching day in the user's schedule.

**Extensions:**
* **2a.** The user does not have a fixed time for the event (a flexible event).
    1. The user enters a duration instead of a start and end time.
    2. The use case continues at step 3. On each matching day, the event will be placed in a time slot when a daily schedule is generated (Use Case 8).
* **4a.** No day of the week is selected, a required field is missing, the end time is not after the start time, or the end date is before the start date.
    1. The system explains which details are invalid.
    2. The user corrects them, and the use case continues at step 4.
* **4b.** On one or more of the selected days, the event overlaps with an existing fixed-time event.
    1. The system warns the user and lists the conflicting dates.
    2. The user either changes the details (continue at step 4) or confirms (continue at step 5).
* **5a.** The system cannot record the recurring event.
    1. The system explains that the event was not created and that the user may try again.
    2. The use case ends with no new event.

### Use Case 5: Save created events
**Primary actor:** The application

**Preconditions:**
* The newly saved activities are not the same as the previously saved activities.

**Trigger:** The application closes / An activity is created/modified/edited (need to decide)

**Success outcome:** All saved activities remain after the application closes and reopens.

**Main success scenario:**
1. The trigger event occurs.
2. All activities and their properties are saved in a json file.
3. Application closes.
4. Application reopens.
5. Saved activities remain in the application.

### Use Case 6: Fetch and Evaluate Daily Weather Data
* **Name:** Fetch and Evaluate Daily Weather Data
* **Primary actor:** User
* **Preconditions:** The user has provided a valid target date and city name.
* **Trigger:** The user requests to check weather suitability or generate a daily schedule for a specific date and city.
* **Success outcome:** The system obtains the weather data for the specified location and date (either from local saved data or by API),
          saves newly fetched data locally, and calculates an outdoor suitability score for schedule generation.
* **Main success scenario:**
    1. The user enters a target date and city name (and optionally selects whether to force a live weather refresh).
    2. The system first checks local storage to see if weather data for the requested city and date has already been saved.
    3. If no saved data exists locally (or if a refresh is requested), the system requests forecasted weather data from the external weather service.
    4. The external weather service returns the weather conditions, and the system triggers Use Case 7 to save this received weather data locally for future reuse.
    5. The system evaluates the weather metrics and calculates a numerical suitability score indicating whether conditions are suitable for outdoor activities.
* **Extensions:**
    * *Step 2a (Local cached data found):* Saved weather data for the same city and date already exists locally and no refresh was requested. 
             The system loads the saved weather data directly, skipping Steps 3 and 4 to avoid redundant API calls.
    * *Step 3a (Network or service failure):* The external weather service is unreachable.
             The system notifies the user of the connection issue and falls back to an indoor-only scheduling mode.
    * *Step 3b (Invalid city name):* The external service cannot find the requested city. The system prompts the user to enter a valid city name.

### Use Case 7: Save received weather information
**Primary actor:** The application

**Preconditions:**
* The received weather information is not already stored.

**Trigger:** The application receives weather information from the api.

**Success outcome:** The application saves the received weather information to be used when needed without creating another api call.

**Main success scenario:**
1. Weather information is received.
2. Weather information is saved in a json? file.
3. When the weather information is needed, the application can retrieve it.

### Use Case 8: Generate Weather-Aware Daily Schedule
* **Name:** Generate Weather-Aware Daily Schedule
* **Primary actor:** User
* **Preconditions:** The user has saved a recurring routine or list of activities for the selected day of the week, 
          and the weather suitability score for the target date and city has been evaluated.
* **Trigger:** The user initiates daily schedule generation after weather data has been fetched and scored.
* **Success outcome:** The system generates a chronological hourly schedule where fixed-time activities are locked into their designated hours,
          eligible flexible activities are placed into remaining free slots, 
          and outdoor flexible activities are automatically filtered out into an unscheduled list if the weather score is unfavorable.
* **Main success scenario:**
    1. The system retrieves the user's saved activities for the selected day of the week.
    2. The system retrieves the evaluated weather data and suitability score for the target date.
    3. The system places all fixed-time activities into their exact designated hourly time slots.
    4. The system checks each flexible activity's category against the weather suitability score;
             if the weather score is below the safe threshold, outdoor activities are marked as unscheduled due to poor weather.
    5. The system allocates the remaining eligible flexible activities into available contiguous hourly slots based on their required duration.
    6. The system presents the completed hourly timeline alongside a list of any unscheduled activities to the user.
* **Extensions:**
    * *Step 3a (Fixed time conflict):* Two fixed-time activities overlap in the same hourly slot.
             The system alerts the user of the conflict so they can edit their events.
    * *Step 5a (Insufficient free time):* A flexible activity requires more consecutive hours than are available in the day.
             The system places the activity into the unscheduled list and displays the remaining schedule.

---

## Minimal Viable Product (MVP)

| Team Member | MVP Use Case |
|-------------|--------------|
| Teng Hou Ao | 5, 7 |
| Chihiro Mori | 1, 2, 3, 4 |
| Tobey Zhao | 6, 8 |
| Rock Qiu | UI |

---

## Entities

### Activity

---

## API Usage

**API Name:** Open-Meteo

**Documentation:** https://open-meteo.com/en/docs

**How it is used in the program:**  To receive weather information of a specific location at a specific date

---

## Design Diagrams

### UML Class Diagram


### Sequence Diagrams

---

## How to Run

[Instructions for running the program.]

---

## Notes

[Any additional project information.]
