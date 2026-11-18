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

As a user, I want to see my events and the weather information of a selected day so that I can schedule that day. day/weekly?

As a user, I want to create events with a specific date, time range, and an optional location so that I can plan my schedule.

As a user, I want to edit the date, time range, and location of an event so that I can change my schedule.

As a user, I want to delete an event so that I can change my schedule.

As a user, I want to create an event with a recurring schedule so that I don't need to re-enter it.

As a user, I want my created events to be saved so I don't need to re-enter them every time I use the application.

As a user, I want to see the weather information of an event based on its date, time range, and location so that I can plan my event.

---

## Use Cases

### Use Case 1: Create an event

### Use Case 2: Edit an event

### Use Case 3: Delete an event

### Use Case 4: Create a recurring event

### Use Case 5: Save created events

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
