package de.elia.soulboss.fight.start;

import de.elia.soulboss.fight.Utils;
import de.elia.soulboss.messages.discord.Discord;
import de.elia.soulboss.messages.messages.CustomMessages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the start class for the Bossfight
 */
public class Start {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode start the Bossfight
   * @param player Requires a Player
   */
  public void start(@NotNull Player player){
    if (player == null)return;
    CustomMessages message = new CustomMessages();
    try {
      message.log(Level.INFO, "load Connection to DiscordWebhook!");
      URL url = new URL("");//TODO: CREATE CONFIGURATION FOR WEBHOOK URL
      message.log(Level.INFO, "Send Message to Discord");
      new Discord(url).message("In 1  1/2 Minuten wird ein Custom Mob gespawnt! @everyone");
    }catch (MalformedURLException exception){exception.printStackTrace();}
    message.log(Level.INFO, "Start countdown!");
    new Utils().start(11*20, player);
    message.messageWithPrefix(player, message.green("Der Boss Kampf wird in kürze beginnen"));
  }

}
