package de.elia.soulboss.entity.spawn;

import de.elia.soulboss.entity.mobs.boss.mob.Boss;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Spawn class to spawn the boss
 */
public class Spawn {

  public void spawn(@NotNull Player player){
    if (player == null)return;
    new Boss(player.getLocation());
  }

}
