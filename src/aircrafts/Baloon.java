package src.aircrafts;

import src.coordinates.Coordinates;
import src.Logger;
import src.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
    
        String weatherCondition = this.weatherTower.getWeather(coordinates);
        if (weatherCondition.equals("SUN")) {
            Logger.writeToFile("Baloon#" + this.getOfficialName() + ": Summer is here, you know what that means, right? SummerSlam baby!!!");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile(this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
            }
        } else if (weatherCondition.equals("RAIN")) {
            Logger.writeToFile("Baloon#" + this.getOfficialName() + ": This Baloon is leaky, where is Rihanna when you need here?");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile(this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
            }
        } else if (weatherCondition.equals("FOG")) {
            Logger.writeToFile("Baloon#" + this.getOfficialName() + ": I can't hear you I'm blind. There's a fog upon L.A.");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile(this.getOfficialName() + " landing");
                this.weatherTower.unregister(this);
            } else {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
            }
        } else {
            Logger.writeToFile("Baloon#" + this.getOfficialName() + ": Winter came for House Frey.");
            if (this.coordinates.getHeight() <= 0) {
                Logger.writeToFile(this.getOfficialName() + " landing");
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