package me.alexisevelyn.advancements_announcements;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class ProtocolLibHandler {
	public boolean registerListeners(Main plugin) {
		// https://bukkit.org/threads/protocollib-packetlistener-not-working.236957/#post-2284387
		
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		manager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.CHAT) {
			@Override
			public void onPacketReceiving(PacketEvent event) {
				PacketContainer packet = event.getPacket();
				String message = packet.getStrings().read(0);
 
				if (message.contains("shit") || message.contains("fuck")) { 
					event.setCancelled(true);
					event.getPlayer().sendMessage("Bad manners!");
				}
            }
		});
		
		return true;
	}
}
