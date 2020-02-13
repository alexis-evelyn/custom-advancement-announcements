package me.alexisevelyn.advancements_announcements;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import org.bukkit.ChatColor;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import me.clip.placeholderapi.PlaceholderAPI;

public class ProtocolLibHandler {
	public boolean registerListeners(Main plugin, final Logger logger) {
		// https://bukkit.org/threads/protocollib-packetlistener-not-working.236957/#post-2284387
		
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		manager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.ADVANCEMENTS) {
			@Override
			public void onPacketSending(PacketEvent event) {
				PacketContainer packet = event.getPacket();
				
				if (event.getPacketType() == PacketType.Play.Server.ADVANCEMENTS) {
					event.setCancelled(false);
					
					StructureModifier<String> text = packet.getStrings();
					StructureModifier<Boolean> booleans = packet.getBooleans();
					
					StructureModifier<WrappedChatComponent> chat = packet.getChatComponents();
					
					logger.info(ChatColor.RED + "String Size: " + text.size());
					for(int x = 0; x < text.size(); x++) {
						logger.info(ChatColor.RED + "String: " + text.readSafely(x));
					}
					
					logger.info(ChatColor.AQUA + "Booleans Size: " + booleans.size());
					for(int x = 0; x < booleans.size(); x++) {
						logger.info(ChatColor.AQUA + "Boolean: " + booleans.readSafely(x));
					}
					
					logger.info(ChatColor.GOLD + "Chat Size: " + chat.size());
					for(int x = 0; x < chat.size(); x++) {
						logger.info(ChatColor.GOLD + "Chat: " + chat.readSafely(x));
					}
					
//					String advancementMessage = ChatColor.WHITE + "%essentials_nickname%" + ChatColor.RESET + ChatColor.WHITE + " has made the advancement " + "advancement.getKey().getKey()" + ChatColor.RESET + ChatColor.WHITE + "!!!";
//					
//					// We parse the placeholders using "setPlaceholders"
//					advancementMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), advancementMessage);
//					
//					getServer().broadcastMessage(advancementMessage);
//		        }
			}
		});
		
		return true;
	}
}
