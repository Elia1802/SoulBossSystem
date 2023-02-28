package de.elia.soulboss;

import de.elia.soulboss.plugin.load.Load;
import de.elia.soulboss.plugin.load.stop.disable.Disable;
import de.elia.soulboss.world.Arena;
import de.elia.soulboss.world.Builder;
import de.elia.soulboss.world.Generator;
import de.elia.soulboss.world.Settings;
import de.elia.soulboss.world.Utils;
import de.elia.soulboss.world.generator.WorldGenerator;
import de.elia.soulboss.world.settings.WorldSettings;
import de.elia.soulboss.world.worldborder.WorldBorderBuilder;
import de.elia.soulboss.world.worldedit.BossArena;
import de.elia.soulboss.world.worldlog.WorldLogger;
import de.elia.soulmain.SoulMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link JavaPlugin}
 * @implements {@link Utils}
 * @description This is the Main Class of the Plugin SoulBoss.
 */
public class SoulBoss extends JavaPlugin implements Utils {
  private static SoulBoss soulBoss;//Instance of this Plugin
  private final SoulMain soulMain = (SoulMain) Bukkit.getPluginManager().getPlugin("SoulMain"); //Gets the Soul API of Elia
  private final Load loader = new Load();//the loader of this Plugin
  private Arena arena;
  private Settings settings;
  private Generator generator;
  private Utils utils;
  private World world;
  private Builder builder;
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();//Save the status of the player for the bossfight.
  private static final Map<Player, Zombie> playerZombieMap = new HashMap<>();//Save the zombie to the Player.
  private final MiniMessage miniMessage = MiniMessage.miniMessage();//Gets the MiniMessage API.
  private final WorldLogger worldLogger = new WorldLogger("Soul-World-Generator");//Create a logger to the WorldGeneration

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable(){
    soulBoss = this;
    arena = new BossArena();
    settings = new WorldSettings();
    generator = new WorldGenerator(this);
    builder = new WorldBorderBuilder();
    utils = this;
    if (soulMain == null)return;
    loader.loadPlugin(this, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reload all thinks in this Plugin.
   */
  public void onReload(){
    soulBoss = this;
    arena = new BossArena();
    settings = new WorldSettings();
    generator = new WorldGenerator(this);
    builder = new WorldBorderBuilder();
    utils = this;
    if (soulMain == null)return;
   loader.reloadPlugin(this, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reload all Configurations in this Plugin.
   */
  public void onConfigurationReload(){
    if (soulMain == null)return;
    loader.reloadConfiguration(this);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload all thinks in this Plugin.
   */
  @Override
  public void onDisable(){
    if (soulMain == null)return;
    new Disable().disable(this);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Bossfight world.
   * @param worldName Name of the world that this will be applied to
   * @param id Unique ID, if any, that was specified to indicate which
   *     generator was requested
   * @return null
   */
  @Override
  @Nullable
  public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id){
    var logger = SoulBoss.soulBoss().worldLogger();
    logger.log("Load World: " + worldName + " + " + id + "...");
    logger.log("load WorldCreator...");
    WorldCreator worldCreator = new WorldCreator(id);
    logger.log("WorldCreator loaded!");
    logger.log("load WorldGenerator...");
    WorldGenerator generator = new WorldGenerator(this);
    logger.log("WorldGenerator loaded!");
    logger.log("Set ChunkGenerator...");
    worldCreator.generator(generator);
    logger.log("ChunkLoader sets!");
    logger.log("Create World...");
    world = Bukkit.createWorld(worldCreator);
    logger.log("World created!");
    logger.log("Set WorldBorder...");
    SoulBoss.soulBoss().builder().setWorldBorder(this.world());
    logger.log("World: " + worldName + " + " + id + " loaded!");
    return null;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the bossfight world.
   * @return the world of this plugin
   */
  @NotNull
  public World world() {
    if (world == null) {
      this.getDefaultWorldGenerator("Bossfight-World", "world_bossfight");
      return world;
    }
    return world;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Is the Instance of this Plugin.
   * @return {@link #soulBoss}
   */
  @NotNull
  public static SoulBoss soulBoss() {
    return soulBoss;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the SoulMain Plugin.
   * @return {@link #soulMain}
   */
  @NotNull
  public SoulMain soulMain() {
    return soulMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disable this Plugin.
   */
  public void disable(){
    Bukkit.getPluginManager().disablePlugin(this);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Arena Interface.
   * @return {@link BossArena}
   */
  @NotNull
  public Arena arena(){
    return this.arena;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link Settings} Interface.
   * @return {@link WorldSettings}
   */
  @NotNull
  public Settings settings(){
    return this.settings;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link Generator} Interface.
   * @return {@link WorldGenerator}
   */
  @NotNull
  public Generator generator(){
    return this.generator;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link Utils} Interface.
   * @return Of {@link SoulBoss} the world methodes.
   */
  @NotNull
  public Utils utils(){
    return this.utils;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link Builder} Interface.
   * @return {@link WorldBorderBuilder}
   */
  @NotNull
  public Builder builder(){
    return this.builder;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the status Map. ({@link Map<Player, Integer>})
   * @return a new {@link HashMap}
   */
  @NotNull
  public static Map<Player, Integer> playerStatusMap() {
    return playerStatusMap;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the playerZombie Map. ({@link Map<Player, Zombie>})
   * @return a new {@link HashMap}
   */
  @NotNull
  public static Map<Player, Zombie> playerZombieMap() {
    return playerZombieMap;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the MiniMessage
   * @return {@link #miniMessage}
   */
  @NotNull
  public MiniMessage miniMessage() {
    return this.miniMessage;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the world generator logger.
   * @return a new {@link WorldLogger}
   */
  @NotNull
  public WorldLogger worldLogger(){
    return this.worldLogger;
  }
}
