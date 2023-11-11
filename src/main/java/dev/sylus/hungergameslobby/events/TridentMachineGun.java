package dev.sylus.hungergameslobby.events;

import dev.sylus.hungergameslobby.HungerGamesLobby;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TridentMachineGun extends BukkitRunnable implements Listener {
    Player player;
    HungerGamesLobby main;
    public TridentMachineGun(HungerGamesLobby mainInstance){
        main = mainInstance;
    }

    @EventHandler
    public void  onPlayerRightClick(PlayerInteractEvent event){
        player = event.getPlayer();

        if (event.getAction().toString().contains("RIGHT") && player.getInventory().getItemInMainHand().getType() == Material.TRIDENT
                && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§9MACHINE GUNDENT")){
            this.runTaskTimer(main, 0, 5);
        }
    }

    @Override
    public void run() {
        if (player.isHandRaised()){
            Location location = player.getLocation();
            Vector direction = location.getDirection();
            ItemStack trident = new ItemStack(Material.TRIDENT);
            trident.addEnchantment(Enchantment.LOYALTY, 1);

            Trident tridentEntity = (Trident) player.launchProjectile(Trident.class);
            tridentEntity.setVelocity(direction.multiply(2.0));
        } else {
            cancel();
        }
    }
}
