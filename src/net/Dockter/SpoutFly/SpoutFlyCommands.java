package net.Dockter.SpoutFly;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SpoutFlyCommands {

    String pre = ChatColor.YELLOW + "[SpoutFly] ";
    ChatColor yellow = ChatColor.YELLOW;
    ChatColor green = ChatColor.GREEN;
    ChatColor white = ChatColor.WHITE;
    ChatColor red = ChatColor.RED;
    public SpoutFly plugin;

    public SpoutFlyCommands(SpoutFly instance) {
        plugin = instance;
    }

    // Help Command
    public void helpCommand(CommandSender sender, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }

        sender.sendMessage(pre + green + "Help Menu!");
        sender.sendMessage(yellow + "/spoutfly " + green + "list - " + yellow + "List players who have SpoutFly enabled.");
        sender.sendMessage(yellow + "/spoutfly " + green + "toggle [player] - " + yellow + "Toggles SpoutFly.");
        sender.sendMessage(yellow + "/spoutfly " + green + "check [player] - " + yellow + "Checks SpoutFly status.");
        sender.sendMessage(yellow + "/spoutfly " + green + "on [player] - " + yellow + "Enable SpoutFly.");
        sender.sendMessage(yellow + "/spoutfly " + green + "off [player] - " + yellow + "Disabled SpoutFly.");

    }

    // Toggle Command
    public void toggleCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change SpoutFly mode while in creative!");
                return;
            }

            if (player.getAllowFlight()) {
                disableFly(player);
                player.sendMessage(pre + red + "SpoutFly disabled!");
                return;
            } else {
                enableFly(player);
                player.sendMessage(pre + green + "SpoutFly enabled!");
                return;
            }

        }

        if (args.length == 2 && sender.hasPermission("spoutfly.toggle.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit SpoutFly for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (target.getAllowFlight()) {
                    disableFly(target);

                    if (target.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage(pre + red + "SpoutFly disabled!");
                        return;
                    }

                    sender.sendMessage(pre + red + "SpoutFly disabled for " + target.getName());
                    target.sendMessage(pre + red + "SpoutFly disabled by " + sender.getName());
                    return;
                } else {
                    enableFly(target);

                    if (target.getName().equalsIgnoreCase(sender.getName())) {
                        sender.sendMessage(pre + green + "SpoutFly enabled!");
                        return;
                    }

                    sender.sendMessage(pre + green + "SpoutFly enabled for " + target.getName());
                    target.sendMessage(pre + green + "SpoutFly enabled by " + sender.getName());
                    return;
                }

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }
        
        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }
        
        sender.sendMessage(red + "You do not have permission to do that...");
        return;

    }

    public void flyOn(CommandSender sender, String[] args) {

        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change SpoutFly while in creative mode!");
                return;
            }

            if (flyModeEnabled(player)) {
                player.sendMessage(pre + red + "SpoutFly on!");
                return;
            }

            enableFly(player);
            sender.sendMessage(pre + green + "SpoutFly enabled!");
            return;

        }

        if (args.length == 2 && sender.hasPermission("spoutfly.on.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit SpoutFly for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (flyModeEnabled(target)) {
                    sender.sendMessage(pre + red + "SpoutFly already on for " + target.getName());
                    return;
                }

                enableFly(target);

                if (target.getName().equalsIgnoreCase(sender.getName())) {
                    sender.sendMessage(pre + green + "SpoutFly enabled!");
                    return;
                }

                sender.sendMessage(pre + green + "SpoutFly enabled for " + target.getName());
                target.sendMessage(pre + green + "SpoutFly enabled by " + sender.getName());
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }
        
        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");
        return;

    }

    public void flyOff(CommandSender sender, String[] args) {

        if (args.length == 1) {

            Player player = (Player) sender;

            if (isCreative(player)) {
                player.sendMessage(pre + red + "Cannot change flight while in creative!");
                return;
            }

            if (!flyModeEnabled(player)) {
                player.sendMessage(pre + red + "SpoutFly already off!");
                return;
            }

            disableFly(player);
            sender.sendMessage(pre + red + "SpoutFly disabled!");
            return;

        }

        if (args.length == 2 && sender.hasPermission("spoutfly.off.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + "Can't edit SpoutFly for " + target.getName() + ", they are in creative mode!");
                    return;
                }

                if (!flyModeEnabled(target)) {
                    sender.sendMessage(pre + red + "SpoutFly already off for " + target.getName());
                    return;
                }

                disableFly(target);

                if (target.getName().equalsIgnoreCase(sender.getName())) {
                    sender.sendMessage(pre + red + "SpoutFly disabled!");
                    return;
                }

                sender.sendMessage(pre + red + "SpoutFly disabled for " + target.getName());
                target.sendMessage(pre + red + "SpoutFly disabled by " + sender.getName());
                return;

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }
        }
        
        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }
        
        sender.sendMessage(red + "You do not have permission to do that...");
        return;

    }

    public void checkCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 1) {

            if (isCreative(player)) {
                sender.sendMessage(pre + red + "You are in creative, of course mode is enabled!");
                return;
            }

            if (flyModeEnabled(player)) {
                sender.sendMessage(pre + green + "SpoutFly is enabled!");
                return;
            }

            sender.sendMessage(pre + red + "SpoutFly disabled!");
            return;

        }

        if (args.length == 2 && sender.hasPermission("spoutfly.check.other")) {

            Player target = Bukkit.getServer().getPlayer(args[1]);

            try {

                if (isCreative(target)) {
                    sender.sendMessage(pre + red + target.getName() + " is in creative, of course flight is enabled!");
                    return;
                }

                if (flyModeEnabled(target)) {
                    sender.sendMessage(pre + green + target.getName() + " has SpoutFly enabled!");
                    return;
                } else {
                    sender.sendMessage(pre + red + target.getName() + " has SpoutFly disabled!");
                    return;
                }

            } catch (Exception e) {
                sender.sendMessage(pre + red + "Player not online!");
                return;
            }

        }
        
        if (args.length > 2) {
            sender.sendMessage(pre + red + "Too many arguments!");
        }

        sender.sendMessage(red + "You do not have permission to do that...");
        return;

    }

    public void listCommand(CommandSender sender, String[] args) {

        StringBuilder s = new StringBuilder();

        if (args.length != 1) {
            sender.sendMessage(pre + red + "Too many arguments!");
            return;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {

            if (s.length() > 0) {
                s.append(white + ", ");
            }

            if (p.getAllowFlight()) {
                s.append(green + p.getName());
            }
        }
        sender.sendMessage(pre + green + "SpoutFly Enabled: " + s);
        return;


    }

    public void enableFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    public void disableFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public boolean flyModeEnabled(Player player) {

        if (player.getAllowFlight()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean currentlyFlying(Player player) {

        if (player.isFlying()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isCreative(Player player) {

        if (player.getGameMode() == GameMode.CREATIVE) {
            return true;
        } else {
            return false;
        }
    }
}
