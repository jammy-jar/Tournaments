package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
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

public class CommandKit extends SubCommand {


    private final VainTournaments plugin;

    public CommandKit(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "kit";
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
        if (args.length == 1)
            return null;
        return new ArrayList<>();
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        if (args.length != 1)
            return false;

        Player player1 = Bukkit.getPlayer(args[0]);
        if (player1 == null)
            return false;

        List<String> json = plugin.getConfig().getStringList("kit");
        for (String jsonObject: json) {
            Object obj = null;
            try {
                obj = new JSONParser().parse(jsonObject);
            } catch (ParseException e) {
                plugin.getLogger().warning("JSON Object could not be parsed!");
                return false;
            }

            JSONObject jo = (JSONObject) obj;
            int count = Integer.parseInt((String) jo.get("count"));
            String itemString = (String) jo.get("item");

            Material material = Material.matchMaterial(itemString);
            ItemStack item = new ItemStack(material, count);
            player1.getInventory().addItem(item);
        }

//        List<String> commands = plugin.getConfig().getStringList("commands");
//        for (:
//             ) {
//
//        }

        return true;
    }
}
