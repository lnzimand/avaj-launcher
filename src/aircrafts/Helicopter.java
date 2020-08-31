package src.aircrafts;

import src.coordinates.Coordinates;
import src.Logger;
import src.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        String weatherCondition = this.weatherTower.getWeather(coordinates);
        if (weatherCondition.equals("SUN")) {
            Logger.writeToFile("Helicopter#" + this.getOfficialName() + ": A perfect day for men who fight without pants.");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile("Helicopter#" + this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
            }
        } else if (weatherCondition.equals("RAIN")) {
            Logger.writeToFile("Helicopter#" + this.getOfficialName() + ": Eh baba, it's raining here yho!");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile("Helicopter#" + this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
            }
        } else if (weatherCondition.equals("FOG")) {
            Logger.writeToFile("Helicopter#" + this.getOfficialName() + ": I can't hear you I'm blind. There's a fog upon L.A.");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile("Helicopter#" + this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
            }
        } else {
            Logger.writeToFile("Helicopter#" + this.getOfficialName() + ": winter came for House Frey.");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile("Helicopter#" + this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
            }
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }
    
}