package me.prouser123.bungee.discord.base;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import me.prouser123.bungee.discord.Discord;
import me.prouser123.bungee.discord.Main;

public interface GenerateOnDemand {
	
	final String GoDPrefix = "GoD.";
	
	default boolean isGoD(String helpMessage) {
		return (helpMessage.startsWith(this.GoDPrefix));
	}
	
	default void runGoD(String[] split, EmbedBuilder embed, MessageCreateEvent event) {
		String[] splitGOD = split[1].split(this.GoDPrefix);
		Main.inst().getDebugLogger().info("[runGOD] " + "ITEM1: " + splitGOD[1]);
		GenerateOnDemand.validCommands.check(split, splitGOD, embed, event);
	}
	
	
	class validCommands {
		
		private static void check(String[] split, String[] splitGOD, EmbedBuilder embed, MessageCreateEvent event) {
			if (splitGOD[1].equalsIgnoreCase("stealAvatar")) {
				GenerateOnDemand.validCommands.stealAvatar(split, embed, event);
			} else {
				return;
			}
		}
		
		private static void stealAvatar(String[] split, EmbedBuilder embed, MessageCreateEvent event) {
			Main.inst().getDebugLogger().info("Running [runGOD.stealAvatar]");
			embed.addField(split[0], "Replace my avatar with my owner's  (" + Discord.getBotOwner(event) + ") avatar.");
		}
	}
}