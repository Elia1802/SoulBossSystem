package de.elia.soulboss.events.connections;

import de.elia.soulboss.commands.mob.SpawnMobCommand;
import de.elia.soulboss.fight.BossFightManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class DisconnectEvent implements Listener {

  @EventHandler
  public void onPlayerDisconnect(@NotNull PlayerQuitEvent event){
    BossFightManager bossFightManager = new BossFightManager();
    if (bossFightManager.playerHasStart(event.getPlayer())) {
      if (SpawnMobCommand.getBossFight() == null)return;
      SpawnMobCommand.getBossFight().stopFight(true);
    }
  }

}
