package de.elia.achivements.events.bossfight;

import de.elia.achivements.AchievementMain;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.achivements.achievement.process.AchievementClass;
import de.elia.achivements.achievement.storage.Achievements;

import net.minecraft.world.entity.monster.Zombie;

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

  @EventHandler
  public void onBossDamaged(@NotNull EntityDamageByEntityEvent event){
    Entity damagedEntity = event.getDamager();
    Entity damagerEntity = event.getEntity();
    if (damagerEntity instanceof Player player) {
      if (damagedEntity instanceof Zombie) {
        if (damagedEntity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
          new AchievementClass(AchievementMain.achievementMain().main()).giveAchievement(player, Achievements.BOSSFIGHT);
        }
      }
    }
  }

}
