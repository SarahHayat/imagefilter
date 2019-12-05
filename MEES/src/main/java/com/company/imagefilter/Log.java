package com.company.imagefilter;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    /**
     *  Create and Write in a Directory all Operations
     * @param message Message to indicate Operation
     */
    void log(String message){
        try {
            FileWriter myWriter = new FileWriter("access.log", true);
            myWriter.write(message);
            System.out.println("Successfully wrote to the file ");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred."); e.printStackTrace();
        }
    }
}
