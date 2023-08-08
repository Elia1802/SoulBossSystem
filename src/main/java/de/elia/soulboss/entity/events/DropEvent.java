package de.elia.soulboss.entity.events;

import de.elia.soulboss.entity.mobs.drop.DropUtils;
import de.elia.systemclasses.keys.PluginKeys;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class DropEvent implements Listener {
  @EventHandler
  public void onDeath(@NotNull EntityDeathEvent event) {
    if (event.getEntityType() == EntityType.ZOMBIE && event.getEntity().getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
      System.out.println("dfdfd");
      event.getDrops().clear();
      DropUtils.drop(event.getEntity().getLocation());
    }
  }
}
