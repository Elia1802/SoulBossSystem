package de.elia.bossfightcreator.fight.events;

import de.elia.bossfightcreator.fight.game.Game;
import de.elia.bossfightcreator.fight.game.maps.GameMaps;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description If the {@link Player} leave the Server end all exist {@link Game}s of this {@link Player}
 */
public class LeaveEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description If the {@link Player} leave the Server end all exist {@link Game}s of this {@link Player}
   * @param event Requires the {@link PlayerQuitEvent}
   */
  @EventHandler
  public void onDisconnect(PlayerQuitEvent event){
    GameMaps.GAMES.forEach(game -> {
      if (game.player == event.getPlayer()) {
        game.removeGame();
      }
    });
  }
}
