package me.alexisevelyn.advancements_announcements;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.clip.placeholderapi.PlaceholderAPI;

public class Main extends JavaPlugin implements Listener {
	
	@Override
    public void onEnable() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			/*
			 * We register the EventListeneres here, when PlaceholderAPI is installed.
			 * Since all events are in the main class (this class), we simply use "this"
			 */
			Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
		
		this.getCommand("announce-advancements").setExecutor(new Commands());
	}
	
	@Override
    public void onDisable() {
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		String joinText = "&f%essentials_nickname%&r&f joined the server!!!";

		// We parse the placeholders using "setPlaceholders"
		joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);

		event.setJoinMessage(joinText);
    }
}
