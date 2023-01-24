package de.elia.soulboss.messages.messages;

import de.elia.soulmain.SoulMain;
import de.elia.soulmain.allplugins.messages.builder.MessageBuilder;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link MessageBuilder}
 * @description This is the MessageClass for this Plugin.
 */
public class CustomMessages extends MessageBuilder {

  public CustomMessages(){

  }

  private final MiniMessage miniMessage = SoulMain.getMiniMessage();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode set a prefix for this Plugin
   * @return string
   */
  public String prefix(){
    String string = "<dark_gray>[</dark_gray><dark_purple>Soul-BossFight</dark_purple><dark_gray>]</dark_gray> ";
    return string;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode send a broadcast to all players.
   * @param message Requires the broadcast message.
   */
  @Override
  public void broadcastWithPrefix(String message) {
    Bukkit.broadcast(this.miniMessage.deserialize(this.prefix() + message));
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This Methode send a message to a player.
   * @param player Requires a Player.
   * @param message Requires the message.
   */
  @Override
  public void messageWithPrefix(@NotNull Player player, String message) {
    player.sendMessage(this.miniMessage.deserialize(this.prefix() + message));
  }

}
