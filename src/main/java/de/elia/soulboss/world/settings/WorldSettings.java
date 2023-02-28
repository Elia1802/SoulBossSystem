package de.elia.soulboss.world.settings;

import de.elia.soulboss.world.Settings;
import de.elia.soulboss.world.generator.WorldGenerator;
import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;

import java.lang.Boolean;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Set the world settings for the {@link WorldGenerator}
 * @impements {@link Settings}
 */
public class WorldSettings implements Settings {

  public WorldSettings(){
    //...
  }

  private Biome biome = null;
  private boolean bedrock = false;
  private boolean caves = false;
  private boolean decoration = false;
  private boolean mobs = false;
  private boolean noise = false;
  private boolean structures = false;
  private boolean surface = false;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate biomes in the world.
   * @return null
   */
  @NotNull
  public Biome biome(){
    return this.biome;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate bedrock in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean bedrock(){
    return this.bedrock;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate caves in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean caves(){
    return this.caves;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate decoration in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean decoration(){
    return this.decoration;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if spawn mobs in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean mobs(){
    return this.mobs;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate noises in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean noise(){
    return this.noise;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate structures in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean structures(){
    return this.structures;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate surfaces in the world.
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean surface(){
    return this.surface;
  }

}
