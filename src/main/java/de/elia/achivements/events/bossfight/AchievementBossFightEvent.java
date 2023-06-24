package de.elia.achivements.events.bossfight;

import de.elia.achivements.AchievementMain;
import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Planned;
import de.elia.systemclasses.keys.PluginKeys;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description Give The player the achievement {@link Achievements#BOSSFIGHT}.
 * @planned Fix that this event not working
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
      if (damagedEntity instanceof Zombie && damagedEntity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        new Achievement().giveAchievement(player, Achievements.BOSSFIGHT);
      }
    }
  }
}

