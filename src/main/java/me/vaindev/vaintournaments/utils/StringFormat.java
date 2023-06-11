package me.vaindev.vaintournaments.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

public class StringFormat {

    public static void msg(CommandSender sender, String message) {
        if (message != null && !message.isEmpty()) {
            sender.sendMessage(formatString(message));
        }
    }

    public static void msg(CommandSender sender, Component message) {
        sender.sendMessage(message);
    }

    public static void broadcast(Server server, String message) {
        if (message != null && !message.isEmpty()) {
            Component formattedMsg = formatString(message);
            server.broadcast(formattedMsg);
        }
    }

    public static void broadcast(Server server, Component message) {
        server.broadcast(message);
    }

    public static Component formatString(String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }

}