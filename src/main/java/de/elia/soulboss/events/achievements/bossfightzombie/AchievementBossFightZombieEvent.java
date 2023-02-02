package de.elia.soulboss.events.achievements.bossfightzombie;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.achievement.process.BossFightAchievements;
import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import io.papermc.paper.event.entity.EntityMoveEvent;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}
 * @description This is the Listener for the Achievement BOSSFIGHT_ZOMBIE.
 */
public class AchievementBossFightZombieEvent implements Listener {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Event for the Achievement BOSSFIGHT_ZOMBIE.
   * @param event Requires the {@link EntityMoveEvent}
   */
  @EventHandler
  public void onBossFightZombie(@NotNull EntityMoveEvent event){
    Entity entity = event.getEntity();
    if (entity instanceof Zombie) {
      if (entity.getPersistentDataContainer().has(new NamespacedKey(SoulBoss.soulBoss(), "680035753"))) {
        Location location = entity.getLocation();
        Collection<Player> nearbyPlayers = location.getNearbyPlayers(100);
        for (Player player : nearbyPlayers) {
          new BossFightAchievements(SoulBoss.soulBoss()).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT_ZOMBIE);
        }
      }
    }
  }

}
