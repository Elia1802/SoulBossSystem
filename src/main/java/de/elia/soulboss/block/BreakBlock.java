package de.elia.soulboss.block;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
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
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the BreakBlock Class. It allows a specific mob to mine blocks.
 */
public class BreakBlock {

  private final Plugin plugin;

  public BreakBlock(Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets all Entitys of the current world
   * @return entityList
   */
  @NotNull
  public List<Entity> entities(){
    List<Entity> entityList = new ArrayList<>();
    Bukkit.getWorlds().forEach(world -> entityList.addAll(world.getEntities()));
    return entityList;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Queries if the block is in a secure region.
   * @param location Requires the Location
   * @return
   */
  public boolean inRegion(@NotNull Location location){
    WorldGuard guard = WorldGuard.getInstance();
    RegionManager regionManager = guard.getPlatform().getRegionContainer().get(BukkitAdapter.adapt(location.getWorld()));
    ApplicableRegionSet regionSet = regionManager.getApplicableRegions(BukkitAdapter.asBlockVector(location));
    return regionSet.size() < 0;
  }

  /**
   * @author D1p4k
   * @modifiedOf Elia
   * @version 1.0
   * @since 1.0
   * @description Break a block in a specify {@link Location}
   * @param location Requires the Location of the Block
   * @param chance Requires a Chance if break the block
   */
  public void breakBlock(@NotNull Location location, float chance){
    final Block block = location.getBlock();
    if (block.getType().getHardness() < 0 || block.getType() == Material.BEDROCK || this.inRegion(location))return;
    if (Math.random() < chance) {
      block.breakNaturally(new ItemBuilder(Material.NETHERITE_PICKAXE).build());
    }else block.setType(Material.AIR);
  }

  /**
   * @author D1p4k
   * @modifiedOf Elia
   * @version 1.0
   * @since 1.0
   * @description Break a specify {@link Block}
   * @param block Requires the specify {@link Block}
   * @param chance Requires a Chance if break the block
   */
  public void breakBlock(@NotNull Block block, float chance){
    if (block.getType().getHardness() < 0 || inRegion(block.getLocation()))return;
    if (Math.random() < chance){
      block.breakNaturally(new ItemBuilder(Material.NETHERITE_PICKAXE).build());
    }else block.setType(Material.AIR);
  }

  /**
   * @author D1p4k
   * @modifiedOf Elia
   * @version 1.0
   * @since 1.0
   * @description Gives the {@link Entity} that has the {@link NamespacedKey} the ability to mine a {@link Block}.
   * @param namespacedKey Requires the {@link NamespacedKey} of the {@link Entity}
   */
  public void breakTask(NamespacedKey namespacedKey){
    new BukkitRunnable() {
      @Override
      public void run() {
        entities().forEach(entity -> {
          if (entity instanceof Zombie) {
            final Monster monster = (Monster) entity;
            if (monster.getPersistentDataContainer().has(namespacedKey)) {
              if (monster.getTarget() != null && monster.getTarget() instanceof Player) {
                final double chance = Math.random();
                if (chance < 0.8) {
                  Block block = monster.getTargetBlock(null, 3);
                  breakBlock(block, 90);
                  if (monster.getLocation().getPitch() < -30) {
                    Location location = (Location) monster.getTargetBlock(null, 3);
                    location.setY(monster.getLocation().getY() + 1);
                    breakBlock(location, 80);
                  }
                }
              }
            }
          }
        });
      }
    }.runTaskTimer(plugin, 5*20, 20);
  }

}
