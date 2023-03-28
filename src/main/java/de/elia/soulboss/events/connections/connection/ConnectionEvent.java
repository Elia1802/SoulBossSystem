package de.elia.soulboss.events.connections.connection;

import de.elia.CustomMessages;
import de.elia.bossfightcreator.BossFightCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
   * @param event Requires the {@link PlayerJoinEvent}
   */
  @EventHandler
  public void onConnection(@NotNull PlayerJoinEvent event){
    CustomMessages message = new CustomMessages();
    Player player = event.getPlayer();
    var status = BossFightCreator.playerStatusMap();
    if (status.get(player) == null) {
      status.put(player, 0);
    }else {
      message.errorLog("This player (" + player + ") has a exist Status!");
      message.warningLog("Status of the Player set to 0!");
      status.replace(player, 0);
    }
  }

}
