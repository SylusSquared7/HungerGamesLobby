package dev.sylus.hungergameslobby.Files;

import dev.sylus.hungergameslobby.HungerGamesLobby;
import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class Files {
    private HungerGamesLobby main;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public Files (HungerGamesLobby mainInstance, String fileName) {
        main = mainInstance;
        saveDefultConfig(fileName); // The file name is the name of the YAML file that will be worked with
        // I am doing it like this, so I can re-use this code in future projects
        // without having to change a thing
    }

    public void reloadConfig(String fileName){
        if (configFile == null){
            configFile = new File(main.getDataFolder(), fileName);
            Logging.log(Level.WARNING, "Config file in null, intialising");
        }
        dataConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = main.getResource(fileName);
        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "File: " + fileName + " is NULL");
            Logging.log(Level.SEVERE, "Files generated an error: " + fileName + " is NULL");
        }
    }

    public FileConfiguration getConfig(String fileName){
        if (dataConfig == null){
            reloadConfig(fileName);
            Logging.log(Level.WARNING, "Files generated an exception: dataConfig is null, re initialising");
        }
        return dataConfig;
    }

    public void saveConfig(String fileName){
        if (dataConfig == null || configFile == null){
            return;
        }
        try {
            getConfig(fileName).save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config for: " + fileName + " Stack trace: " + e);
        }
    }

    public void saveDefultConfig(String fileName){
        if (configFile == null){
            configFile = new File(main.getDataFolder(), fileName);
        }
        if (!(configFile.exists())){
            main.saveResource(fileName, false);
        }
    }
}
