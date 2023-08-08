package de.elia.soulboss.events;

import de.elia.soulboss.entity.mobs.boss.magic.attack.Attack;
import de.elia.systemclasses.keys.PluginKeys;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class AttackEvent implements Listener {

  @EventHandler
  public void onAttack(@NotNull EntityMoveEvent event) {
    if (event.isCancelled()) {
      return;
    }
    Random random = new Random();
    int fire = random.nextInt(300);
    int strikeLightning = random.nextInt(400);
    int teleport = random.nextInt(500);
    if (event.getEntity().getType() == EntityType.ZOMBIE && event.getEntity().getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
      if (fire == 1) {
        event.setCancelled(true);
        Attack.attackFire(event.getEntity());
        event.setCancelled(false);
        return;
      }
      if (strikeLightning == 1) {
        event.setCancelled(true);
        Attack.attackStrikeLightning(event.getEntity());
        event.setCancelled(false);
        return;
      }
      if (teleport == 1) {
        event.setCancelled(true);
        Attack.attackTeleport(event.getEntity());
        event.setCancelled(false);
        return;
      }
    }
  }
}
