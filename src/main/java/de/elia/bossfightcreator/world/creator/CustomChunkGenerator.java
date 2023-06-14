package de.elia.bossfightcreator.world.creator;

import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.world.settings.WorldSettings;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.Override;
import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link ChunkGenerator}
 * @description Overrides the {@link ChunkGenerator} to generate a void map
 */
public class CustomChunkGenerator extends ChunkGenerator {

  private final WorldSettings settings = new WorldSettings();

  public CustomChunkGenerator(){
    //...
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Set the spawn location for Players
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation
   * @return {@link Location}
   */
  @Override
  @Nullable
  public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random){
    var worldMain = BossFightCreatorMain.worldMain();
    worldMain.logInfo("Set Spawnlocation...");
    Location location = new Location(world, 7D, 68D, -16D);
    worldMain.logInfo("Spawnlocation sets!");
    return location;
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @deprecated has no Effect! Is a part of {@link CustomChunkGenerator#shouldGenerateSurface()}
   * @description Checks if generate Bedrock
   * @return {@link Boolean}
   */
  @Override
  @Deprecated //has no Effect! Is a part of shouldGenerateSurface()
  public boolean shouldGenerateBedrock(){
    return this.settings.bedrock();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate caves
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateCaves(){
    return this.settings.caves();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate decorations
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateDecorations(){
    return this.settings.decorations();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate mobs
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateMobs(){
    return this.settings.mobs();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate noises
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateNoise(){
    return this.settings.noise();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate structures
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateStructures(){
    return this.settings.structures();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Checks if generate surfaces
   * @return {@link Boolean}
   */
  @Override
  public boolean shouldGenerateSurface(){
    return this.settings.surface();
  }

  /**
   * @author Paper,Elia
   * @version 1.0
   * @since 1.0
   * @overridesOf {@link ChunkGenerator}
   * @description Generate the Bedrock of not Vanilla worlds
   * @param worldInfo The world info of the world this chunk will be used for
   * @param random The random generator to use
   * @param chunkX The X-coordinate of the chunk
   * @param chunkZ The Z-coordinate of the chunk
   * @param chunkData To modify
   */
  @Override
  public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData){
    //...
  }
}
