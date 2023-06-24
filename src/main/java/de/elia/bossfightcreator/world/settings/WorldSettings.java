package de.elia.bossfightcreator.world.settings;

import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description In this class I set the settings for the world.
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

  @NotNull
  public Biome biome() {
    return this.biome;
  }

  @Deprecated
  @NotNull
  public Boolean bedrock() {
    return this.bedrock;
  }

  @NotNull
  public Boolean caves() {
    return this.caves;
  }

  @NotNull
  public Boolean decorations() {
    return this.decorations;
  }

  @NotNull
  public Boolean mobs() {
    return this.mobs;
  }

  @NotNull
  public Boolean noise() {
    return this.noise;
  }

  @NotNull
  public Boolean structures() {
    return this.structures;
  }

  @NotNull
  public Boolean surface() {
    return this.surface;
  }
}

