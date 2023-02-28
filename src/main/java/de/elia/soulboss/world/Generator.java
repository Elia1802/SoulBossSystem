package de.elia.soulboss.world;

import de.elia.soulboss.world.generator.WorldGenerator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Interface of the custom world generator for the bossfight world.
 * @implementationOf {@link WorldGenerator}
 */
public interface Generator {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set a spawnlocation for the {@link Player}
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation (Not used in this Plugin)
   * @implementationOf {@link WorldGenerator#getFixedSpawnLocation(World, Random)}
   * @return {@link Location}
   */
  @Nullable Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random);

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate bedrock in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateBedrock()}
   * @return {@link Settings#bedrock()}
   */
  boolean shouldGenerateBedrock();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate caves in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateCaves()}
   * @return {@link Settings#caves()}
   */
  boolean shouldGenerateCaves();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate decoration in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateDecorations()}
   * @return {@link Settings#decoration()}
   */
  boolean shouldGenerateDecorations();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if spawn mobs in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateMobs()}
   * @return {@link Settings#mobs()}
   */
  boolean shouldGenerateMobs();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate noises in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateNoise()}
   * @return {@link Settings#noise()}
   */
  boolean shouldGenerateNoise();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate structures in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateStructures()}
   * @return {@link Settings#structures()}
   */
  boolean shouldGenerateStructures();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate surfaces in the world.
   * @implementationOf {@link WorldGenerator#shouldGenerateSurface()}
   * @return {@link Settings#surface()}
   */
  boolean shouldGenerateSurface();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Generate a spawn block for the {@link Player}
   * @implementationOf {@link WorldGenerator#generateBedrock(WorldInfo, Random, int, int, ChunkGenerator.ChunkData)}
   * @param worldInfo The world info of the world this chunk will be used for (Not used in this Plugin)
   * @param random The random generator to use (Not used in this Plugin)
   * @param chunkX The X-coordinate of the chunk (Not used in this Plugin)
   * @param chunkZ The Z-coordinate of the chunk (Not used in this Plugin)
   * @param chunkData To modify
   */
  void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData);

}
