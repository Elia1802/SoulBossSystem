package de.elia.bossfightcreator.events.disconnect;

import de.elia.bossfightcreator.game.Game;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description If the {@link Player} leave the Server end the excist {@link Game} of this {@link Player}
 */
@Deprecated
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
    //TODO COMING SOON!!!
    /**GameList.GAMES.forEach(game -> {
      if (game.player == event.getPlayer()) {
        try {
          game.removeGame(Bukkit.getWorld("world").getSpawnLocation());
        } catch (SoulBossSystemNullException exception) {
          new SaveError().saveError(exception, "PlayerQuitEvent-onPlayerQuitServer-line_35=null");
          exception.stacktrace();
        }catch (IOException | WorldEditException exception) {
          new SaveError().saveError(exception, "PlayerQuitEvent-onPlayerQuitServer-line_35=worldedit_or_io");
          exception.printStackTrace();
        }
      }
    });**/
  }

}
