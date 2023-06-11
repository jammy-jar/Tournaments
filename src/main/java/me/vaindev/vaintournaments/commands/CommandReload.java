package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
import me.vaindev.vaintournaments.utils.StringFormat;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandReload extends SubCommand {

    private final VainTournaments plugin;

    public CommandReload(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
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
        return "tournaments.admin";
    }

    @Override
    public List<String> getArguments(String[] args) {
        return new ArrayList<>();
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        StringFormat.msg(sender,"&aPlugin was Reloaded!");
        this.plugin.reloadConfig();
        return true;
    }
}
