package dev.sylus.hungergameslobby.utils;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Logging {

    private static final String fileName = "hungerGamesCore.log";

    public Logging(){

    }

    public static void log(Level severity, String log){
        try {
            File logFile = new File(fileName);
            if (!(logFile.exists())){
                logFile.createNewFile();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String logEntry = dateFormat.format(new Date() + " [" + String.valueOf(severity) + "] " + log);

            FileWriter writer = new FileWriter(logFile, true);
            writer.write(logEntry + "\n");
            writer.close();
        } catch (IOException exception) {
            Bukkit.getLogger().log(Level.SEVERE, String.valueOf(exception));
        }
    }


}
