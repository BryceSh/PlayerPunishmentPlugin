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

import java.util.Calendar;
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

                String selectedM = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());

                if (selectedM.equalsIgnoreCase("one hour")) {
                    banLength = "One Hour";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("one day")) {
                    banLength = "One Day";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("one week")) {
                    banLength = "One Week";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("one month")) {
                    banLength = "One Month";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("3 months")) {
                    banLength = "3 Months";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("permanent ban")) {
                    banLength = "Permanent";
                    Menus.openConfirmBan(p, punishCommand.targetPlayer);
                } else if (selectedM.equalsIgnoreCase("ip ban")) {
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


                    String name = ChatColor.stripColor(punishCommand.targetPlayer.getDisplayName());
                    String IP = punishCommand.targetPlayer.getAddress().getHostName();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());

                    if (banLength.equalsIgnoreCase("one hour")) {
                        cal.add(Calendar.HOUR_OF_DAY, 1);
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, cal.getTime(), p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("one day")) {
                        cal.add(Calendar.DAY_OF_WEEK, 1);
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, cal.getTime(), p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("one week")) {
                        cal.add(Calendar.WEEK_OF_MONTH, 1);
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, cal.getTime(), p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("one month")) {
                        cal.add(Calendar.MONTH, 1);
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, cal.getTime(), p.getDisplayName());
                    } else if (banLength.equalsIgnoreCase("3 months")) {
                        cal.add(Calendar.MONTH, 3);
                        p.getServer().getBanList(BanList.Type.NAME).addBan(name, banReason, cal.getTime(), p.getDisplayName());
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
