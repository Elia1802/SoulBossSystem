package de.elia.soulboss.entity.mobs.boss.magic.attackaction;

import de.elia.soulboss.SoulBoss;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This an Actions for the Attacks
 */
public class Actions {

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This random teleport the Player.
   */
  public void actionTeleport(Vector vector, Player player){
    new BukkitRunnable(){
      public void run(){
        Location playerLocation = player.getLocation();
        Random random = new Random();
        Location randomLocation = null;
        while (randomLocation == null) {
          double x = playerLocation.getX() + (random.nextDouble() - 0.5) * 15;
          double y = playerLocation.getY() + (random.nextDouble() - 0.5) * 15;
          double z = playerLocation.getZ() + (random.nextDouble() - 0.5) * 15;
          randomLocation = new Location(player.getWorld(), x, y, z);
          if (randomLocation.getBlock().getType() != Material.AIR){
            randomLocation = null;
          }
        }
        player.teleport(randomLocation);
        player.playSound(playerLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5F, 1.0F);
      }
    }.runTaskLater(SoulBoss.main(), 10);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This set the Player in Fire
   */
  public void actionFire(@NotNull Player player, int ticks){
    player.setFireTicks(ticks);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This spawn a strike lightning in the Player.
   */
  public void actionStrikeLightning(@NotNull World world, @NotNull Player player){
    world.strikeLightning(player.getLocation());
  }

}
