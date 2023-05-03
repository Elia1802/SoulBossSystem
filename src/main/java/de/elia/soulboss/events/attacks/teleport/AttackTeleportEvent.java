package de.elia.soulboss.events.attacks.teleport;

import de.elia.systemclasses.keys.PluginKeys;
import de.elia.soulboss.entity.mobs.boss.magic.attack.Attack;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the Attack Teleport.
 */
public class AttackTeleportEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Attack Teleport.
   * @param event Requires the {@link EntityMoveEvent}
   */
  @EventHandler
  public void onTeleport(@NotNull EntityMoveEvent event){
    if (event.isCancelled())return;
    event.setCancelled(true);
    if (event.getEntity().getType() == EntityType.ZOMBIE) {
      if (event.getEntity().getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        Random random = new Random();
        int chance = random.nextInt(200);
        if (chance == 1) {
          new Attack().attackTeleport(event.getEntity());
        }
      }
    }
    event.setCancelled(false);
  }

}
