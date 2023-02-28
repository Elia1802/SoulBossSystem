package de.elia.soulboss.fight.remove;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.messages.message.CustomMessages;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Zopnote
 * @version 1.0
 * @since 1.0
 * @description This remove the boss.
 */
public class RemoveNearBosses {

  private int COUNT;

  /**
   * @author Zopnote
   * @version 1.0
   * @since 1.0
   * @description This remove the boss in a radius of 5 blocks
   * @param player Requires a {@link Player}
   */
  public RemoveNearBosses(@NotNull Player player) {
    CustomMessages message = new CustomMessages();
    Collection<LivingEntity> entities = player.getLocation().getNearbyLivingEntities(5);
    var status = SoulBoss.playerStatusMap();
    COUNT = 0;
    for (LivingEntity entity : entities) {
      if (entity.getPersistentDataContainer().has(new NamespacedKey(SoulBoss.soulBoss(), "680035753"))) {
        entity.remove();
        if (status.get(player) == 1) {
          status.replace(player, 0);
        }
        COUNT++;
      }
    }
    if (COUNT == 0) {
      message.messageWithPrefix(player, message.gray("Es wurde kein Boss gelöscht."));
    }else {
      message.messageWithPrefix(player, message.gray("Es wurde ein Entity gelöscht."));
    }
  }

}
