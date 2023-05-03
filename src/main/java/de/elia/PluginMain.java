package de.elia;

import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.elia.api.Main;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Files;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.systemclasses.logging.PluginLogger.SaveError;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Exception;
import java.lang.String;

import static de.elia.PluginInstances.*;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link JavaPlugin}
 * @description This is the Main Class of the Plugin SoulBoss and BossFightCreator.
 */
public class PluginMain extends JavaPlugin {

  public static PluginMain main;//Instance of this Plugin
  private final WorldGuardPlugin worldGuardPlugin = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
  private final Main library = (Main) Bukkit.getPluginManager().getPlugin("SoulLibrary");//Gets the Soul API of Elia
  private static WorldCreator worldCreator;
  public static World WORLD;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable() {
    MAIN_LOGGER.logInfo("load de.elia.Main");
    main = this;
    MAIN_LOGGER.logInfo("Checks if " + library.getName() + "loaded...");
    if (library == null){
      MAIN_LOGGER.logError(library.getName() + "is not loaded!");
      try {
        throw new SoulBossSystemNullException(library.getName() + " is not loaded!");
      } catch (SoulBossSystemNullException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(exception, "PluginMain-onEnable-line_53=null");
        MAIN_LOGGER.stackstrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }
      MAIN_LOGGER.logWarning("Disable this Plugin");
      this.disable();
      return;
    }else if (worldGuardPlugin == null) {
      MAIN_LOGGER.logError(worldGuardPlugin.getName() + "is not loaded!");
      try {
        throw new SoulBossSystemNullException(worldGuardPlugin.getName() + " is not loaded!");
      } catch (SoulBossSystemNullException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(exception, "PluginMain-onEnable-line_67=null");
        MAIN_LOGGER.stackstrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }
      MAIN_LOGGER.logWarning("Disable this Plugin");
      this.disable();
      return;
    }
    MAIN_LOGGER.logInfo(library.getName() + "is loaded!");
    MAIN_LOGGER.logInfo("Try to load TheZepserAPI, Item, BossFightCreator and SoulBoss...");
    try {
      MAIN_LOGGER.logInfo("Load TheZepserAPI...");
      THE_ZEPSER_API_MAIN.enable(this);
      MAIN_LOGGER.logInfo("TheZepserAPI loaded!");
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      new SaveError().saveError(exception, "PluginMain-onEnable-line_85=exception");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      MAIN_LOGGER.logInfo("Load Item...");
      ITEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Item loaded!");
    }catch (SoulBossSystemNullException exception) {
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      new SaveError().saveError(exception, "PluginMain-onEnable-line_97=null");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      new SaveError().saveError(exception, "PluginMain-onEnable-line_97=exception");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }
    MAIN_LOGGER.logInfo("Load BossFightCreator...");
    try {
      BOSS_FIGHT_CREATOR.enable(this);
    } catch (FileNotFoundException exception){
      new SaveError().saveError(exception, "PluginMain-onEnable-line_115=fileNotFound");
      exception.printStackTrace();
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss! (See Stacktrace)");
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    } catch (IOException | WorldEditException exception){
      new SaveError().saveError(exception, "PluginMain-onEnable-line_115=io_or_worldedit");
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }
    MAIN_LOGGER.logInfo("BossFightCreator loaded!");
    MAIN_LOGGER.logInfo("Load SoulBoss...");
    try {
      SOUL_BOSS.enable(this);
    } catch (SoulBossSystemNullException exception) {
      new SaveError().saveError(exception, "PluginMain-onEnable-line_132=null");
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }catch (Exception exception) {
      new SaveError().saveError(exception, "PluginMain-onEnable-line_132=exception");
      MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
      MAIN_LOGGER.stackstrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }
    MAIN_LOGGER.logInfo("SoulBoss loaded!");
    MAIN_LOGGER.logInfo("TheZepserAPI, Item, BossFightCreator and SoulBoss loaded!");
    MAIN_LOGGER.logInfo("Loaded de.elia.Main");
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
    MAIN_LOGGER.logInfo("Checks if " + library.getName() + " and " + worldGuardPlugin.getName() + " loaded...");
    if (!(library == null) || !(worldGuardPlugin == null)) {
      MAIN_LOGGER.logInfo(library.getName() + " and " + worldGuardPlugin.getName() + "is loaded!");
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
        new SaveError().saveError(exception, "PluginMain-onEnable-line_166-175=exception");
        MAIN_LOGGER.logError("An error occurred while unloading TheZepserAPI, Item, BossFightCreator or SoulBoss! (See Stacktrace)");
        MAIN_LOGGER.stackstrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main stopped!");
        return;
      }
    }
    MAIN_LOGGER.logError(library.getName() + " and " + worldGuardPlugin.getName() + "is not loaded!");
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
      WORLD = Bukkit.createWorld(worldCreator);
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
   * @description Is the Instance of this Plugin.
   * @return {@link PluginMain}
   */
  @NotNull
  public static PluginMain main() {
    return main;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the SoulMain Plugin.
   * @return {@link Main}
   */
  @NotNull
  public Main soulMain() {
    return library;
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
