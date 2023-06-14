package de.elia.bossfightcreator.world.settings;

import org.bukkit.block.Biome;

import org.jetbrains.annotations.NotNull;

import java.lang.Boolean;
import java.lang.Deprecated;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Set all Settings for the World
 */
public class WorldSettings {

  private final Biome biome = null;
  private final Boolean bedrock = false;
  private final Boolean caves = false;
  private final Boolean decorations = false;
  private final Boolean mobs = false;
  private final Boolean noise = false;
  private final Boolean structures = false;
  private final Boolean surface = false;

  public WorldSettings(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Biom
   * @return {@link Biome}
   */
  @NotNull
  public Biome biome(){
    return this.biome;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @deprecated has no Effect! Is a part of {@link WorldSettings#surface()}
   * @description Gets the bedrock
   * @return {@link Boolean}
   */
  @NotNull
  @Deprecated //has no Effect! Is a part of surface()
  public Boolean bedrock(){
    return this.bedrock;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the caves
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
   * @description Gets the decorations
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean decorations(){
    return this.decorations;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the mobs
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
   * @description Gets the noise
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
   * @description Gets the structures
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
   * @description Gets the surface
   * @return {@link Boolean}
   */
  @NotNull
  public Boolean surface(){
    return this.surface;
  }
}
