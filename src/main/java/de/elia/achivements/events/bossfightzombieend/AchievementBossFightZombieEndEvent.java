package de.elia.achivements.events.bossfightzombieend;

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

  @EventHandler
  public void onBossFightZombieEnd(@NotNull EntityDeathEvent event){
    Entity boss = event.getEntity();
    EntityDamageEvent killer = boss.getLastDamageCause();
    if (killer instanceof EntityDamageByEntityEvent) {
      Entity damager = ((EntityDamageByEntityEvent)killer).getDamager();
      if (damager instanceof Player player) {
        if (boss instanceof Zombie) {
          if (boss.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
            new AchievementClass(AchievementMain.achievementMain().main()).giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE_END);
          }
        }
      }
    }
  }

}
