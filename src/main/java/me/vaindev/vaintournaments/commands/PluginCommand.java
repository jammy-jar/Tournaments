package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
import me.vaindev.vaintournaments.utils.StringFormat;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PluginCommand implements TabExecutor {

    private final List<SubCommand> subCommands = new ArrayList<>();

    private final VainTournaments plugin;
    public PluginCommand(VainTournaments plugin) {
        this.plugin = plugin;

        this.subCommands.add(new CommandReload(plugin));
        this.subCommands.add(new CommandDuel(plugin));
        this.subCommands.add(new CommandSetPositions(plugin));
        this.subCommands.add(new CommandKit(plugin));
        this.subCommands.add(new CommandEditKit(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        if (args.length < 1) {
            StringFormat.msg(sender, "&cInvalid Command!");
            return true;
        }
        for (SubCommand subCommand: getSubCommands())
            if (args[0].equalsIgnoreCase(subCommand.getName())) {
                if (sender.hasPermission(subCommand.getRequiredPermission())) {
                    if (subCommand.run(sender, Arrays.copyOfRange(args, 1, args.length)))
                        return true;
                } else {
                    StringFormat.msg(sender, "&cYou have insufficient permissions to do this!");
                    return true;
                }
            }

        StringFormat.msg(sender, "&cInvalid Command!");
        return true;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 1) {
            Set<String> subCommandNames = new HashSet<>();

            getSubCommands().forEach(command -> {
                if (sender.hasPermission(command.getRequiredPermission()))
                    subCommandNames.add(command.getName());
            });

            options.addAll(StringUtil.copyPartialMatches(args[0], subCommandNames, new ArrayList<>()));
        } else {
            for (SubCommand subCommand : getSubCommands()) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    if (subCommand.getArguments(Arrays.copyOfRange(args, 1, args.length)) == null) {
                        options = null;
                    } else {
                        options.addAll(StringUtil.copyPartialMatches(args[args.length - 1], subCommand.getArguments(Arrays.copyOfRange(args, 1, args.length)), new ArrayList<>()));
                    }
                }
            }
        }

        return options;
    }
}
