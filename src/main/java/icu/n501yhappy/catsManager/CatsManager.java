package icu.n501yhappy.catsManager;

import icu.n501yhappy.catsManager.Listeners.AttackLis;
import icu.n501yhappy.catsManager.Listeners.ChatLis;
import icu.n501yhappy.catsManager.Listeners.EatLis;
import icu.n501yhappy.catsManager.Listeners.JoinORLeftLis;
import icu.n501yhappy.catsManager.commands.catmanager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class CatsManager extends JavaPlugin {
    public static List<Player> Cats;
    public static CatsManager instance;

    public static List<String> playerNames = new ArrayList<>();
    @Override
    public void onEnable() {
        instance=this;
        getServer().getPluginCommand("catmanager").setExecutor(new catmanager());
        saveDefaultConfig();
        playerNames = CatsManager.instance.getConfig().getStringList("Cats");
        Cats = new ArrayList<>();
        getServer().getPluginManager().registerEvents(new AttackLis(),this);
        getServer().getPluginManager().registerEvents(new ChatLis(),this);
        getServer().getPluginManager().registerEvents(new EatLis(),this);
        getServer().getPluginManager().registerEvents(new JoinORLeftLis(),this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
