package de.elia.bossfightcreator.game.executer;

import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author Elia
 * @description This Event create a new {@link GameExecuter} for the {@link Player}.
 */
@Beta
public class NewGameExecuter implements Listener {
  
  public NewGameExecuter() {
    AnnotationChecker.processAnnotations(NewGameExecuter.class);
  }

  @EventHandler
  private void newGameExecuter(@NotNull PlayerInteractEvent event) {
    Player player = event.getPlayer();
    PluginMessages message = new PluginMessages();
    Map<Player, Integer> status = BossFightCreatorMain.playerStatusMap();
    if (ArenaHandler.isInit()) {
      if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.ZOMBIE_SPAWN_EGG))) {
        if (status.get(player) == 0) {
          new GameExecuter().init(player);
          event.setCancelled(true);
          player.getInventory().remove(event.getItem());
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Create a new GameExecuter for " + player.getName() + "...");
        } else {
          message.message(player, message.red("Du hast grade ein Spiel am laufen!"));
        }
      }
    } else {
      ArenaHandler.init();
      message.message(player, message.darkRed("Try again!!!"));
      return;
    }
  }
}
