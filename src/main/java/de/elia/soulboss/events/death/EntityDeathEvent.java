package de.elia.soulboss.events.death;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.fight.BossFight;
import de.elia.soulboss.items.DropUtils;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Zopnote, Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Load Class for the Main Class.
 */
public class EntityDeathEvent implements Listener {

  /**
   * @author Zopnote, Elia
   * @version 1.0
   * @since 1.0
   * @description This is event for the drops of the Boss
   * @param event Requires a {@link org.bukkit.event.entity.EntityDeathEvent}
   */
  //Zopnote starts...
  @EventHandler
  public void onBossDeath(org.bukkit.event.entity.@NotNull EntityDeathEvent event){
    if (event.getEntity().getPersistentDataContainer().has(new NamespacedKey(SoulBoss.soulBoss(), "680035753"))) {
      if (event.getEntityType() == EntityType.ZOMBIE) {
        Collection<ItemStack> itemStacks = event.getDrops();
        event.getDrops().removeAll(itemStacks);
        //Zopnote ends!
        //You have to let your own drops spawn after this one.
        //Elia starts...
        Location location = event.getEntity().getLocation();
        location.getWorld().strikeLightningEffect(location);
        new DropUtils().drop(location);
        //Elia ends!
        //Zopnote starts...
      }//If that doesn't work:
      event.setCancelled(true);
      //Zopnote ends!
      //Elia starts...
      BossFight.bossFight.stopFight(true);
      //Elia ends!
    }
  }

}
