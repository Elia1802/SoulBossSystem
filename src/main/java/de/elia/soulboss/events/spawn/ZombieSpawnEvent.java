package de.elia.soulboss.events.spawn;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.fight.BossFight;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulboss.spawn.SpawnEgg;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the SpawnEgg of the Boss
 */
public class ZombieSpawnEvent implements Listener {

  private BossFight bossFight;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Attack Fire.
   */
  @EventHandler
  public void onSpawnEgg(@NotNull PlayerInteractEvent event){
    Player player = event.getPlayer();
    SpawnEgg spawnEgg = new SpawnEgg(SoulBoss.soulBoss());
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (player.getInventory().getItemInMainHand().getItemMeta() == null)return;
      if (player.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
        System.out.println("tgdgrdfbdfg");
      }
    }
  }
}
