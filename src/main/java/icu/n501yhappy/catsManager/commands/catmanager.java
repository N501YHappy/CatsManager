package icu.n501yhappy.catsManager.commands;

import icu.n501yhappy.catsManager.CatsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static icu.n501yhappy.catsManager.CatsManager.Cats;
import static icu.n501yhappy.catsManager.CatsManager.instance;

public class catmanager implements CommandExecutor {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final StringBuilder helpMessageBuilder = new StringBuilder()
            .append("§a§l§m-----------------------------------§a§l[§dCats§bManager§a§l]§a§l§m-----------------------------------\n")
            .append("§l§e▶ §b/cm add <player>                §a把一个玩家变为猫娘\n")
            .append("§l§e▶ §b/cm remove <player>             §a把一个玩家从猫娘移除\n")
            .append("§l§e▶ §b/cm list                      §a查看猫娘列表\n")
            .append("§a§l§m-----------------------------------§a§l[§dCats§bManager§a§l]§a§l§m-----------------------------------");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("catsmanager.use")) {
            sender.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }
        if (args.length < 1) {
            sendHelpMessage(sender);
            return true;
        }

        String action = args[0];

        if (action.equalsIgnoreCase("add")) {
            handleAddCommand(sender, args);
        } else if (action.equalsIgnoreCase("remove")) {
            handleRemoveCommand(sender, args);
        } else if (action.equalsIgnoreCase("list")) {
            handleListCommand(sender);
        }
        scheduleSaveConfig();
        return true;
    }

    private void handleAddCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§cUsage: /cm add <player>");
            return;
        }

        String playerName = args[1];
        Player target = getPlayer(playerName);

        if (target == null) {
            sender.sendMessage("§cPlayer " + playerName + " not found.");
            return;
        }

        if (Cats.contains(target)) {
            sender.sendMessage("§cPlayer " + playerName + " is already a cat.");
            return;
        }

        Cats.add(target);
        sender.sendMessage("§aPlayer " + playerName + " is now a cat!");
    }

    private void handleRemoveCommand(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("§cUsage: /cm remove <player>");
            return;
        }

        String playerName = args[1];
        Player target = getPlayer(playerName);

        if (target == null) {
            sender.sendMessage("§cPlayer " + playerName + " not found.");
            return;
        }

        if (!Cats.contains(target)) {
            sender.sendMessage("§cPlayer " + playerName + " is not a cat.");
            return;
        }

        Cats.remove(target);
        sender.sendMessage("§aPlayer " + playerName + " is no longer a cat!");
    }

    private void handleListCommand(CommandSender sender) {
        if (Cats.isEmpty()) {
            sender.sendMessage("§eThere are no cats currently.");
            return;
        }

        StringBuilder listMessage = new StringBuilder("§eCurrent Cats:\n");
        for (Player player : Cats) {
            listMessage.append("§b- ").append(player.getName()).append("\n");
        }
        sender.sendMessage(listMessage.toString());
    }


    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(helpMessageBuilder.toString());
    }

    private Player getPlayer(String playerName) {
        try {
            return Bukkit.getPlayer(playerName);
        } catch (Exception e) {
            return null;
        }
    }

    private void scheduleSaveConfig() {
        scheduler.schedule(() -> {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Cats) {
                playerNames.add(player.getName());
            }
            instance.getConfig().set("Cats", playerNames);
            instance.saveConfig();
        }, 5, TimeUnit.SECONDS);
    }
}
