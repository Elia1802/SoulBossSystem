package de.elia.soulboss.events.achievements.bossfight;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.achievement.process.BossFightAchievements;
import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the Achievement BOSSFIGHT.
 */
public class AchievementBossFightEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Achievement BOSSFIGHT.
   * @param event Requires the {@link EntityDamageByEntityEvent}
   */
  @EventHandler
  public void onBossFight(@NotNull EntityDamageByEntityEvent event){
    Entity damagedEntity = event.getDamager();
    Entity damagerEntity = event.getEntity();
    if (damagerEntity instanceof Player player) {
      if (damagedEntity instanceof Zombie) {
        if (damagedEntity.getPersistentDataContainer().has(new NamespacedKey(SoulBoss.main(), "680035753"))) {
          new BossFightAchievements(SoulBoss.main()).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT);
        }
      }
    }
  }

}
