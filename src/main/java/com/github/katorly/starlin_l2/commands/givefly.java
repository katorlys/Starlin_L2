package com.github.katorly.starlin_l2.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.katorly.starlin_l2.backup.Messager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class givefly implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("givefly")) { //Gives a specific player fly.
            if (args.length == 1) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.performCommand("lp user " + args[0] + " group add fly");
                } else {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + args[0] + " group add fly");
                }
            } else {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /givefly <玩家ID>.");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        if (args.length == 1) {
            if (Objects.equals(args[0], "givefly")) {
                List<String> sub = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sub.add(player.getName());
                }
                return sub;
            }
        }
        return null;
    }
}