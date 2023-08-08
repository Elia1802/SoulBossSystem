package de.elia.systemclasses.messages;

import de.elia.api.messages.builder.MessageBuilder;
import de.elia.systemclasses.messages.prefix.PluginPrefix;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginMessages
  extends MessageBuilder {
  private final MiniMessage miniMessage = MiniMessage.miniMessage();
  private final PluginPrefix prefix = new PluginPrefix();

  public void broadcastWithPrefix(String message) {
    Bukkit.broadcast((Component)this.miniMessage.deserialize((this.prefix.prefix() + message)));
  }

  public void messageWithPrefix(@NotNull Player player, String message) {
    player.sendMessage(this.miniMessage.deserialize((this.prefix.prefix() + message)));
  }

  public void messageWithPrefix(@NotNull Player player, Component message) {
    player.sendMessage(this.prefix.prefix().append(message));
  }

  public void messageWithPrefix(@NotNull CommandSender sender, String message) {
    sender.sendMessage(this.miniMessage.deserialize((this.prefix.prefix() + message)));
  }
}
