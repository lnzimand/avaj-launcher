package src.weather;

import src.coordinates.Coordinates;

public class WeatherProvider {

    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() { }

    public static WeatherProvider getProvider() {
        
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {

        return weather[(int)(Math.random() * (coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLatitude()) % 4)];
    }
}