package src.tower;

import src.aircrafts.Flyable;
import src.coordinates.Coordinates;
import src.weather.WeatherProvider;

public class WeatherTower extends Tower {
    
    public String getWeather(Coordinates coordinates) {
        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        return weatherProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }

    public void registerToWeatherTower(Flyable flyable) {
        flyable.registerTower(this);
    }
}