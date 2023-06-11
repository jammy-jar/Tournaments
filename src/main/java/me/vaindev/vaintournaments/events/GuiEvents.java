package me.vaindev.vaintournaments.events;

import me.vaindev.vaintournaments.VainTournaments;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class GuiEvents {

    private final VainTournaments plugin;

    public GuiEvents(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnClose(InventoryCloseEvent event) {
        FileConfiguration config = plugin.getConfig();
        config.set("kit", "");

        int i = 0;
        for (ItemStack item : event.getInventory().getContents()) {
            config.set("kit." + i, item);
            i++;
        }

        plugin.saveConfig();
        plugin.reloadConfig();
    }
}
