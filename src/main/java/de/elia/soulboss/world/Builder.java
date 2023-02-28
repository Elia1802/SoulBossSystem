package de.elia.soulboss.world;

import de.elia.soulboss.world.worldborder.WorldBorderBuilder;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Interface of the worldborder builder.
 * @implementationOf {@link WorldBorderBuilder}
 */
public interface Builder {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set the Worldborder in the specifiy {@link World};
   * @param world Requires the specify {@link World}
   */
  void setWorldBorder(@NotNull World world);

}
