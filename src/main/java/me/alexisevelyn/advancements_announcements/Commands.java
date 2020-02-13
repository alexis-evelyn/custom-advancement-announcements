package me.alexisevelyn.advancements_announcements;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.ChatColor;

public class Commands implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "Hello World!!!");
		return true;
	}
}
