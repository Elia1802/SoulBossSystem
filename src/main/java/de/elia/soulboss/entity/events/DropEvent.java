package de.elia.soulboss.entity.events;

import de.elia.systemclasses.keys.PluginKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This event cancel the drops.
 * @implements {@link Listener}
 */
public class DropEvent implements Listener {

  @EventHandler
  public void onDeath(@NotNull EntityDeathEvent event){
    if (event.getEntity().getPersistentDataContainer().has(PluginKeys.RECIPE_KEY.key())) {
      event.getDrops().clear();
    }
  }

}
