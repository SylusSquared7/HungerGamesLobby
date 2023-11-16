package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Hologram {
    ArmorStand hologram;
    private List<ArmorStand> armorStands = new ArrayList<>();
    public void initialiseLeaderbord(Location startLocation){
        World world = Bukkit.getWorld("world");
        double yOffset = 0; // Adjust the Y offset as needed

        for (int i = 0; i <= 6; i++) {
            ArmorStand armorStand = (ArmorStand) startLocation.getWorld().spawnEntity(startLocation.clone().add(0, yOffset, 0), EntityType.ARMOR_STAND); // spawn(startLocation.clone().add(0, yOffset, 0), ArmorStand.class);
            armorStand.setVisible(false);
            if (i == 0){
                armorStand.setCustomName("§b§lOverall Points");
            } else {
                armorStand.setCustomName("§6Player" + String.valueOf(i) + " §7- §e" + (6 - i) * 200); // Dummy data
            }
            armorStand.setCustomNameVisible(true);
            armorStand.setGravity(false);
            armorStands.add(armorStand);
            yOffset -= 0.3; // Adjust the vertical spacing as needed
        }
        Logging.log(Level.INFO, "Created the armourstand scoreboard");
    }

    public void updateLeaderbord(){
        World world = Bukkit.getWorld("world");
    }
}
