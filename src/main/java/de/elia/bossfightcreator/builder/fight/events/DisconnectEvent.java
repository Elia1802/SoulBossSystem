package de.elia.bossfightcreator.builder.fight.events;

import com.sk89q.worldedit.WorldEditException;
import de.elia.bossfightcreator.builder.fight.game.Game;
import de.elia.bossfightcreator.builder.fight.game.maps.GameList;
import de.elia.systemclasses.logging.PluginLogger.SaveError;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

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
        try {
          game.killGame();
        } catch (SoulBossSystemNullException exception) {
          new SaveError().saveError(exception, "PlayerQuitEvent-onPlayerQuitServer-line_35=null");
          exception.stacktrace();
        }catch (IOException | WorldEditException exception) {
          new SaveError().saveError(exception, "PlayerQuitEvent-onPlayerQuitServer-line_35=worldedit_or_io");
          exception.printStackTrace();
        }
      }
    });
  }

}
