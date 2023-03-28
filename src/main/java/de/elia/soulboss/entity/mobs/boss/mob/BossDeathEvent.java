package de.elia.soulboss.entity.mobs.boss.mob;

import de.elia.bossfightcreator.fight.game.maps.GameMaps;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.items.DropUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class BossDeathEvent implements Listener {

  @EventHandler
  public void onBossDeath(@NotNull EntityDeathEvent event) {
    if (event.getEntityType() == EntityType.ZOMBIE) {
      var bossKey = GameMaps.BOSS_KEY.get(event.getEntity());
      if (event.getEntity().getPersistentDataContainer().has(new NamespacedKey(SoulBoss.main(), "isZombieBoss"))) {
        Bukkit.getLogger().severe(bossKey);
        event.getEntity().remove();
        Collection<ItemStack> itemStacks = event.getDrops();
        event.getDrops().removeAll(itemStacks);
        //Zopnote ends!
        //You have to let your own drops spawn after this one.
        //Elia starts...
        Location location = event.getEntity().getLocation();
        location.getWorld().strikeLightningEffect(location);
        new DropUtils().drop(location);
        //Elia ends!
      }
    }
  }
}

