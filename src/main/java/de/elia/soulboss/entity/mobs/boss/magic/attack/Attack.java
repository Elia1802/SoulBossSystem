package de.elia.soulboss.entity.mobs.boss.magic.attack;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.entity.mobs.boss.magic.attackaction.Actions;
import java.util.Collection;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Attack {
  private static Location position;
  private static Vector direction;
  private static int counter;
  private static boolean ifAttackIsUsed = false;

  public static void attackTeleport(final @NotNull Entity entity) {
    if (!ifAttackIsUsed) {
      ifAttackIsUsed = true;
      final Location location = entity.getLocation();
      Collection<Player> nearbyPlayers = location.getNearbyPlayers(20.0);
      if (nearbyPlayers == null) {
        return;
      }
      nearbyPlayers.forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7f, 0.3f));
      final LivingEntity boss = (LivingEntity)entity;
      boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, false, false));
      counter = 0;
      new BukkitRunnable(){

        public void run() {
          if (counter < 11) {
            ++counter;
            position = location;
            direction = entity.getLocation().getDirection();
            position.add(direction.multiply(2.3));
            position.getWorld().spawnParticle(Particle.PORTAL, position, 300);
            Collection<Player> damagedPlayer = position.getNearbyPlayers(0.5);
            for (Player player : damagedPlayer) {
              Actions.actionTeleport(direction, player, (Entity)boss);
            }
          } else {
            this.cancel();
          }
        }
      }.runTaskTimer((Plugin)SoulBoss.soulBoss().main(), 0L, 1L);
      ifAttackIsUsed = false;
    }
  }

  public static void attackFire(final @NotNull Entity entity) {
    if (!ifAttackIsUsed) {
      ifAttackIsUsed = true;
      final Location location = entity.getLocation();
      Collection<Player> nearbyPlayers = location.getNearbyPlayers(15.0);
      if (nearbyPlayers == null) {
        return;
      }
      for (Player player : nearbyPlayers) {
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7f, 0.3f);
      }
      LivingEntity boss = (LivingEntity)entity;
      boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, false, false));
      counter = 0;
      new BukkitRunnable(){

        public void run() {
          if (counter < 11) {
            ++counter;
            position = location;
            direction = entity.getLocation().getDirection();
            position.add(direction.multiply(2.3));
            position.getWorld().spawnParticle(Particle.FLAME, position, 300);
            Collection<Player> damagedPlayers = position.getNearbyPlayers(0.5);
            for (Player player : damagedPlayers) {
              Actions.actionFire(player, 200);
            }
          } else {
            this.cancel();
          }
        }
      }.runTaskTimer((Plugin)SoulBoss.soulBoss().main(), 0L, 10L);
      ifAttackIsUsed = false;
    }
  }

  public static void attackStrikeLightning(final @NotNull Entity entity) {
    if (!ifAttackIsUsed) {
      ifAttackIsUsed = true;
      final Location location = entity.getLocation();
      Collection<Player> nearbyPlayers = location.getNearbyPlayers(20.0);
      if (nearbyPlayers == null) {
        return;
      }
      for (Player player : nearbyPlayers) {
        player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7f, 0.3f);
      }
      LivingEntity boss = (LivingEntity)entity;
      boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 255, false, false));
      counter = 0;
      new BukkitRunnable(){
        @Override
        public void run() {
          if (counter < 11) {
            ++counter;
            position = location;
            direction = entity.getLocation().getDirection();
            position.add(direction.multiply(2.3));
            position.getWorld().spawnParticle(Particle.CLOUD, position, 300);
            Collection<Player> damagedPlayers = position.getNearbyPlayers(0.5);
            for (Player player : damagedPlayers) {
              Actions.actionStrikeLightning(location.getWorld(), player);
            }
          } else {
            this.cancel();
          }
        }
      }.runTaskTimer(SoulBoss.soulBoss().main(), 0L, 10L);
      ifAttackIsUsed = false;
    }
  }
}
