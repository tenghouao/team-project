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

### Use Case 6: Receive weather information

### Use Case 7: Save received weather information

### Use Case 8: Daily schedule suggestion based on weather information

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
