package de.elia.achivements.events.bossfight;

import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.systemclasses.keys.PluginKeys;

import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Planned;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This Event give the {@link Player} the achievement {@link Achievements#BOSSFIGHT} if the player damaged the boss.
 * @implements {@link Listener}
 * @planned Because: Fix that this event not working
 */
@Planned("Fix that this event not working")
public class AchievementBossFightEvent implements Listener {

  public AchievementBossFightEvent() {
    AnnotationChecker.processAnnotations(AchievementBossFightEvent.class);
  }

  @EventHandler
  public void onBossDamaged(@NotNull EntityDamageByEntityEvent event) {
    Entity damagedEntity = event.getDamager();
    Entity damagerEntity = event.getEntity();
    if (damagerEntity instanceof Player player) {
      if (damagedEntity.getType() == EntityType.ZOMBIE && damagedEntity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        new Achievement().giveAchievement(player, Achievements.BOSSFIGHT);
      }
    }
  }
}
