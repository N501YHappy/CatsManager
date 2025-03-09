package icu.n501yhappy.catsManager.Listeners;

import icu.n501yhappy.catsManager.CatsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

import static icu.n501yhappy.catsManager.CatsManager.Cats;

public class EatLis implements Listener {
    public static boolean isFish(ItemStack itemStack) {
        if (itemStack == null) return false;

        Material material = itemStack.getType();

        // Check for the different types of fish
        return material == Material.COD ||
                material == Material.COOKED_COD ||
                material == Material.COOKED_SALMON ||
                material == Material.SALMON ||
                material == Material.TROPICAL_FISH ||
                material == Material.PUFFERFISH;
    }
    @EventHandler
    public void OnEat(PlayerItemConsumeEvent event){
        Player player =event.getPlayer();
        if (Cats.contains(player)){
            ItemStack is = event.getItem();
            if (!isFish(is)){
                player.sendMessage("§c不是小鱼干！难吃喵！");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot()); // Get item in new slot

        if (item != null && isFish(item)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    applySlownessAura(player);
                }
            }.runTaskLater(CatsManager.instance, 1);
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity rightClickedEntity = event.getRightClicked();
        //TODO
    }
    private void applySlownessAura(Player fishHolder) {
        List<Entity> nearbyEntities = fishHolder.getNearbyEntities(20, 20, 20);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof Player) {
                Player nearbyPlayer = (Player) entity;
                if (!nearbyPlayer.equals(fishHolder) && Cats.contains(nearbyPlayer)) {
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10*20, 1));
                    faceLocation(nearbyPlayer,fishHolder.getLocation());
                }
            }
        }
    }
    public static void faceLocation(Player playerToRotate, Location targetLocation) {
        Location playerLocation = playerToRotate.getLocation();
        Vector direction = targetLocation.toVector().subtract(playerLocation.toVector()).normalize();
        Location newLocation = playerLocation.setDirection(direction);
        playerToRotate.teleport(newLocation);
    }
}
