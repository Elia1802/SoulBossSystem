package de.elia.bossfightcreator.events.connect;

import de.elia.systemclasses.messages.PluginMessages;
import de.elia.bossfightcreator.BossFightCreatorMain;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description Set the Bossfight status of the player to 0 (ready)
 */
public class ConnectionEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set the Bossfight status of the player to 0 (ready)
   * @param event Requires the {@link PlayerLoginEvent}
   */
  @EventHandler
  public void onConnection(@NotNull PlayerLoginEvent event){
    PluginMessages message = new PluginMessages();
    var log = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    Player player = event.getPlayer();
    var status = BossFightCreatorMain.playerStatusMap();
    if (status.get(player) == null) {
      status.put(player, 0);
      log.logInfo(player.getName() + " has status 0!");
    }else {
      log.logError("This player (" + player + ") has a exist Status!");
      log.logWarning("Status of the Player set to 0!");
      status.replace(player, 0);
    }
  }

}
