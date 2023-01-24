package de.elia.soulboss.fight.stop;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.entity.mobs.boss.mob.Boss;
import de.elia.soulboss.messages.messages.CustomMessages;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the stop class for the Bossfight
 */
public class Stop {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is methode stopped the Bossfight
   * @param player Requires a Player
   * @param boss Requires the Boss
   */
  public void stop(@NotNull Player player, Zombie boss){
    if (player == null)return;
    if (boss == null)return;
    if (boss.getBukkitEntity().getPersistentDataContainer().has(new NamespacedKey(SoulBoss.soulBoss(), "680035753"))) {
      CustomMessages message = new CustomMessages();
      message.log(Level.INFO, "Remove Boss");
      boss.remove(Entity.RemovalReason.KILLED);
      message.log(Level.INFO, "Remove BossBar");
      Boss.boss().bossBar().removeAll();
      Boss.boss().bossBar().setVisible(false);
      message.log(Level.WARNING, "BOSSFIGHT STOPPED!");
    }
  }

}
