package de.elia.soulboss.entity.mobs.boss.magic.attackaction;

import de.elia.api.timing.StartTasks;
import de.elia.api.timing.TimerUtils;
import de.elia.soulboss.SoulBoss;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * @author ELia, Zopnote
 * @description This class has all attacks.
 */
public class Actions {
  public static void actionTeleport(Vector vector, final Player player, final Entity entity) {
    new BukkitRunnable(){
      @Override
      public void run() {
        Location location = entity.getLocation().clone();
        entity.teleport(player.getLocation());
        Location newLocation = new Location(location.getWorld(), location.x(), location.y() + 15d, location.z());
        player.teleport(newLocation);
        player.playSound(newLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1.0f);
      }
    }.runTaskLater(SoulBoss.soulBoss().main(), 10L);
  }

  public static void actionFire(@NotNull Player player, int ticks) {
    player.setFireTicks(ticks);
  }

  public static void actionStrikeLightning(@NotNull World world, @NotNull Player player) {
    world.strikeLightning(player.getLocation());
    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 255, false, false, false));
    new Timer().start(20*20, player, null, SoulBoss.soulBoss().main());
  }

  private static class Timer extends StartTasks {
    @Override
    public void start(int time, @NotNull Player player, Location location, Plugin plugin) {
      TimerUtils.countdownAndRun(time, new Runnable() {
        public void run() {
        }
      }, plugin);
      TimerUtils.countdownInterval(time, new TimerUtils.TimeRunnable() {
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int var2 = ticks / 20;
          }

        }
      }, new Runnable() {
        @Override
        public void run() {
          new BukkitRunnable() {
            @Override
            public void run() {
              player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 15, 255, false, false, false));
            }
          }.runTask(SoulBoss.soulBoss().main());
        }
      }, plugin);
    }
  }
}
