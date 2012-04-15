package net.Dockter.SpoutFly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;


public class EventListener implements Listener {
	
	
	private final Logger logger = Logger.getInstance();
	
	
	@EventHandler
	
	public void onInput(KeyPressedEvent event) {
		if(event.getKey() == Keyboard.KEY_F12) {  //This needs to equal what ever the keybind is currently set to.
			
			//enableFly(player);
			
			
		}
	}
}
