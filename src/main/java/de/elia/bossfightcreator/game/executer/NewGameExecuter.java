package de.elia.bossfightcreator.game.executer;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import org.jetbrains.annotations.NotNull;

public class NewGameExecuter implements Listener {

  @EventHandler
  private void newGameExecuter(@NotNull PlayerInteractEvent event){
    Player player = event.getPlayer();
    PluginMessages message = new PluginMessages();
    var status = BossFightCreatorMain.playerStatusMap();
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.ZOMBIE_SPAWN_EGG))) {
        if (status.get(player) == 0) {
          new GameExecuter(BossFightCreatorMain.bossFightCreator().main()).init(player);
          player.getInventory().remove(event.getItem());
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Create a new GameExecuter for " + player.getName() + "...");
        }else {
          message.message(player, message.red("Du hast grade ein Spiel am laufen!"));
        }
      }
    }
  }

}
