package entity;

/**
 * Supported cities with pre-defined coordinates for direct Open-Meteo forecast queries.
 */
public enum City {
    TORONTO("Toronto", 43.6532, -79.3832),
    MONTREAL("Montreal", 45.5017, -73.5673),
    OTTAWA("Ottawa", 45.4215, -75.6972);

    private final String displayName;
    private final double latitude;
    private final double longitude;

    /**
     * Constructs a City enum constant with its display name and geographic coordinates.
     *
     * @param displayName the human-readable name of the city
     * @param latitude    the geographic latitude of the city
     * @param longitude   the geographic longitude of the city
     */
    City(String displayName, double latitude, double longitude) {
        this.displayName = displayName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the human-readable display name of the city.
     *
     * @return the city display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the geographic latitude of the city.
     *
     * @return the latitude in degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the geographic longitude of the city.
     *
     * @return the longitude in degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Converts a stored string (such as "TORONTO" or "Toronto") back into a City enum.
     *
     * @param text the city name string to parse
     * @return the matching City enum constant, or null if no match is found
     */
    public static City fromString(String text) {
        if (text == null) {
            return null;
        }
        String trimmed = text.trim();
        City[] values = City.values();
        for (int i = 0; i < values.length; i++) {
            City c = values[i];
            if (c.name().equalsIgnoreCase(trimmed)
                    || c.displayName.equalsIgnoreCase(trimmed)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns the display name of the city for UI components such as dropdown menus.
     *
     * @return the city display name
     */
    @Override
    public String toString() {
        return displayName;
    }
}