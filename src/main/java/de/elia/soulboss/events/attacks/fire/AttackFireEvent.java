package de.elia.soulboss.events.attacks.fire;

import de.elia.soulboss.entity.mobs.boss.magic.attack.Attack;
import de.elia.systemclasses.keys.PluginKeys;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @description This event is the attack event for the boss
 */
public class AttackFireEvent implements Listener {

  @EventHandler
  public void onAttack(@NotNull EntityMoveEvent event) {
    if (event.isCancelled()) {
      return;
    }
    Random random = new Random();
    int fire = random.nextInt(200);
    int strikeLightning = random.nextInt(300);
    int teleport = random.nextInt(400);
    if (event.getEntity().getType() == EntityType.ZOMBIE && event.getEntity().getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
      if (fire == 1) {
        event.setCancelled(true);
        new Attack().attackFire(event.getEntity());
        event.setCancelled(false);
        return;
      }else if (strikeLightning == 1) {
        event.setCancelled(true);
        new Attack().attackStrikeLightning(event.getEntity());
        event.setCancelled(false);
        return;
      }else if (teleport == 1) {
        event.setCancelled(true);
        new Attack().attackTeleport(event.getEntity());
        event.setCancelled(false);
        return;
      }
    }
  }
}
