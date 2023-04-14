package de.elia.api.spells.spells.gravitation;

import de.elia.PluginMain;
import de.elia.api.TheZepserAPI;
import de.elia.api.datatypes.Region;
import de.elia.api.enums.RegionType;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;


public class GRAVITATION_DEFENSE {
  public GRAVITATION_DEFENSE(Player player, boolean pvp) {
    Collection<Player> players = player.getLocation().getNearbyPlayers(14);
    for (Player player1 : players) {
      player1.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
      player1.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.6f, 0.1f);
    }
    Region region = new Region(player.getLocation(), 5, RegionType.GRAVITATION_DEFENSE, player, pvp);
    TheZepserAPI.region.spawnCircle(region, Particle.DRIPPING_OBSIDIAN_TEAR);
    for (LivingEntity entity : TheZepserAPI.region.contains(region)) {
      if (entity instanceof Player player1) {
        if (!(player1 == region.getOwner())) {
          if (pvp) {
            player1.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
            entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*4, 1, false, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 65, 1, false, false, false));
            new BukkitRunnable() {
              public void run() {
                entity.damage(6);
              }
            }.runTaskLater(PluginMain.main(), 80);
          }
          else {
            player1.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
          }
        }
        else {
          player1.playSound(entity.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
        }
      }
      else {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*4, 1, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 65, 1, false, false, false));
        new BukkitRunnable() {
          public void run() {
            entity.damage(6);
          }
        }.runTaskLater(PluginMain.main(), 80);
      }

    }
  }
}
