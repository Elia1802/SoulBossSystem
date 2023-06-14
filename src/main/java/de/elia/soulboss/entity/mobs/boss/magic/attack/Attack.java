package de.elia.soulboss.entity.mobs.boss.magic.attack;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.entity.mobs.boss.magic.attackaction.Actions;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This an Attacks of the Zombie
 */
public class Attack {

  private Location position;
  private Vector direction;
  private int counter;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This is the attack for random player teleport
   */
  public void attackTeleport(@NotNull Entity entity){
    Location location = entity.getLocation();
    Collection<Player> nearbyPlayers = location.getNearbyPlayers(15D);
    if (nearbyPlayers == null)return;
    for (Player player : nearbyPlayers) {
      player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7F, 0.3F);
    }
    LivingEntity boss = (LivingEntity) entity;
    boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2*20, 255, false, false));
    counter = 0;
    new BukkitRunnable() {
      public void run(){
        if (counter < 11) {
          counter++;
          position = location;
          direction = entity.getLocation().getDirection();
          position.add(direction.multiply(2.3));
          position.getWorld().spawnParticle(Particle.PORTAL, position, 300);
          Collection<Player> damagedPlayer = position.getNearbyPlayers(0.5D);
          for (Player player : damagedPlayer) {
            new Actions().actionTeleport(direction, player);
          }
        }else cancel();
      }
    }.runTaskTimer(SoulBoss.main(), 0L, 10L);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This is the attack for fire
   */
  public void attackFire(@NotNull Entity entity){
    Location location = entity.getLocation();
    Collection<Player> nearbyPlayers = location.getNearbyPlayers(15);
    if (nearbyPlayers == null)return;
    for (Player player : nearbyPlayers) {
      player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7F, 0.3F);
    }
    LivingEntity boss = (LivingEntity) entity;
    boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2*20, 255, false, false));
    counter = 0;
    new BukkitRunnable() {
      public void run(){
        if (counter < 11) {
          counter++;
          position = location;
          direction = entity.getLocation().getDirection();
          position.add(direction.multiply(2.3));
          position.getWorld().spawnParticle(Particle.FLAME, position, 300);
          Collection<Player> damagedPlayers = position.getNearbyPlayers(0.5);
          for (Player player : damagedPlayers) {
            new Actions().actionFire(player, 10*20);
          }
        }else cancel();
      }
    }.runTaskTimer(SoulBoss.main(), 0L, 10L);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This is the attack StrikeLightnings
   */
  public void attackStrikeLightning(@NotNull Entity entity){
    Location location = entity.getLocation();
    Collection<Player> nearbyPlayers = location.getNearbyPlayers(20);
    if (nearbyPlayers == null)return;
    for (Player player : nearbyPlayers) {
      player.playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 0.7F, 0.3F);
    }
    LivingEntity boss = (LivingEntity) entity;
    boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2*20, 255, false, false));
    counter = 0;
    new BukkitRunnable() {
      public void run(){
        if (counter < 11) {
          counter++;
          position = location;
          direction = entity.getLocation().getDirection();
          position.add(direction.multiply(2.3));
          position.getWorld().spawnParticle(Particle.CLOUD, position, 300);
          Collection<Player> damagedPlayers = position.getNearbyPlayers(0.5);
          for (Player player : damagedPlayers) {
            new Actions().actionStrikeLightning(location.getWorld(), player);
          }
        }else cancel();
      }
    }.runTaskTimer(SoulBoss.main(), 0L, 10L);
  }

}
