package de.elia;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.elia.api.Main;
import de.elia.api.logging.PluginLogger.SaveError;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.systemclasses.register.commands.CommandRegister;
import de.elia.systemclasses.register.events.EventRegister;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import static de.elia.PluginInstances.*;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @extends {@link JavaPlugin}
 * @description This is the Main Class of the Plugin SoulBoss and BossFightCreator.
 */
public class PluginMain extends JavaPlugin {

  public static PluginMain main;//Instance of this Plugin
  private final WorldGuardPlugin worldGuardPlugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
  private final Main library = (Main) Bukkit.getPluginManager().getPlugin("SoulLibrary");//Gets the Soul API of Elia
  private static WorldCreator worldCreator;
  public static World world;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable() {
    //Load this main
    MAIN_LOGGER.logInfo("load de.elia.Main");
    main = this;
    //Checks if library SoulLibrary loaded
    MAIN_LOGGER.logInfo("Checks if " + library.getName() + "loaded...");
    if (library == null){
      MAIN_LOGGER.logError(library.getName() + "is not loaded!");
      try {
        throw new NullPointerException("SoulLibrary is null!");
      } catch (NullPointerException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_60=null");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }
      return;
    }
    //Checks if API WorldGuard loaded
    if (worldGuardPlugin == null) {
      MAIN_LOGGER.logError(worldGuardPlugin.getName() + "is not loaded!");
      try {
        throw new NullPointerException("Worldguard is null!");
      } catch (NullPointerException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_75=null");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }
      return;
    }
    MAIN_LOGGER.logInfo(library.getName() + "is loaded!");
    //Load all Plugins
    MAIN_LOGGER.logInfo("Try to load Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");
    //Load the Achievements plugin
    try {
      MAIN_LOGGER.logInfo("Load Achievements plugin...");
      ACHIEVEMENT_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Achievements plugin loaded!");
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Achievements plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_93=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    //Load the Item plugin
    try {
      MAIN_LOGGER.logInfo("Load Item...");
      ITEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Item loaded!");
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Item plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_122=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    //Load the BossFightCreator plugin
    try {
      MAIN_LOGGER.logInfo("Load BossFightCreator...");
      BOSS_FIGHT_CREATOR.enable(this);
      MAIN_LOGGER.logInfo("BossFightCreator loaded!");
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading BossFightCreator plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_143=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    //Load the SoulBoss plugin
    try {
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    }catch (Exception exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_164=exception");
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS_SYSTEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    }catch (Exception exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_164=exception");
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    this.generateWorld("world_bossfight");
    //Send load finish message of the plugins
    MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem loaded!");
    //Load Arenas
    ArenaHandler.init();
    //Load the Commands
    try {
      CommandRegister.registerCommand(this);
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Commands! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_233=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      EventRegister.registerEvents(Bukkit.getPluginManager(), this);
    } catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Events See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_244=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    //Send finish message that all thinks loaded
    MAIN_LOGGER.logInfo("Loaded de.elia.Main");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Unload all thinks in this Plugin.
   */
  @Override
  public void onDisable(){
    MAIN_LOGGER.logInfo("Stop de.elia.Main");
    MAIN_LOGGER.logInfo("Checks if " + library.getName() + " and " + worldGuardPlugin.getName() + " loaded...");
    if (!(library == null) || !(worldGuardPlugin == null)) {
      MAIN_LOGGER.logInfo(library.getName() + " and " + worldGuardPlugin.getName() + "is loaded!");
      MAIN_LOGGER.logInfo("Try to unload Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");
      //Disable achievement plugin
      try {
        MAIN_LOGGER.logInfo("Unload achievement plugin...");
        ACHIEVEMENT_MAIN.disable();
        MAIN_LOGGER.logInfo("Achievement plugin unloaded!");
      }catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_275=exception");
        MAIN_LOGGER.logError("An error occurred while disabling achievement plugin! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload BossFightCreator...");
        BOSS_FIGHT_CREATOR.disable();
        MAIN_LOGGER.logInfo("BossFightCreator unloaded!");
      }catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_299=exception");
        MAIN_LOGGER.logError("An error occurred while disabling BossFightCreator! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload Item...");
        ITEM_MAIN.disable();
        MAIN_LOGGER.logInfo("Item unloaded!");
      }catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_311=exception");
        MAIN_LOGGER.logError("An error occurred while disabling Item! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload SoulBoss...");
        SOUL_BOSS.disable(this);
        MAIN_LOGGER.logInfo("SoulBoss unloaded!");
      }catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_323=exception");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBoss! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload SoulBossSystem...");
        SOUL_BOSS_SYSTEM_MAIN.disable(this);
        MAIN_LOGGER.logInfo("SoulBossSystem unloaded!");
      }catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_335=exception");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBossSystem! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem unloaded!");
      MAIN_LOGGER.logInfo("de.elia.Main stopped!");
      return;
    }
    MAIN_LOGGER.logWarning("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem not corect unloaded!");
    MAIN_LOGGER.logError(library.getName() + " and " + worldGuardPlugin.getName() + "is not loaded!");
    MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Reload the plugin
   */
  public void onReload(){
    this.onDisable();
    this.onEnable();
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Load a custom World
   * @param id Unique ID, if any, that was specified to indicate which
   *     generator was requested
   * @return {@link ChunkGenerator}
   */
  public void generateWorld(String id){
    var worldMain = BossFightCreatorMain.worldMain();
    worldMain.logInfo("0%");
    worldMain.logInfo("Create a new World... (" + id + ")");
    worldMain.logInfo("Load org.bukkit.WorldGenerator...");
    worldCreator = new WorldCreator(id);
    worldMain.logInfo("org.bukkit.WorldGenerator loaded!");
    worldMain.logInfo("25%");
    worldMain.logInfo("Load de.elia.bossfightcreator.world.creator.CustomChunkGenerator...");
    CustomChunkGenerator generator = new CustomChunkGenerator();
    worldMain.logInfo("de.elia.bossfightcreator.world.creator.CustomChunkGenerator loaded!");
    worldMain.logInfo("50%");
    worldMain.logInfo("Set the custom generator to the WorldGenerator...");
    worldCreator.generator(generator);
    worldMain.logInfo("The custom generator to the WorldGenerator sets!");
    worldMain.logInfo("75%");
    worldMain.logInfo("Create a new org.bukkit.World...");
    world = Bukkit.createWorld(worldCreator);
    worldMain.logInfo("A new org.bukkit.World is created!");
    worldMain.logInfo("Ending world creation progress...");
    worldMain.logInfo("100%");
    worldMain.logInfo("world creation progress ended!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Is the Instance of this Plugin.
   * @return {@link PluginMain}
   */
  @NotNull
  public static PluginMain main() {
    return main;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the SoulMain Plugin.
   * @return {@link Main}
   */
  @NotNull
  public Main soulMain() {
    return library;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Disable this Plugin.
   */
  public void disable(){
    Bukkit.getPluginManager().disablePlugin(this);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the WorldGuard plugin
   * @return {@link WorldGuardPlugin}
   */
  public WorldGuardPlugin worldGuardPlugin() {
    return worldGuardPlugin;
  }

  public Main library() {
    return library;
  }
}
