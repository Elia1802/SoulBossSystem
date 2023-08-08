package de.elia.bossfightcreator.events.connect;

import de.elia.api.logging.PluginLogger;

import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Elia
 * @description This event set the {@link Player} a status if the {@link Player} login in the Server.
 */
public class ConnectionEvent implements Listener {

  @EventHandler
  public void onConnection(@NotNull PlayerLoginEvent event) {
    PluginMessages message = new PluginMessages();
    PluginLogger log = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    Player player = event.getPlayer();
    Map<Player, Integer> status = BossFightCreatorMain.playerStatusMap();
    if (status.get(player) == null) {
      status.put(player, 0);
      log.logInfo(player.getName() + " has status 0!");
    } else {
      log.logError("This player (" + player + ") has a exist Status!");
      log.logWarning("Status of the Player set to 0!");
      status.replace(player, 0);
    }
  }
}
