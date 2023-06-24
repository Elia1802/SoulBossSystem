package de.elia.items.events.magicbook;

import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;
import de.elia.api.thezepserapi.datatypes.Region;
import de.elia.api.thezepserapi.enums.RegionType;
import de.elia.api.thezepserapi.spells.Spells;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Elia, Zopnote
 * @description This event give the magic book functions.
 * @implements {@link Listener}
 */
public class MagicBookEvent implements Listener {

  private final Plugin plugin;
  private static final Collection<Region> REGIONS = new ArrayList<>();
  private int count;

  public MagicBookEvent(Plugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onRightClick(@NotNull PlayerInteractEvent event) {
    if (event.getAction().isRightClick()) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.MAGIC_BOOK))) {
        final Collection livingEntities = event.getPlayer().getLocation().getNearbyLivingEntities(20.0);
        livingEntities.forEach(entity -> {
          if (!(entity instanceof Player player))return;
          player.spawnParticle(Particle.ELECTRIC_SPARK, event.getPlayer().getLocation(), 15);
          player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 0.7f, 0.8f);
        });
        final Region region = TheZepserAPI.region.create(new Location(event.getPlayer().getWorld(), event.getPlayer().getLocation().x(), event.getPlayer().getLocation().y() + 5.0, event.getPlayer().getLocation().z()), 6.0, RegionType.NORMAL, event.getPlayer(), false);
        REGIONS.add(region);
        this.count = 0;
        new BukkitRunnable(){
          @Override
          public void run() {
            if (MagicBookEvent.this.count < 40) {
              ++MagicBookEvent.this.count;
              TheZepserAPI.region.spawnCircle(region, Particle.DRIPPING_OBSIDIAN_TEAR);
              livingEntities.forEach(entity -> {
                if (!(entity instanceof Player player)) return;
                double currentHealth = player.getHealth();
                double newHealth = currentHealth + 1.0;
                player.setHealth(Math.min(newHealth, player.getHealthScale()));
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 0.7f, 0.2f);
              });
            } else {
              REGIONS.remove(region);
              this.cancel();
            }
          }
        }.runTaskTimer(this.plugin, 0L, 5L);
      }
    } else if (event.getAction().isLeftClick() && TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.MAGIC_BOOK))) {
      Spells.GRAVITATION_ATTACK(event.getPlayer(), false);
    }
  }
}
