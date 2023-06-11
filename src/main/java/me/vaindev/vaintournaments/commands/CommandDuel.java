package me.vaindev.vaintournaments.commands;

import me.vaindev.vaintournaments.VainTournaments;
import me.vaindev.vaintournaments.utils.StringFormat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandDuel extends SubCommand {

    private final VainTournaments plugin;

    public CommandDuel(VainTournaments plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "duel";
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
        if (args.length == 1 || args.length == 2)
            return null;
        return new ArrayList<>();
    }

    @Override
    public boolean run(CommandSender sender, String[] args) {
        if (args.length != 2)
            return false;

        Player player1 = Bukkit.getPlayer(args[0]);
        Player player2 = Bukkit.getPlayer(args[1]);
        if (player1 == null || player2 == null)
            return false;

        if (!(sender instanceof Player playerSender))
            return false;

        if (plugin.getDuelManager().getCurrentDuel() != null)
            return false;

        List<Double> pos1 = plugin.getConfig().getDoubleList("position1");
        Location location1 = new Location(playerSender.getWorld(), pos1.get(0), pos1.get(1), pos1.get(2), pos1.get(3).floatValue(), 0);
        List<Double> pos2 = plugin.getConfig().getDoubleList("position2");
        Location location2 = new Location(playerSender.getWorld(), pos2.get(0), pos2.get(1), pos2.get(2), pos2.get(3).floatValue(), 0);

        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        int task = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            player1.teleport(location1);
            player2.teleport(location2);
        }, 0,5);
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            Bukkit.getScheduler().cancelTask(task);
        }, 100);

        player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l5"));
        player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l5"));

        Location center = new Location(playerSender.getWorld(), pos1.get(0) + (pos2.get(0) - pos1.get(0)) / 2, pos1.get(1) + (pos2.get(1) - pos1.get(1)) / 2, pos1.get(2) + (pos2.get(2) - pos1.get(2)) / 2);

        for (Player player : center.getWorld().getPlayers()) {
            player.playSound(center, Sound.UI_BUTTON_CLICK, 1.0f, 1.1f);
        }

        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l4"));
            player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l4"));
        },20);
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l3"));
            player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l3"));
            StringFormat.broadcast(plugin.getServer(), "&eThe round begins in 3 seconds!");

        }, 40);

//        List<String> json = plugin.getConfig().getStringList("kit");
//        for (String jsonObject: json) {
//            Object obj = null;
//            try {
//                obj = new JSONParser().parse(jsonObject);
//            } catch (ParseException e) {
//                return false;
//            }
//
//            JSONObject jo = (JSONObject) obj;
//
//            int count = (int) jo.get("count");
//            String itemString = (String) jo.get("item");
//
//            Material material = Material.matchMaterial(itemString);
//            ItemStack item = new ItemStack(material, count);
//            player1.getInventory().addItem(item);
//            player2.getInventory().addItem(item);
//        }

        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l2"));
            player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l2"));
            StringFormat.broadcast(plugin.getServer(), "&eThe round begins in 2 seconds!");

        }, 60);
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l1"));
            player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&l1"));
            StringFormat.broadcast(plugin.getServer(), "&eThe round begins in 1 second!");

        }, 80);
        scheduler.scheduleSyncDelayedTask(plugin, () -> {
            player1.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&lGO!"));
            player2.sendTitlePart(TitlePart.TITLE, StringFormat.formatString("&a&lGO!"));

            StringFormat.broadcast(plugin.getServer(), "&a&lThe round has begun!");

            playerSender.getWorld().strikeLightning(center);
        }, 100);

        return true;
    }
}
