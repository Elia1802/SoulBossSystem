package de.elia.soulboss.entity.mobs.boss.magic.attackaction;

import de.elia.soulboss.SoulBoss;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author ELia, Zopnote
 * @description This class has all attacks.
 */
public class Actions {

  public void actionTeleport(Vector vector, final Player player) {
    new BukkitRunnable(){

      public void run() {
        Location playerLocation = player.getLocation();
        Random random = new Random();
        Location randomLocation = null;
        while (randomLocation == null) {
          double x = playerLocation.getX() + (random.nextDouble() - 0.5) * 15.0;
          double y = playerLocation.getY() + (random.nextDouble() - 0.5) * 15.0;
          double z = playerLocation.getZ() + (random.nextDouble() - 0.5) * 15.0;
          randomLocation = new Location(player.getWorld(), x, y, z);
          if (randomLocation.getBlock().getType() == Material.AIR) continue;
          randomLocation = null;
        }
        player.teleport(randomLocation);
        player.playSound(playerLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
      }
    }.runTaskLater((Plugin)SoulBoss.soulBoss().main(), 10L);
  }

  public void actionFire(@NotNull Player player, int ticks) {
    player.setFireTicks(ticks);
  }

  public void actionStrikeLightning(@NotNull World world, @NotNull Player player) {
    world.strikeLightning(player.getLocation());
  }
}
