package me.vaindev.vaintournaments.guis;

import me.vaindev.vaintournaments.VainTournaments;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class KitGui implements InventoryHolder {

    private final Inventory inventory;
    private final VainTournaments plugin;

    public KitGui(VainTournaments plugin) {
        this.inventory = Bukkit.createInventory(null, 27, Component.text("Edit-Kit - Close to Apply"));
        this.plugin = plugin;
        initGui();
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return this.inventory;
    }

    public void initGui() {
        FileConfiguration config = plugin.getConfig();

        List items = config.getList("kit");
        items.forEach(item -> plugin.getLogger().warning(item.toString()));
        ItemStack item = (ItemStack) items.get(0);
        inventory.setItem(0, (ItemStack) item);
        int i = 0;
//        for (Object item : items.values()) {
//            inventory.setItem(i, item);
//            i++;
//        }
    }
}

