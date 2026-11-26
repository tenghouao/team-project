package data_access;

import entity.City;
import entity.WeatherData;
import use_case.weather.WeatherAPIDataAccessInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Data Access Object that fetches real daily weather forecasts from Open-Meteo
 * using coordinates stored in the City enum and standard Java 8 networking classes.
 */
public class OpenMeteoWeatherDAO implements WeatherAPIDataAccessInterface {

    private static final int TIMEOUT_MS = 5000;
    private static final int HTTP_OK = 200;

    /**
     * Constructs an OpenMeteoWeatherDAO instance.
     */
    public OpenMeteoWeatherDAO() {
    }

    /**
     * Fetches daily weather forecast metrics from Open-Meteo for the specified City and date.
     *
     * @param city the target City enum providing latitude and longitude
     * @param date the target date in "YYYY-MM-DD" format
     * @return a populated WeatherData entity, or null if the request fails or data is invalid
     */
    @Override
    public WeatherData fetchWeatherFromApi(City city, String date) {
        if (city == null || date == null || date.trim().isEmpty()) {
            return null;
        }

        String cleanDate = date.trim();
        try {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                    + city.getLatitude()
                    + "&longitude=" + city.getLongitude()
                    + "&daily=temperature_2m_max,precipitation_sum,wind_speed_10m_max,weather_code"
                    + "&timezone=auto&start_date=" + cleanDate
                    + "&end_date=" + cleanDate;

            String weatherResponse = sendGetRequest(weatherUrl);
            if (weatherResponse == null || !weatherResponse.contains("\"daily\":")) {
                return null;
            }

            String dailySection = weatherResponse.substring(
                    weatherResponse.indexOf("\"daily\":"));
            double temperature = extractNumber(dailySection, "\"temperature_2m_max\":[");
            double precipitation = extractNumber(dailySection, "\"precipitation_sum\":[");
            double windSpeed = extractNumber(dailySection, "\"wind_speed_10m_max\":[");
            int weatherCode = (int) extractNumber(dailySection, "\"weather_code\":[");

            String condition = translateWeatherCode(weatherCode);

            return new WeatherData(
                    cleanDate, city, temperature, precipitation, windSpeed, condition);
        } catch (Exception e) {
            return null;
        }
    }

    private String sendGetRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(TIMEOUT_MS);
        connection.setReadTimeout(TIMEOUT_MS);

        int status = connection.getResponseCode();
        if (status != HTTP_OK) {
            connection.disconnect();
            return null;
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            response.append(line);
            line = reader.readLine();
        }
        reader.close();
        connection.disconnect();
        return response.toString();
    }

    private double extractNumber(String json, String key) {
        int startIndex = json.indexOf(key);
        if (startIndex == -1) {
            return 0.0;
        }
        startIndex = startIndex + key.length();
        int endIndex = startIndex;
        while (endIndex < json.length()) {
            char c = json.charAt(endIndex);
            if ((c >= '0' && c <= '9') || c == '.' || c == '-') {
                endIndex++;
            } else {
                break;
            }
        }
        if (endIndex == startIndex) {
            return 0.0;
        }
        return Double.parseDouble(json.substring(startIndex, endIndex));
    }

    private String translateWeatherCode(int code) {
        if (code == 0) {
            return "Clear sky";
        } else if (code >= 1 && code <= 3) {
            return "Partly cloudy";
        } else if (code >= 45 && code <= 48) {
            return "Foggy";
        } else if (code >= 51 && code <= 67) {
            return "Rain";
        } else if (code >= 71 && code <= 77) {
            return "Snow";
        } else if (code >= 80 && code <= 82) {
            return "Rain showers";
        } else if (code >= 95) {
            return "Thunderstorm";
        } else {
            return "Cloudy";
        }
    }
}