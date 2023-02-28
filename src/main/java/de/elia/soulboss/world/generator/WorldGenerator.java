package de.elia.soulboss.world.generator;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.world.Generator;
import de.elia.soulboss.world.Settings;
import de.elia.soulboss.world.worldlog.WorldLogger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link ChunkGenerator}
 * @implements {@link Generator}
 * @description The custom world generator for the bossfight world.
 */
public class WorldGenerator extends ChunkGenerator implements Generator {

  private final Settings settings = SoulBoss.soulBoss().settings();
  private final SoulBoss soulBoss = SoulBoss.soulBoss();
  private final WorldLogger logger = new WorldLogger("Soul-World-Generator");
  private final JavaPlugin javaPlugin;

  public WorldGenerator(JavaPlugin javaPlugin){
    this.javaPlugin = javaPlugin;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set a spawnlocation for the {@link Player}
   * @param world The world to locate a spawn point for
   * @param random Random generator to use in the calculation (Not used in this Plugin)
   * @return {@link Location}
   */
  @Override
  @Nullable
  public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random){
    logger.log("Set spawn location.");
    return new Location(world, 0D, 64D, 0D);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate bedrock in the world.
   * @return {@link Settings#bedrock()}
   */
  @Override
  public boolean shouldGenerateBedrock(){
    return this.settings.bedrock();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate caves in the world.
   * @return {@link Settings#caves()}
   */
  @Override
  public boolean shouldGenerateCaves(){
    return this.settings.caves();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate decoration in the world.
   * @return {@link Settings#decoration()}
   */
  @Override
  public boolean shouldGenerateDecorations(){
    return this.settings.decoration();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if spawn mobs in the world.
   * @return {@link Settings#mobs()}
   */
  @Override
  public boolean shouldGenerateMobs(){
    return this.settings.mobs();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate noises in the world.
   * @return {@link Settings#noise()}
   */
  @Override
  public boolean shouldGenerateNoise(){
    return this.settings.noise();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate structures in the world.
   * @return {@link Settings#structures()}
   */
  @Override
  public boolean shouldGenerateStructures(){
    return this.settings.structures();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate surfaces in the world.
   * @return {@link Settings#surface()}
   */
  @Override
  public boolean shouldGenerateSurface(){
    return this.settings.surface();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Generate a spawn block for the {@link Player}
   * @param worldInfo The world info of the world this chunk will be used for (Not used in this Plugin)
   * @param random The random generator to use (Not used in this Plugin)
   * @param chunkX The X-coordinate of the chunk (Not used in this Plugin)
   * @param chunkZ The Z-coordinate of the chunk (Not used in this Plugin)
   * @param chunkData To modify
   */
  @Override
  public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData){
    final int x = 0;
    final int y = 64;
    final int z = 0;
    logger.log("Generate a spawn platform...");
    chunkData.setBlock(x, y, z, Material.BEDROCK);
    logger.log("Platform generates!");
  }

}
