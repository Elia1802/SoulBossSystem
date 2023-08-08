package de.elia.bossfightcreator.world.creator;

import de.elia.PluginMain;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.settings.WorldSettings;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author Elia
 * @description This class overwrided the {@link ChunkGenerator} to generate a empty world..
 */
public class CustomChunkGenerator extends ChunkGenerator {

  private final WorldSettings settings = new WorldSettings();

  @Nullable
  public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
    WorldMain worldMain = PluginMain.main().worldMain();
    worldMain.logInfo("Set Spawnlocation...");
    Location location = new Location(world, 7.0, 68.0, -16.0);
    worldMain.logInfo("Spawnlocation sets!");
    return location;
  }

  @Override
  @Deprecated
  public boolean shouldGenerateBedrock() {
    return this.settings.bedrock();
  }

  @Override
  public boolean shouldGenerateCaves() {
    return this.settings.caves();
  }

  @Override
  public boolean shouldGenerateDecorations() {
    return this.settings.decorations();
  }

  @Override
  public boolean shouldGenerateMobs() {
    return this.settings.mobs();
  }

  @Override
  public boolean shouldGenerateNoise() {
    return this.settings.noise();
  }

  @Override
  public boolean shouldGenerateStructures() {
    return this.settings.structures();
  }

  @Override
  public boolean shouldGenerateSurface() {
    return this.settings.surface();
  }

  @Override
  public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
  }
}
