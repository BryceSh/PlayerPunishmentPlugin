package com.ubiniti.punishment.listeners;

import com.sun.tools.javac.util.StringUtils;
import com.ubiniti.punishment.Punishment;
import com.ubiniti.punishment.commands.punishCommand;
import com.ubiniti.punishment.utilities.Menus;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.util.Date;

public class MenuListener implements Listener {

    Plugin plugin = Punishment.getPlugin(Punishment.class);
    public static String banLength;
    public static String banReason;

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        // Checks what menu they're on
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Punish Player")) {


            // Checks if they actually clicked on an item
            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem().getType().equals(Material.WOODEN_AXE)) {

                    if (p.isOp() || p.hasPermission("playerpunish.ban") || p.hasPermission("playerpunish.*")) {
                        Menus.openBanOptions(p, punishCommand.targetPlayer);
                    } else {
                        p.sendMessage(Punishment.pre + ChatColor.RED  + "You do not have permission to use this function!");
                    }

                } else if (e.getCurrentItem().getType().equals(Material.REDSTONE)) {

                    if (p.isOp() || p.hasPermission("playerpunish.kick") || p.hasPermission("playerpunish.*")) {
                        Menus.openConfirmKick(p, punishCommand.targetPlayer);
                    } else {
                        p.sendMessage(Punishment.pre + ChatColor.RED  + "You do not have permission to use this function!");
                    }


                } else if (e.getCurrentItem().getType().equals(Material.BOOK)) {
                    if (plugin.getServer().getPluginManager().getPlugin("Essentials") != null) {
                        p.sendMessage(Punishment.pre + ChatColor.GOLD + "EssentialsX is installed!");
                    } else {
                        p.sendMessage(Punishment.pre + ChatColor.DARK_RED + "[ERROR] " + ChatColor.RED + "You need the plugin Essentials installed to use this function!");
                    }
                }
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Choose Length")) {

            if (e.getCurrentItem() != null) {

                Material selectedM = e.getCurrentItem().getType();

                if (selectedM.equals(Material.BARRIER)) {

                    Menus.openBanOptions(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.GREEN_WOOL)) {

                    banLength = "One Day";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.YELLOW_WOOL)) {

                    banLength = "One Week";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.BEDROCK)) {

                    banLength = "Permanent";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.END_CRYSTAL)) {

                    banLength = "IP Ban";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);

                }

            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm Ban?")) {

            if (e.getCurrentItem() != null) {

                Material selectedM = e.getCurrentItem().getType();

                if (selectedM.equals(Material.RED_WOOL)) {

                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.GREEN_WOOL)) {

                    // Banning Logic
                    p.closeInventory();


                    Date length;
                    String name = ChatColor.stripColor(punishCommand.targetPlayer.getDisplayName());
                    String IP = punishCommand.targetPlayer.getAddress().getHostName();

                    if (banLength.equalsIgnoreCase("one day")) {
                        length = new Date(System.currentTimeMillis() + 1000 * (60 * 60));
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, length, p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("one week")) {
                        length = new Date(System.currentTimeMillis() + 1000 * ((60 * 60) * 24));
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, length, p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("permanent")) {
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, null, p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("ip ban")) {
                        System.out.println("" + IP + " has been IP Banned");
                        p.getServer().banIP(punishCommand.targetPlayer.getAddress().toString());
                    }

                    if (plugin.getConfig().getBoolean("options.announceBans")) {
                        Bukkit.broadcastMessage(Punishment.pre + ChatColor.RED + "Player " + ChatColor.GOLD + punishCommand.targetPlayer.getName() + ChatColor.RED + " has been "+ ChatColor.DARK_RED + ChatColor.UNDERLINE + "banned"+ChatColor.RESET  + ChatColor.RED+" from the server!");
                    }

                    if (punishCommand.targetPlayer != null) {
                        punishCommand.targetPlayer.kickPlayer("You've been banned!");
                    }

                    p.sendMessage("Player has been banned. Reason: " + banReason + " Length: " + banLength);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.7F, 0.7F);

                }

            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Choose a reason")) {

            banReason = "Not Specified";

            if (e.getCurrentItem() != null) {

                Material selectedM = e.getCurrentItem().getType();
                if (selectedM.equals(Material.DIAMOND_SWORD)) {

                    banReason = "Hacking / Exploiting";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.DIAMOND_BLOCK)) {

                    banReason = "XRay";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.RED_BED)) {

                    banReason = "Ban Evasion";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.ARMOR_STAND)) {

                    banReason = "Staff Impersonation";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.BOOK)) {

                    banReason = "Spamming";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.EGG)) {

                    banReason = "Not Specified";
                    Menus.openBanLength(p, punishCommand.targetPlayer);

                } else if (selectedM.equals(Material.BARRIER)) {

                    Menus.openPunishmentMenu(p, punishCommand.targetPlayer);

                }

            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm Kick?")) {

            if (e.getCurrentItem() != null) {

                if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)) {

                    if (plugin.getConfig().getBoolean("options.announceBans")) {
                        Bukkit.broadcastMessage(Punishment.pre + ChatColor.RED + "Player " + ChatColor.GOLD + punishCommand.targetPlayer.getName() + ChatColor.RED + " has been kicked from the server!");
                    }

                    if (punishCommand.targetPlayer != null) {
                        punishCommand.targetPlayer.kickPlayer("You've been kicked!");
                    }

                } else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
                    Menus.openPunishmentMenu(p, punishCommand.targetPlayer);
                }

            }
            e.setCancelled(true);
        }

    }


}
