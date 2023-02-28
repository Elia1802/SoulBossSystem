package de.elia.soulboss.world;

import de.elia.soulboss.SoulBoss;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.String;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Save the methodes for the bossfight world of the main class
 * @implementationOf {@link SoulBoss}
 */
public interface Utils {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Bossfight world.
   * @implementationOf {@link SoulBoss#getDefaultWorldGenerator(String, String)}
   * @param worldName Name of the world that this will be applied to
   * @param id Unique ID, if any, that was specified to indicate which
   *     generator was requested
   * @return null
   */
  @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id);

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the bossfight world.
   * @implementationOf {@link SoulBoss#world()}
   * @return the world of this plugin
   */
  World world();

}
