package src.tower;

import java.util.ArrayList;
import java.util.List;

import src.aircrafts.Aircraft;
import src.aircrafts.Baloon;
import src.aircrafts.Flyable;
import src.aircrafts.Helicopter;
import src.aircrafts.JetPlane;
import src.Logger;

public abstract class Tower {

    private final List<Flyable> observers = new ArrayList<Flyable>();
    int index;

    public void register(final Flyable flyable) {

        if (flyable instanceof JetPlane) {
            Logger.writeToFile(
                    "Tower says: JetPlane#" + ((JetPlane) flyable).getOfficialName() + " registered to weather tower.");
        } else if (flyable instanceof Baloon) {
            Logger.writeToFile(
                    "Tower says: Baloon#" + ((Baloon) flyable).getOfficialName() + " registered to weather tower.");
        } else {
            Logger.writeToFile("Tower says: Helicopter#" + ((Helicopter) flyable).getOfficialName()
                    + " registered to weather tower.");
        }
        observers.add(flyable);
    }

    public void unregister(final Flyable flyable) {

        final String flyableName = ((Aircraft) flyable).getOfficialName();
        int index = 0;
        for (Flyable flyableHolder : observers) {
            if (flyableName.equals(((Aircraft)flyableHolder).getOfficialName())) {
                break;
            }
            index++;
        }
        if (flyable instanceof JetPlane) {
            Logger.writeToFile("Tower says: JetPlane#" + ((JetPlane) flyable).getOfficialName()
                    + " unregistered from weather tower.");
        } else if (flyable instanceof Baloon) {
            Logger.writeToFile("Tower says: Baloon#" + ((Baloon) flyable).getOfficialName()
                    + " unregistered from weather tower.");
        } else {
            Logger.writeToFile("Tower says: Helicopter#" + ((Helicopter) flyable).getOfficialName()
                    + " unregistered from weather tower.");
        }
        observers.remove(index);
        if (this.index > 0)
            this.index--;
    }

    protected void conditionsChanged() {
        if (!observers.isEmpty()) {
            for (this.index = 0; this.index < observers.size(); this.index++) {
                observers.get(this.index).updateConditions();
            }
        }
    }
    
}