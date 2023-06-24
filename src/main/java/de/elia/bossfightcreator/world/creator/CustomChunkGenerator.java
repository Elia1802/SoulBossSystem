package de.elia.bossfightcreator.world.creator;

import de.elia.PluginMain;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.settings.WorldSettings;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Elia
 * @description This class modfied the ChunkGenerator of the Server.
 * @extends {@link ChunkGenerator}
 */
public class CustomChunkGenerator extends ChunkGenerator {

  private final WorldSettings settings = new WorldSettings();

  /**
   * Gets a fixed spawn location to use for a given world. A null value is returned if a world should not use a fixed spawn point, and will instead attempt to find one randomly.
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation
   * @return Location containing a new spawn point, otherwise null
   */
  @Override
  @Nullable
  public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
    WorldMain worldMain = PluginMain.main().worldMain();
    worldMain.logInfo("Set Spawnlocation...");
    Location location = new Location(world, 7.0, 68.0, -16.0);
    worldMain.logInfo("Spawnlocation sets!");
    return location;
  }

  /**
   * @description  Gets if the server should generate Vanilla bedrock. The Vanilla bedrock is generated <b>before</b> {@link #generateBedrock(WorldInfo, Random, int, int, ChunkData)} is called.
   * @return true if the server should generate Vanilla bedrock
   * @deprecated has no effect, bedrock generation is part of the surface step, see {@link #shouldGenerateSurface()}
   */
  @Override
  @Deprecated
  public boolean shouldGenerateBedrock() {
    return this.settings.bedrock();
  }

  /**
   * @description  Gets if the server should generate Vanilla caves. The Vanilla caves are generated <b>before</b> {@link #generateCaves(WorldInfo, Random, int, int, ChunkData)} is called. This is method is not called (and has therefore no effect), if {@link #shouldGenerateCaves(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla caves
   */
  @Override
  public boolean shouldGenerateCaves() {
    return this.settings.caves();
  }

  /**
   * @description Gets if the server should generate Vanilla decorations after this ChunkGenerator. The Vanilla decoration are generated <b>before</b> any {@link BlockPopulator} are called. This is method is not called (and has therefore no effect), if {@link #shouldGenerateDecorations(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla decorations
   */
  @Override
  public boolean shouldGenerateDecorations() {
    return this.settings.decorations();
  }

  /**
   * @description Gets if the server should generate Vanilla mobs after this ChunkGenerator. This is method is not called (and has therefore no effect), if {@link #shouldGenerateMobs(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla mobs
   */
  @Override
  public boolean shouldGenerateMobs() {
    return this.settings.mobs();
  }

  /**
   * @description Gets if the server should generate Vanilla noise. The Vanilla noise is generated <b>before</b> {@link #generateNoise(WorldInfo, Random, int, int, ChunkData)} is called. This is method is not called (and has therefore no effect), if {@link #shouldGenerateNoise(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla noise
   */
  @Override
  public boolean shouldGenerateNoise() {
    return this.settings.noise();
  }

  /**
   * @description Gets if the server should generate Vanilla structures after thisChunkGenerator. This is method is not called (and has therefore no effect), if {@link #shouldGenerateStructures(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla structures
   */
  @Override
  public boolean shouldGenerateStructures() {
    return this.settings.structures();
  }

  /**
   * @description Gets if the server should generate Vanilla surface. The Vanilla surface is generated <b>before</b> {@link #generateSurface(WorldInfo, Random, int, int, ChunkData)} is called. This is method is not called (and has therefore no effect), if {@link #shouldGenerateSurface(WorldInfo, Random, int, int)} is overridden.
   * @return true if the server should generate Vanilla surface
   */
  @Override
  public boolean shouldGenerateSurface() {
    return this.settings.surface();
  }

  /**
   * @description Shapes the Chunk caves for the given coordinates.
   * @notes
   * This method should <b>never</b> attempt to get the Chunk at the passed coordinates, as doing so may cause an infinite loop.
   * This method should <b>never</b> modify the {@link ChunkData} at a later point of time.
   * The Y-coordinate range should <b>never</b> be hardcoded, to get the Y-coordinate range use the methods {@link ChunkData#getMinHeight()} and {@link ChunkData#getMaxHeight()}.
   * If {@link #shouldGenerateCaves()} is set to true, the given{@link ChunkData} contains already the Vanilla cave generation.
   * @param worldInfo The world info of the world this chunk will be used for
   * @param random The random generator to use
   * @param chunkX The X-coordinate of the chunk
   * @param chunkZ The Z-coordinate of the chunk
   * @param chunkData To modify
   */
  @Override
  public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
  }
}

