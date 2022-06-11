package com.ubiniti.punishment;

import com.ubiniti.punishment.commands.punishCommand;
import com.ubiniti.punishment.listeners.MenuListener;
import com.ubiniti.punishment.listeners.playerPreLoad;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Punishment extends JavaPlugin {

    public final static String pre = ChatColor.BLACK + "[" + ChatColor.DARK_RED + "Player Punishment" + ChatColor.BLACK + "] " + ChatColor.WHITE;
    public final static Boolean debug = true; // for plugin testing only! Don't enable when running on a server, or you'll risk your security!

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new playerPreLoad(), this);
        getCommand("punish").setExecutor(new punishCommand());


        loadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


}
