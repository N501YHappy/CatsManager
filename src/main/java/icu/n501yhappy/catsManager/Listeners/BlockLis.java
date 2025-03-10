package icu.n501yhappy.catsManager.Listeners;

import icu.n501yhappy.catsManager.items.catnip;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Random;

import static icu.n501yhappy.catsManager.CatsManager.Cats;
import static icu.n501yhappy.catsManager.CatsManager.cat_team;

public class BlockLis implements Listener {
    @EventHandler
    public void OnBreak(BlockBreakEvent event){
        if (Cats.contains(event.getPlayer())){
            if (new Random().nextDouble() < 0.1){
                event.getPlayer().sendMessage("§a爪子挖不动喵，再试试吧");
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void OnPlace(BlockPlaceEvent event){
        if (event.getItemInHand().isSimilar(catnip.get())) event.setCancelled(true);
    }
}
