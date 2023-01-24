package de.elia.soulboss.events.spawn;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.fight.Utils;
import de.elia.soulboss.fight.start.Start;
import de.elia.soulboss.spawn.SpawnEgg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the SpawnEgg of the Boss
 */
public class ZombieSpawnEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Attack Fire.
   * @param event Requires the {@link PlayerInteractEvent}
   */
  @EventHandler
  public void onSpawnEgg(@NotNull PlayerInteractEvent event){
    Player player = event.getPlayer();
    if (player.getActiveItem() == new SpawnEgg(SoulBoss.soulBoss()).bossSpawnEgg()) {
      if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
        new Start().start(player);
        //TODO: ???Spawn a block at the player's location to stop the bossfight???
      }
    }
  }

}
