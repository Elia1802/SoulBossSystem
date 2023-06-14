package de.elia.systemclasses.messages;

import de.elia.systemclasses.messages.prefix.PluginPrefix;
import de.elia.api.messages.builder.MessageBuilder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @extends {@link MessageBuilder}
 * @description This is the MessageClass for this Plugin.
 */
public class PluginMessages extends MessageBuilder {

  private final MiniMessage miniMessage = MiniMessage.miniMessage();
  private final PluginPrefix prefix = new PluginPrefix();

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode send a broadcast to all players.
   * @param message Requires the broadcast message.
   */
  @Override
  public void broadcastWithPrefix(String message) {
    Bukkit.broadcast(this.miniMessage.deserialize(prefix.prefix() + message));
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode send a message to a player.
   * @param player Requires a Player.
   * @param message Requires the message.
   */
  @Override
  public void messageWithPrefix(@NotNull Player player, String message) {
    player.sendMessage(this.miniMessage.deserialize(prefix.prefix() + message));
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode send a message to a player.
   * @param player Requires a Player.
   * @param message Requires the message.
   */
  @Override
  public void messageWithPrefix(@NotNull Player player, Component message) {
    player.sendMessage(this.prefix.prefix().append(message));
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This Methode send a message to a CommmandSender.
   * @param sender Requires a CommandSender.
   * @param message Requires the message.
   */
  @Override
  public void messageWithPrefix(@NotNull CommandSender sender, String message) {
    sender.sendMessage(this.miniMessage.deserialize(prefix.prefix() + message));
  }

}
