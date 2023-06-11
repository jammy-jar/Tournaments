package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
import me.vaindev.vaintournaments.utils.StringFormat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandSetPositions extends SubCommand {

    private final VainTournaments plugin;

    public CommandSetPositions(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "setpositions";
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
            return List.of("pos1", "pos2");
        if (args.length == 2 || args.length == 3 || args.length == 4)
            return List.of("~");
        return new ArrayList<>();
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        if (args.length != 4)
            return false;

        double x;
        double y;
        double z;

        try {
            if (args[1].equalsIgnoreCase("~") && sender instanceof Player player)
                x = player.getLocation().getX();
            else
                x = Double.parseDouble(args[1]);
            if (args[2].equalsIgnoreCase("~") && sender instanceof Player player)
                y = player.getLocation().getY();
            else
                y = Double.parseDouble(args[2]);
            if (args[3].equalsIgnoreCase("~") && sender instanceof Player player)
                z = player.getLocation().getZ();
            else
                z = Double.parseDouble(args[3]);
        } catch (NumberFormatException e) {
            return false;
        }

        double[] pos = { x, y, z };

        if (args[0].equalsIgnoreCase("pos1")) {
            plugin.getConfig().set("position1", pos);
            StringFormat.msg(sender, "&aPosition 1 successfully set to: " + x + ", " + y + ", " + z);
        } else if (args[0].equalsIgnoreCase("pos2")) {
            plugin.getConfig().set("position2", pos);
            StringFormat.msg(sender, "&aPosition 2 successfully set to: " + x + ", " + y + ", " + z);
        } else {
            return false;
        }

        plugin.saveConfig();
        plugin.reloadConfig();
        return true;
    }
}
