package me.vaindev.vaintournaments;

import me.vaindev.vaintournaments.commands.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class VainTournaments extends JavaPlugin {

    private static final String console_prefix = "[VainTournaments] ";

    private DuelManager duelManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.getLogger().info(console_prefix + "Enabling Plugin");

        this.duelManager = new DuelManager();

        this.getCommand("tournament").setExecutor(new PluginCommand(this));
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(console_prefix + "Disabling Plugin");
    }

    public DuelManager getDuelManager() {
        return this.duelManager;
    }
}
