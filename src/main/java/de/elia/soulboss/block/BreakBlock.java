package de.elia.soulboss.block;

import de.elia.api.itembuilder.ItemBuilder;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author D1p4k and modified by Elia
 * @description A mob can break a block with this class.
 */
public class BreakBlock {

  private final Plugin plugin;

  public BreakBlock(@NotNull Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * @description Gets a list of all {@link Entity}s in the world.
   * @return the list witht the {@link Entity}s.
   */
  @NotNull
  public List<Entity> entities() {
    ArrayList<Entity> entityList = new ArrayList<>();
    Bukkit.getWorlds().forEach(world -> entityList.addAll(world.getEntities()));
    return entityList;
  }

  /**
   * @description Break a block on a specify {@link Location} to a specify chance.
   * @param location Requires the {@link Location}
   * @param chance Requires the chance.
   */
  private void breakBlock(@NotNull Location location, float chance) {
    Block block = location.getBlock();
    if (block.getType().getHardness() < 0.0f || block.getType() == Material.BEDROCK || block.getType() == Material.BARRIER || block.getWorld() == Bukkit.getWorld("world_bossfight")) {
      return;
    }
    if (Math.random() < (double)chance) {
      block.breakNaturally(new ItemBuilder(Material.NETHERITE_PICKAXE).build());
    } else {
      block.setType(Material.AIR);
    }
  }

  /**
   * @description Break a {@link Block} to a specify chance.
   * @param block Requires the {@link Block}
   * @param chance Requires the chance.
   */
  private void breakBlock(@NotNull Block block, float chance) {
    if (block.getType().getHardness() < 0.0f || block.getWorld() == Bukkit.getWorld("world_bossfight")) {
      return;
    }
    if (Math.random() < (double)chance) {
      block.breakNaturally(new ItemBuilder(Material.NETHERITE_PICKAXE).build());
    } else {
      block.setType(Material.AIR);
    }
  }

  /**
   * @description Start the task for the entity
   * @param namespacedKey Requires the key of the entity.
   */
  public void breakTask(final NamespacedKey namespacedKey) {
    new BukkitRunnable(){

      public void run() {
        BreakBlock.this.entities().forEach(entity -> {
          Monster monster;
          if (entity instanceof Zombie && (monster = (Monster)entity).getPersistentDataContainer().has(namespacedKey) && monster.getTarget() != null && monster.getTarget() instanceof Player) {
            double chance = Math.random();
            if (chance < 0.8) {
              Block block = monster.getTargetBlock(null, 3);
              BreakBlock.this.breakBlock(block, 90.0f);
              if (monster.getLocation().getPitch() < -30.0f) {
                Location location = block.getLocation();
                location.setY(monster.getLocation().getY() + 1.0);
                BreakBlock.this.breakBlock(location, 80.0f);
              }
            }
          }
        });
      }
    }.runTaskTimer(this.plugin, 100L, 20L);
  }
}

