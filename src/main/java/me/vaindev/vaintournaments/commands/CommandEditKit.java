package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
import me.vaindev.vaintournaments.guis.KitGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class CommandEditKit extends SubCommand {


    private final VainTournaments plugin;

    public CommandEditKit(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "editkit";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public String getRequiredPermission() {
        return "tournaments.staff";
    }

    @Override
    public List<String> getArguments(String[] args) {
        return new ArrayList<>();
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player))
            return false;

        player.openInventory(new KitGui(plugin).getInventory());

        return true;
    }
}
