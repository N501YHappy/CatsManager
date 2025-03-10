package icu.n501yhappy.catsManager.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static icu.n501yhappy.catsManager.CatsManager.*;

public class JoinORLeftLis implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent event){
        if (playerNames.contains(event.getPlayer().getName())) Cats.add(Bukkit.getPlayer(event.getPlayer().getName()));
        cat_team.addEntity(event.getPlayer());
    }
    @EventHandler
    public void OnLeft(PlayerQuitEvent event){
        if (Cats.contains(event.getPlayer())) Cats.remove(Bukkit.getPlayer(event.getPlayer().getName()));
        cat_team.removeEntity(event.getPlayer());
    }
}
