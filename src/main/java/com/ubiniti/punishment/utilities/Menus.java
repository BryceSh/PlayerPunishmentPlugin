package com.ubiniti.punishment.utilities;

import com.ubiniti.punishment.listeners.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.Array;
import java.util.ArrayList;

public class Menus {

   public static void openPunishmentMenu(Player p, Player target) {

       Inventory menu = Bukkit.createInventory(p, 27, ChatColor.RED + "Punish Player");

       // Creates the main menu items
       ItemStack banItem = new ItemStack(Material.WOODEN_AXE,1);
       ItemMeta banItemMeta = banItem.getItemMeta();
       banItemMeta.setDisplayName(ChatColor.RED + "Ban Player");
       banItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
       banItem.setItemMeta(banItemMeta);

       ItemStack kickItem = new ItemStack(Material.REDSTONE,1);
       ItemMeta kickItemMeta = kickItem.getItemMeta();
       kickItemMeta.setDisplayName(ChatColor.RED + "Kick Player");
       kickItem.setItemMeta(kickItemMeta);

       ItemStack muteItem = new ItemStack(Material.BOOK, 1);
       ItemMeta muteItemMeta = muteItem.getItemMeta();
       muteItemMeta.setDisplayName(ChatColor.RED + "Mute Player");
       ArrayList<String> lore = new ArrayList<>();
       lore.add(ChatColor.GOLD + "Requires EssentialsX plugin");
       muteItemMeta.setLore(lore);
       muteItem.setItemMeta(muteItemMeta);

       ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
       SkullMeta playerHeadM = (SkullMeta) playerHead.getItemMeta();
       playerHeadM.setDisplayName(target.getDisplayName());
       playerHeadM.setOwnerProfile(target.getPlayerProfile());
       playerHead.setItemMeta(playerHeadM);

       // Adds the items to the menu
       menu.setItem(4, playerHead);
       menu.setItem(11, banItem);
       menu.setItem(13, kickItem);
       menu.setItem(15, muteItem);

       p.openInventory(menu);

   }

   public static void openBanLength(Player p, Player target) {

       Inventory banMenu = Bukkit.createInventory(p, 27, ChatColor.RED + "Choose Length");

       ItemStack oneHour = new ItemStack(Material.PAPER,1 );
       ItemMeta oneHourM = oneHour.getItemMeta();
       oneHourM.setDisplayName(ChatColor.GOLD + "One Hour");
       oneHour.setItemMeta(oneHourM);

       ItemStack oneDay = new ItemStack(Material.PAPER,1 );
       ItemMeta oneDayM = oneDay.getItemMeta();
       oneDayM.setDisplayName(ChatColor.GOLD + "One Day");
       oneDay.setItemMeta(oneDayM);

       ItemStack oneWeek = new ItemStack(Material.PAPER, 1);
       ItemMeta oneWeekM = oneWeek.getItemMeta();
       oneWeekM.setDisplayName(ChatColor.GOLD + "One Week");
       oneWeek.setItemMeta(oneWeekM);

       ItemStack oneMonth = new ItemStack(Material.PAPER, 1);
       ItemMeta oneMonthM = oneMonth.getItemMeta();
       oneMonthM.setDisplayName(ChatColor.GOLD + "One Month");
       oneMonth.setItemMeta(oneMonthM);

       ItemStack threeMonths = new ItemStack(Material.PAPER, 1);
       ItemMeta threeMonthsM = threeMonths.getItemMeta();
       threeMonthsM.setDisplayName(ChatColor.GOLD + "3 Months");
       threeMonths.setItemMeta(threeMonthsM);

       ItemStack permBan = new ItemStack(Material.BEDROCK, 1);
       ItemMeta permBanM = permBan.getItemMeta();
       permBanM.setDisplayName(ChatColor.RED + "Permanent Ban");
       permBan.setItemMeta(permBanM);

       ItemStack ipBan = new ItemStack(Material.END_CRYSTAL, 1);
       ItemMeta ipBanM = ipBan.getItemMeta();
       ipBanM.setDisplayName(ChatColor.DARK_RED + "IP Ban");
       ipBan.setItemMeta(ipBanM);



       ItemStack cancel = new ItemStack(Material.BARRIER, 1);
       ItemMeta cancelM = cancel.getItemMeta();
       cancelM.setDisplayName(ChatColor.AQUA + "<- Go Back");
       cancel.setItemMeta(cancelM);

       ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
       SkullMeta playerHeadM = (SkullMeta) playerHead.getItemMeta();
       playerHeadM.setDisplayName(target.getDisplayName());
       playerHeadM.setOwnerProfile(target.getPlayerProfile());
       playerHead.setItemMeta(playerHeadM);

       banMenu.setItem(10, oneHour);
       banMenu.setItem(11, oneDay);
       banMenu.setItem(12, oneWeek);
       banMenu.setItem(13, oneMonth);
       banMenu.setItem(14, threeMonths);
       banMenu.setItem(15, permBan);
       banMenu.setItem(16, ipBan);
       banMenu.setItem(26, cancel);
       banMenu.setItem(4, playerHead);

       p.openInventory(banMenu);


   }

