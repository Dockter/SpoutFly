package net.Dockter.SpoutFly;

import java.io.IOException;

import net.Dockter.SpoutFly.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;


public class SpoutFly extends JavaPlugin {

    private SpoutFlyCommands cHandler = new SpoutFlyCommands(this);
	private final PluginManager pluginManager = Bukkit.getPluginManager();
	private final Logger logger = Logger.getInstance();
	
    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
    	if (pluginManager.getPlugin("Spout") == null || !pluginManager.isPluginEnabled("Spout")) {
			logger.info("TextureMe requires SpoutPlugin to run, please download Spout at http://get.spout.org - Shutting down TextureMe...");
			pluginManager.disablePlugin(this);
			return;
		}      
    	getCommand("spoutfly").setExecutor(this);
      
    	// Register Listener Events
    	pluginManager.registerEvents(new EventListener(), this);
    	
    	// Register key binding, Below line is incomplete.
    			// SpoutManager.getKeyBindingManager().registerBinding("spoutfly_toggle_key", Keyboard.KEY_RCONTROL, "Toggles SpoutFly", new SelectorBindingDelegate(this), this);
    }
    

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spoutfly")) {
            return doCommand(sender, args);
        }

        return onCommand(sender, cmd, commandLabel, args);
    }

       
    private boolean doCommand(CommandSender sender, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(cHandler.pre + ChatColor.GREEN + "");
            sender.sendMessage(cHandler.yellow + "- To view commands, do /flight " + cHandler.green + "help");
            return true;
        }

        // Help Command

        if ("help".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.help")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.helpCommand(sender, args);
            return true;

        }

        // Toggle Command

        if ("toggle".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.toggle")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.toggleCommand(sender, args);
            return true;
        }

        // On Command
        
        if ("on".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.on")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.flyOn(sender, args);
            return true;

        }

        // Off Command
        
        if ("off".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.off")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.flyOff(sender, args);
            return true;

        }

        // Check Command

        if ("check".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.check")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.checkCommand(sender, args);
            return true;
        }

        // List Command

        if ("list".equalsIgnoreCase(args[0])) {

            if (!sender.hasPermission("spoutfly.list")) {
                noPerms(sender);
                return true;
            }

            this.cHandler.listCommand(sender, args);
            return true;

        }

        return false;
    }

    public void noPerms(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You do not have permission for that command...");
    }

}