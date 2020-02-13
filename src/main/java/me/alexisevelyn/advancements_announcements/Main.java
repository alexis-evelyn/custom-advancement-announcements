package me.alexisevelyn.advancements_announcements;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;

// Third Party Imports
import me.clip.placeholderapi.PlaceholderAPI;

public class Main extends JavaPlugin implements Listener {
	private boolean debug = true;
	private boolean essentials = false;
	
	// Getters and Setters
	
	public boolean isDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public boolean isEssentials() {
		return this.essentials;
	}

	public void setEssentials(boolean essentials) {
		this.essentials = essentials;
	}
	
	// Event Listeners
	
	@Override
    public void onEnable() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			/*
			 * We register the EventListeners here, when PlaceholderAPI is installed.
			 * Since all events are in the main class (this class), we simply use "this"
			 */
			Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
		
		if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
			getLogger().info("Essentials Found!!! Can Use Essentials' Placeholders!!!");
			this.setEssentials(true);
        } else {
            getLogger().info("Essentials Not Found!!! Cannot use Essentials Placeholder!!!");
        }
		
		// Register Commands
		this.getCommand("announce-advancements").setExecutor(new Commands());
		
		// Announce Successful Start
		getLogger().info("Custom Advancements Announcements has successfully started!!!");
	}
	
	@Override
    public void onDisable() {
		getLogger().info("Thank you for using the Custom Advancements Announcements plugin!!!");
	}
	
	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
		Advancement advancement = event.getAdvancement();
		String advancementMessage = ChatColor.WHITE + "%essentials_nickname%" + ChatColor.RESET + ChatColor.WHITE + " has made the advancement " + advancement.getKey().getKey() + ChatColor.RESET + ChatColor.WHITE + "!!!";
		
		// We parse the placeholders using "setPlaceholders"
		advancementMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), advancementMessage);
		
		if(false) {
			// Check if Advancement Gamerule is False.
			// TODO: Do this per world for announcement.
			// https://papermc.io/javadocs/paper/1.15/org/bukkit/GameRule.html#ANNOUNCE_ADVANCEMENTS
			return;
		}
		
		if(isDebug()) {
			getLogger().info(ChatColor.GREEN + "Debug");
			getLogger().info(ChatColor.GREEN + "---");
			
			for(String value : advancement.getCriteria()) { 
				getLogger().info(ChatColor.GREEN + "Key/Value: " + value);
			}
			
			getLogger().info(ChatColor.GREEN + "---");
			
			getLogger().info(ChatColor.GREEN + "Advancement Made: " + advancementMessage);
		}
		
		//event.setJoinMessage(joinText);
		getServer().broadcastMessage(advancementMessage);
	}
}
