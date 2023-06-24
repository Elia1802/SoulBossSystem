package de.elia.items.events.join;

import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @description This event give maybe the player by the join the recipe
 */
public class RecipeEvent implements Listener {

  @EventHandler
  private void onJoin(@NotNull PlayerJoinEvent event) {
    PluginMessages message = new PluginMessages();
    Player player = event.getPlayer();
    Random random = new Random();
    int chance = random.nextInt(100);
    if (chance == 1) {
      message.message(player, message.white("[Gott] Sieh dir das an und glaub mir..."));
      message.message(player, message.white("[Gott] https://youtu.be/9akTEno-X7Y"));
    }
  }

}
