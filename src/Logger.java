package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static final String fileName = "simulation.txt";
    private static FileWriter fileWriter = null;
    private static PrintWriter printWriter = null;

    public static void writeToFile(String message) {
        try {
            if (fileWriter == null) {
                fileWriter = new FileWriter(fileName);
            }
            if (printWriter == null) {
                printWriter = new PrintWriter(fileWriter);
            }
            printWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public static void createFile() {
    
        File newFile = new File(fileName);
        try {
            System.out.println("\nAvajLauncher launched");
			newFile.createNewFile();
		} catch (IOException e) {
            System.out.println("An error occurred");
			e.printStackTrace();
		}
            
    }

    public static void closeFile() throws IOException {
        fileWriter.close();
    }
}