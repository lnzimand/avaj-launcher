package src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import src.aircrafts.AircraftFactory;
import src.aircrafts.Flyable;
import src.tower.WeatherTower;

class MyException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MyException(String description) {
        super(description);
    }
}

class validateInput {

    public void validateCoordinates(String[] args) throws MyException {

        if (args.length != 5) {
            throw new MyException(
                    "\nError:\tBad input(Describing Aircraft)\nUsage:\t<TYPE> <NAME> <LONGITUDE> <LATITUDE> <HEIGHT>\n");
        }

        try {
            if (Integer.parseInt(args[2]) < 1 || Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[4]) < 1) {
                throw new MyException(
                        "\nError:\tBad input(Coordinates)\nUsage:\t<LONGITUDE> <LATITUDE> <HEIGHT> should all be positive numbers\n");
            }
        } catch (Exception e) {
            throw new MyException(
                    "\nError:\tBad input(Coordinates)\nUsage:\t<LONGITUDE> <LATITUDE> <HEIGHT> should all be positive numbers\n");
        }
    }

    public int getIterationNumber(String data) throws MyException {
        try {
            int iteration = Integer.parseInt(data);
            return iteration;
        } catch (Exception e) {
            throw new MyException(
                    "\nError:\tBad input\nUsage:\tFirst line of the file should be a positive integer number\n");
        }
    }

    public File getFile(String userInputFileName) {

        File input = new File(userInputFileName);
        return input;
    }

    public Scanner getScanner(File input) {
        try {
            Scanner scanner = new Scanner(input);
            return scanner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void validateAircraftType(String aircraftName) {

        try {
            if (aircraftName.equals("Baloon")) {
                return;
            } else if (aircraftName.equals("JetPlane")) {
                return;
            } else if (aircraftName.equals("Helicopter")) {
                return;
            } else {
                throw new MyException(
                        "\nError:\tAircraft Name\nUsage:\tAllowed aircrafts names are: \"Baloon\" \"JetPlane\" \"Helicopter\"\n");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}

class AircraftProduction extends AircraftFactory { }

public class Main {

    public static void main(String[] args) throws MyException {
        if (args.length == 1) {
            try {
                validateInput validateInput = new validateInput();
                File input = validateInput.getFile(args[0]);
                if (!input.isFile())
                    throw new MyException("\nError:\tError while reading file\nReason:\tNo such file exists\n");
                Scanner readerScanner = validateInput.getScanner(input);
                String data[] = null;
                WeatherTower weatherTower = new WeatherTower();

                int iteration = validateInput.getIterationNumber(readerScanner.nextLine());
                Logger.createFile();
                while (readerScanner.hasNextLine()) {
                    data = readerScanner.nextLine().split("\t+| ");
                    validateInput.validateAircraftType(data[0]);
                    validateInput.validateCoordinates(data);
                    weatherTower.registerToWeatherTower(AircraftProduction.newAircraft(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4])));
                }

                int index = 0;
                while (index < iteration) {
                    weatherTower.changeWeather();
                    index++;
                }
                readerScanner.close();
                try {
                    Logger.closeFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\nProgram exited successfully.\nCheck simulation.txt for more information\n");
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                throw new MyException("\nError:\tBad input(Running Program)\nUsage:\tjava <PROGRAM NAME> <FILEPATH/NAME>\n");
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}