   public static void openConfirmBan(Player p, Player target) {

       Inventory confirmBan = Bukkit.createInventory(p, 27, ChatColor.RED + "Confirm Ban?");

       ItemStack confirm = new ItemStack(Material.GREEN_WOOL, 1);
       ItemMeta confirmM = confirm.getItemMeta();
       confirmM.setDisplayName(ChatColor.GREEN + "Confirm: " + ChatColor.RED + MenuListener.banLength);
       confirm.setItemMeta(confirmM);

       ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
       ItemMeta cancelM = cancel.getItemMeta();
       cancelM.setDisplayName(ChatColor.RED + "Cancel");
       cancel.setItemMeta(cancelM);

       ItemStack time = new ItemStack(Material.PAPER, 1);
       ItemMeta timeM = time.getItemMeta();
       timeM.setDisplayName(ChatColor.GOLD + MenuListener.banLength);
       time.setItemMeta(timeM);

       ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
       SkullMeta playerHeadM = (SkullMeta) playerHead.getItemMeta();
       playerHeadM.setDisplayName(target.getDisplayName());
       playerHeadM.setOwnerProfile(target.getPlayerProfile());
       playerHead.setItemMeta(playerHeadM);


       confirmBan.setItem(12, confirm);
       confirmBan.setItem(14, cancel);
       confirmBan.setItem(4, playerHead);
       confirmBan.setItem(22, time);

       p.openInventory(confirmBan);

   }

   public static void openConfirmKick(Player p, Player target) {

       Inventory confirmKick = Bukkit.createInventory(p, 27, ChatColor.RED + "Confirm Kick?");
       ItemStack confirm = new ItemStack(Material.GREEN_WOOL, 1);
       ItemMeta confirmM = confirm.getItemMeta();
       confirmM.setDisplayName(ChatColor.GREEN + "Confirm: " + ChatColor.RED + "Kick");
       confirm.setItemMeta(confirmM);

       ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
       ItemMeta cancelM = cancel.getItemMeta();
       cancelM.setDisplayName(ChatColor.RED + "Cancel");
       cancel.setItemMeta(cancelM);

       ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
       SkullMeta playerHeadM = (SkullMeta) playerHead.getItemMeta();
       playerHeadM.setDisplayName(target.getDisplayName());
       playerHeadM.setOwnerProfile(target.getPlayerProfile());
       playerHead.setItemMeta(playerHeadM);


       confirmKick.setItem(12, confirm);
       confirmKick.setItem(14, cancel);
       confirmKick.setItem(4, playerHead);

       p.openInventory(confirmKick);

   }

   public static void openBanOptions(Player p, Player target) {

       Inventory menu = Bukkit.createInventory(p, 27, ChatColor.RED + "Choose a reason");

       ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
       SkullMeta playerHeadM = (SkullMeta) playerHead.getItemMeta();
       playerHeadM.setDisplayName(target.getDisplayName());
       playerHeadM.setOwnerProfile(target.getPlayerProfile());
       playerHead.setItemMeta(playerHeadM);

       ItemStack reason1 = new ItemStack(Material.DIAMOND_SWORD, 1);
       ItemMeta reason1M = reason1.getItemMeta();
       reason1M.setDisplayName(ChatColor.AQUA + "Hacking / Exploiting");
       reason1.setItemMeta(reason1M);

       ItemStack reason2 = new ItemStack(Material.DIAMOND_BLOCK, 1);
       ItemMeta reason2M = reason2.getItemMeta();
       reason2M.setDisplayName(ChatColor.AQUA + "XRay");
       reason2.setItemMeta(reason2M);

       ItemStack reason3 = new ItemStack(Material.RED_BED, 1);
       ItemMeta reason3M = reason3.getItemMeta();
       reason3M.setDisplayName(ChatColor.AQUA + "Ban Evasion");
       reason3.setItemMeta(reason3M);

       ItemStack reason4 = new ItemStack(Material.ARMOR_STAND, 1);
       ItemMeta reason4M = reason4.getItemMeta();
       reason4M.setDisplayName(ChatColor.AQUA + "Staff Impersonation");
       reason4.setItemMeta(reason4M);

       ItemStack reason5 = new ItemStack(Material.BOOK, 1);
       ItemMeta reason5M = reason5.getItemMeta();
       reason5M.setDisplayName(ChatColor.AQUA + "Spamming");
       reason5.setItemMeta(reason5M);

       ItemStack reason6 = new ItemStack(Material.EGG, 1);
       ItemMeta reason6M = reason6.getItemMeta();
       reason6M.setDisplayName(ChatColor.AQUA + "Other");
       reason6.setItemMeta(reason6M);

       ItemStack cancel = new ItemStack(Material.BARRIER, 1);
       ItemMeta cancelM = cancel.getItemMeta();
       cancelM.setDisplayName(ChatColor.AQUA + "<- Go Back");
       cancel.setItemMeta(cancelM);

       menu.setItem(10, reason1);
       menu.setItem(11, reason2);
       menu.setItem(12, reason3);
       menu.setItem(13, reason4);
       menu.setItem(14, reason5);
       menu.setItem(15, reason6);
       menu.setItem(26, cancel);
       menu.setItem(4, playerHead);

       p.openInventory(menu);

   }

}
