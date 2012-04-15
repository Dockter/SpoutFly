package net.Dockter.SpoutFly;

import java.util.logging.Level;
import org.bukkit.Bukkit;

public class Logger {
	
	private static final Logger logger = new Logger();
	
	private Logger() {
		
	}

	public static Logger getInstance() {
		return logger;
	}
	
	public void log(Level level, Object obj) {
		Bukkit.getLogger().log(level, "[SpoutFly] " + obj);
	}
	
	public void info(Object obj) {
		log(Level.INFO, obj);
	}
	
	public void warn(Object obj) {
		log(Level.WARNING, obj);
	}
	
	public void severe(Object obj) {
		log(Level.SEVERE, obj);
	}
	
	public void config(Object obj) {
		log(Level.CONFIG, obj);
	}
}
