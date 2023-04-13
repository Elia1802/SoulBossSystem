package de.elia.bossfightcreator.builder.fight.events;

import de.elia.bossfightcreator.builder.fight.game.Game;
import de.elia.bossfightcreator.builder.fight.game.maps.GameList;
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
public class DisconnectEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description If the {@link Player} leave the Server end all exist {@link Game}s of this {@link Player}
   * @param event Requires the {@link PlayerQuitEvent}
   */
  @EventHandler
  public void onPlayerQuitServer(PlayerQuitEvent event){
    GameList.GAMES.forEach(game -> {
      if (game.player == event.getPlayer()) {
        game.killGame();
      }
    });
  }

}
