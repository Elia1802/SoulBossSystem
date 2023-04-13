package de.elia;

import de.elia.api.TheZepserAPIMain;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Files;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.load.start.StartPlugin;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.items.ItemMain;
import de.elia.soulboss.SoulBoss;
import de.elia.soulmain.SoulMain;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.Exception;
import java.lang.String;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link JavaPlugin}
 * @description This is the Main Class of the Plugin SoulBoss and BossFightCreator.
 */
public class Main extends JavaPlugin {

  public static Main main;//Instance of this Plugin
  private final SoulMain soulMain = (SoulMain) Bukkit.getPluginManager().getPlugin("SoulMain");//Gets the Soul API of Elia
  private static final TheZepserAPIMain THE_ZEPSER_API_MAIN = new TheZepserAPIMain();//Gets the Main class of the TheZepserAPI
  private static final ItemMain ITEM_MAIN = new ItemMain();//Gets the the Main class of Item
  private static final BossFightCreator BOSS_FIGHT_CREATOR = new BossFightCreator();//Gets the Main class of the bossfightcreator Plugin
  private static final SoulBoss SOUL_BOSS = new SoulBoss();//Gets the Main class of the SoulBoss Plugin
  private static final PluginLogger MAIN_LOGGER = new PluginLogger("SoulBossSystem -> SoulBossSystem");//A logger for the  SoulBossPlugin
  public static final PluginLogger BOSS_FIGHT_CREATOR_LOGGER = new PluginLogger("SoulBossSystem -> BossFightCreator");//A logger for the  BossFightCreator
  public static final PluginLogger SOUL_BOSS_LOGGER = new PluginLogger("SoulBossSystem -> SoulBoss");//A logger for the Plugin
  public static final PluginLogger THE_ZEPSER_API_LOGGER = new PluginLogger("SoulBossSystem -> TheZepserAPI");//A logger for the TheZepserAPI
  public static final PluginLogger ITEM_LOGGER = new PluginLogger("SoulBossSystem -> Item");
  private World world;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable(){
    MAIN_LOGGER.logInfo("load de.elia.Main");
    main = this;
    MAIN_LOGGER.logInfo("Checks if " + soulMain.getName() + "loaded...");
    if (soulMain == null){
      MAIN_LOGGER.logError(soulMain.getName() + "is not loaded!");
      MAIN_LOGGER.logWarning("Disable this Plugin");
      this.disable();
      return;
    }
    MAIN_LOGGER.logInfo(soulMain.getName() + "is loaded!");
    MAIN_LOGGER.logInfo("Try to load TheZepserAPI, Item, BossFightCreator and SoulBoss...");
    try {
      MAIN_LOGGER.logInfo("Load TheZepserAPI...");
      THE_ZEPSER_API_MAIN.enable(this);
      MAIN_LOGGER.logInfo("TheZepserAPI loaded!");
      MAIN_LOGGER.logInfo("Load Item...");
      ITEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Item loaded!");
      MAIN_LOGGER.logInfo("Load BossFightCreator...");
      BOSS_FIGHT_CREATOR.enable(this);
      MAIN_LOGGER.logInfo("BossFightCreator loaded!");
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI, Item, BossFightCreator or SoulBoss! (See Stacktrace)");
      exception.printStackTrace();
      MAIN_LOGGER.logWarning("Disable this Plugin");
      this.disable();
      return;
    }
    MAIN_LOGGER.logInfo("TheZepserAPI, Item, BossFightCreator and SoulBoss loaded!");
    MAIN_LOGGER.logInfo("Loaded de.elia.Main");
    try {
      StartPlugin.loadArenas();
    }catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload all thinks in this Plugin.
   */
  @Override
  public void onDisable(){
    MAIN_LOGGER.logInfo("Stop de.elia.Main");
    MAIN_LOGGER.logInfo("Checks if " + soulMain.getName() + "loaded...");
    if (!(soulMain == null)) {
      MAIN_LOGGER.logInfo(soulMain.getName() + "is loaded!");
      MAIN_LOGGER.logInfo("Try to unload TheZepserAPI, Item, BossFightCreator and SoulBoss...");
      try {
        MAIN_LOGGER.logInfo("Unload TheZepserAPI...");
        THE_ZEPSER_API_MAIN.disable();
        MAIN_LOGGER.logInfo("TheZepserAPI unloaded!");
        MAIN_LOGGER.logInfo("Unload Item...");
        ITEM_MAIN.disable();
        MAIN_LOGGER.logInfo("Item unloaded!");
        MAIN_LOGGER.logInfo("Unload BossFightCreator...");
        BOSS_FIGHT_CREATOR.disable();
        MAIN_LOGGER.logInfo("BossFightCreator unloaded!");
        MAIN_LOGGER.logInfo("Unload SoulBoss...");
        SOUL_BOSS.disable(this);
        MAIN_LOGGER.logInfo("SoulBoss unloaded!");
        MAIN_LOGGER.logInfo("TheZepserAPI, Item, BossFightCreator and SoulBoss unloaded!");
        return;
      }catch (Exception exception) {
        MAIN_LOGGER.logError("An error occurred while unloading TheZepserAPI, Item, BossFightCreator or SoulBoss! (See Stacktrace)");
        exception.printStackTrace();
        MAIN_LOGGER.logWarning("de.elia.Main stopped!");
        return;
      }
    }
    MAIN_LOGGER.logError(soulMain.getName() + "is not loaded!");
    MAIN_LOGGER.logWarning("de.elia.Main stopped!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load a custom World
   * @param worldName Name of the world that this will be applied to
   * @param id Unique ID, if any, that was specified to indicate which
   *     generator was requested
   * @return {@link ChunkGenerator}
   */
  @Override
  public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id){
    var worldMain = BossFightCreator.worldMain();
    worldMain.logInfo("0%");
    var world_status = Files.WORLD_STATUS;
    if (world_status.get("status") == null|| world_status.get("status", false)) {
      worldMain.logInfo("Create a new World... (" + worldName + ":" + id + ")");
      worldMain.logInfo("Load org.bukkit.WorldGenerator...");
      WorldCreator creator = new WorldCreator(id);
      worldMain.logInfo("org.bukkit.WorldGenerator loaded!");
      worldMain.logInfo("25%");
      worldMain.logInfo("Load de.elia.bossfightcreator.world.creator.CustomChunkGenerator...");
      CustomChunkGenerator generator = new CustomChunkGenerator();
      worldMain.logInfo("de.elia.bossfightcreator.world.creator.CustomChunkGenerator loaded!");
      worldMain.logInfo("50%");
      worldMain.logInfo("Set the custom generator to the WorldGenerator...");
      creator.generator(generator);
      worldMain.logInfo("The custom generator to the WorldGenerator sets!");
      worldMain.logInfo("75%");
      worldMain.logInfo("Create a new org.bukkit.World...");
      Plugin.world_bossfight = Bukkit.createWorld(creator);
      worldMain.logInfo("A new org.bukkit.World is created!");
      worldMain.logInfo("Ending world creation progress...");
      world_status.set("status", true);
      world_status.save();
      worldMain.logInfo("world creation progress ended!");
      worldMain.logInfo("100%");
    }else {
      worldMain.logInfo("World " + worldName + ":" + id + " exist!");
      worldMain.logInfo("100%");
    }
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
   * @return {@link Main}
   */
  @NotNull
  public static Main main() {
    return main;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the SoulMain Plugin.
   * @return {@link SoulMain}
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

}
