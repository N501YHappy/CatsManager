package icu.n501yhappy.catsManager.Listeners;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static icu.n501yhappy.catsManager.CatsManager.Cats;

public class AttackLis implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            if (Cats.contains(attacker)) {
                double originalDamage = event.getDamage();
                double newDamage = originalDamage * 0.1;
                event.setDamage(newDamage);
                attacker.sendTitle("","§a没有力气了qwq",0,60,0);
            }
        }
    }
    @EventHandler
    public void OnDamage(EntityDamageEvent event) {
        Player player = null;
        if (event.getEntity() instanceof Player) player= (Player) event.getEntity();
        if (Cats.contains(player)){
            event.setDamage(event.getDamage()*0.9);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 3*20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3*20, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3*20, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10*20, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*20, 2));
        }
    }
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getTarget() instanceof Player) {
            Player player = (Player) event.getTarget();

            if (Cats.contains(player)) {
                if (event.getEntity() instanceof Creature) {
                    Creature creature = (Creature) event.getEntity();

                    event.setCancelled(true);

                    creature.setTarget(null);
                }
            }
        }
    }
}
