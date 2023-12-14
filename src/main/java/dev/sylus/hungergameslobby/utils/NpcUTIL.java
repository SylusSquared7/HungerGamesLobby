package dev.sylus.hungergameslobby.utils;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.logging.Level;

public class NpcUTIL {
    public NpcUTIL(){ //Constructor

    }

    public void createNPC(String name, Location location){
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name); npc.spawn(location);
        Logging.log(Level.INFO, "Created an NPC with the name of: " + name + " and the location: " + location);
    }
}
