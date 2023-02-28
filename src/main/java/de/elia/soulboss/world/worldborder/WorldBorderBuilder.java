package de.elia.soulboss.world.worldborder;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.world.Builder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This class built a worldborder.
 * @implements {@link Builder}
 */
public class WorldBorderBuilder implements Builder {

  public WorldBorderBuilder(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set the Worldborder in the specifiy {@link World};
   * @param world Requires the specify {@link World}
   */
  public void setWorldBorder(@NotNull World world){
    var logger = SoulBoss.soulBoss().worldLogger();
    logger.log("Set world: " + world.getName() + " a Worldborder...");
    logger.log("Load Worldborder...");
    WorldBorder worldBorder = world.getWorldBorder();
    logger.log("Worldborder loaded!");
    logger.log("Set center at x:0.00 y:65.50 z:64.50...");
    worldBorder.setCenter(new Location(world, 0.00D, 64D, 0D));
    logger.log("Center was set!");
    logger.log("Set size at 200...");
    worldBorder.setSize(200D);
    logger.log("Size sets!");
    logger.log("Set damage buffer at 3");
    worldBorder.setDamageBuffer(3D);
    logger.log("Damage buffer sets!");
    logger.log("Set damage at 20");
    worldBorder.setDamageAmount(20D);
    logger.log("Damage sets!");
    logger.log("Worldborder sets at world: " + world.getName());
  }

}
