package icu.n501yhappy.catsManager;

import icu.n501yhappy.catsManager.Listeners.*;
import icu.n501yhappy.catsManager.commands.catmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public final class CatsManager extends JavaPlugin {
    public static List<Player> Cats;
    public static CatsManager instance;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static List<String> playerNames = new ArrayList<>();
    public static String team_display;
    public static Team cat_team;
    @Override
    public void onEnable() {
        instance=this;
        cat_team = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("Cats");
        getServer().getPluginCommand("catmanager").setExecutor(new catmanager());
        saveDefaultConfig();
        team_display = getConfig().getString("Perfix");
        playerNames = CatsManager.instance.getConfig().getStringList("Cats");
        Cats = new ArrayList<>();
        getServer().getPluginManager().registerEvents(new AttackLis(),this);
        getServer().getPluginManager().registerEvents(new ChatLis(),this);
        getServer().getPluginManager().registerEvents(new EatLis(),this);
        getServer().getPluginManager().registerEvents(new JoinORLeftLis(),this);
        getServer().getPluginManager().registerEvents(new BlockLis(),this);
        //初始化team
        cat_team.setDisplayName(team_display);
        cat_team.setColor(ChatColor.LIGHT_PURPLE);
        cat_team.setNameTagVisibility(NameTagVisibility.ALWAYS);
        cat_team.setPrefix(team_display + " ");
        cat_team.setSuffix(String.valueOf(ChatColor.RESET));
        CatBuffer(); //为cat加buff
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void CatBuffer(){
        scheduler.schedule(() -> {
            for (Player player : Cats) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,10,1));
            }
        }, 5, TimeUnit.SECONDS);
    }
}
