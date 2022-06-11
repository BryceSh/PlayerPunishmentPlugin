package com.ubiniti.punishment.listeners;

import com.ubiniti.punishment.Punishment;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.UUID;

public class playerPreLoad implements Listener {

    @EventHandler
    public static void onPrePlayer(AsyncPlayerPreLoginEvent e) {

        Plugin plugin = Punishment.getPlugin(Punishment.class);

        System.out.println("Player Loaded");
        if (plugin.getConfig().getBoolean("banMessage.enabled")) {
            if (Bukkit.getBanList(BanList.Type.NAME).isBanned(String.valueOf(e.getUniqueId()))) {
                String reason = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(String.valueOf(e.getUniqueId())).getReason();
                String length = String.valueOf(Bukkit.getBanList(BanList.Type.NAME).getBanEntry(String.valueOf(e.getUniqueId())).getExpiration());
                if (length.equals("null")) {
                    length = "" + ChatColor.BOLD + ChatColor.DARK_RED + "Permanent Ban";
                }
                String bannedBy = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(String.valueOf(e.getUniqueId())).getSource();
                if (plugin.getConfig().getBoolean("banMessage.showBannedBy")) {
                    bannedBy =  "\n" + ChatColor.WHITE + "Banned By: " + ChatColor.LIGHT_PURPLE + bannedBy + "\n";
                } else {
                    bannedBy = "\n";
                }
                String banMessage = "" + ChatColor.BOLD + ChatColor.RED  + plugin.getConfig().getString("banMessage.title") + "\n" +
                        ChatColor.WHITE + "Reason: " + ChatColor.LIGHT_PURPLE + reason  +
                        bannedBy +
                        ChatColor.WHITE + "Expires: " + ChatColor.LIGHT_PURPLE + length + "\n\n" +
                        ChatColor.GOLD + "Appeal at: " + ChatColor.GREEN + ChatColor.UNDERLINE + plugin.getConfig().getString("banMessage.appeal");
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, banMessage);
            }
        }

    }
}
