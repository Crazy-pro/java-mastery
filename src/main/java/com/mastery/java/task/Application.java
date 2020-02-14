package com.mastery.java.task;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.Calendar;

@SpringBootApplication
public class Application {

    public static Logger LOGGER = Logger.getLogger("LOGGER");

    public static void main(String... args) {
        createLogFile();
        SpringApplication.run(Application.class, args);
    }

    private static void createLogFile() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
            FileHandler fileHandler = new FileHandler("./logs/LogFile_" + format.format(Calendar.getInstance().getTime()) + ".log");
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
        } catch (SecurityException | IOException ex) {
            throw new RuntimeException("Problems with creating the log file");
        }
    }

}
