package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Hologram {
    ArmorStand hologram;
    private List<ArmorStand> armorStands = new ArrayList<>();
    Databases databases;
    PositionManager positionManager;

    public Hologram(Databases databasesInstance, PositionManager positionManagerInstance){
        databases = databasesInstance;
        positionManager = positionManagerInstance;
    }

    public void initialiseLeaderbord(Location startLocation){
        World world = Bukkit.getWorld("world");
        double yOffset = 0; // Adjust the Y offset as needed

        for (int i = 0; i <= 5; i++) {
            ArmorStand armorStand = (ArmorStand) startLocation.getWorld().spawnEntity(startLocation.clone().add(0, yOffset, 0), EntityType.ARMOR_STAND); // spawn(startLocation.clone().add(0, yOffset, 0), ArmorStand.class);
            armorStand.setVisible(false);
            if (i == 0){
                armorStand.setCustomName("§b§lOverall Points");
            } else {
                armorStand.setCustomName("§6Nobody §7- §e0"); // Dummy data
            }
            armorStand.setCustomNameVisible(true);
            armorStand.setGravity(false);
            armorStands.add(armorStand);
            yOffset -= 0.3; // Adjust the vertical spacing as needed
        }
        Logging.log(Level.INFO, "Created the armourstand scoreboard");
    }

    public void updateScores() {
       for (int i = 1; i < armorStands.size(); i++) {
            ArmorStand armorStand = armorStands.get(i);
            if (positionManager.getPlayerLeaderbord().get(i -1).equals("Nobody")){
                armorStand.setCustomName("§6Nobody §7- §e " + 0);
            } else {
                armorStand.setCustomName("§6" + positionManager.getPlayerLeaderbord().get(i - 1) + " §7- §e" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(i -1)).getUniqueId()));
            }
        }
        Logging.log(Level.INFO, "Updated scores on the armor stands.");
    }
}

