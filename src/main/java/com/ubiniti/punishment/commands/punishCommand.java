package com.ubiniti.punishment.commands;

import com.ubiniti.punishment.Punishment;
import com.ubiniti.punishment.utilities.Menus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class punishCommand implements CommandExecutor {

    public static Player targetPlayer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            targetPlayer = null;

            if (args.length == 1 && !args[0].equals("giveBanHammer")) {

                targetPlayer =  p.getServer().getPlayerExact(args[0]);

                if (targetPlayer != null && targetPlayer.isOnline()) {

                    // Player Checking
                    if (p.isOp() || p.hasPermission("playerpunish.open") || p.hasPermission("playerpunish.*")) {
                        if (!targetPlayer.isOp() ) {
                            Menus.openPunishmentMenu(p, targetPlayer);
                        } else {
                            p.sendMessage(Punishment.pre + "Cannot target player because they're oped!");
                            if (Punishment.debug) {
                                Menus.openPunishmentMenu(p, targetPlayer);
                            }
                        }
                    } else {
                        p.sendMessage(Punishment.pre + ChatColor.RED + "You do not have permission to use this function!");
                    }


                } else {
                    p.sendMessage(Punishment.pre + "Player is offline! Please try again!");
                }

            } else if (args.length == 1 && args[0].equals("giveBanHammer")) {

                if (p.isOp()) {

//                    ItemStack banHammer = new ItemStack(Material.WOODEN_AXE, 1);
//                    ItemMeta banHammerMeta = banHammer.getItemMeta();
//                    banHammerMeta.setDisplayName(ChatColor.DARK_RED + "Ban Hammer");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(ChatColor.DARK_PURPLE + "One must be careful with the powers");
//                    lore.add(ChatColor.DARK_PURPLE + "of the Ban Hammer");
//                    lore.add(ChatColor.GOLD + "Hit any player to ban them!");
//                    banHammerMeta.setLore(lore);
//                    banHammerMeta.isUnbreakable();
//                    banHammerMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1000,true );
//                    banHammer.setItemMeta(banHammerMeta);
//                    p.getInventory().setItemInMainHand(banHammer);

                }

            }else {

                p.sendMessage(Punishment.pre + "Invalid command usage! /punish [player name]");

            }

        }

        return true;
    }
}
