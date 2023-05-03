package de.elia.soulboss.events.achievements.bossfightzombieend;

import de.elia.systemclasses.keys.PluginKeys;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.achievement.process.BossFightAchievements;
import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the Achievement BOSSFIGHT_ZOMBIE_END.
 */
public class AchievementBossFightZombieEndEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Achievement BOSSFIGHT_ZOMBIE_END.
   * @param event Requires the {@link EntityDeathEvent}
   */
  @EventHandler
  public void onBossFightZombieEnd(@NotNull EntityDeathEvent event){
    Entity entity = event.getEntity();
    EntityDamageEvent damageEvent = entity.getLastDamageCause();
    if (damageEvent instanceof EntityDamageByEntityEvent) {
      Entity damager = ((EntityDamageByEntityEvent)damageEvent).getDamager();
      if (damager instanceof Player player) {
        if (entity instanceof Zombie) {
          if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
            new BossFightAchievements(SoulBoss.main()).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT_ZOMBIE_END);
          }
        }
      }
    }
  }

}
