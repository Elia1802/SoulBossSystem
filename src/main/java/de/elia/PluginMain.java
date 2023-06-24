package de.elia;

import de.elia.api.logging.SaveError;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.systemclasses.register.commands.CommandRegister;
import de.elia.systemclasses.register.events.EventRegister;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static de.elia.systemclasses.PluginInstances.*;

/**
 * @author Elia
 * @description This is the main class of this system.
 * @extends {@link JavaPlugin}
 */
public class PluginMain extends JavaPlugin {

  public static PluginMain main;
  private static WorldCreator worldCreator;
  public static World world;
  private WorldMain worldMain;

  /**
   * @description Load this System.
   */
  @Override
  public void onEnable() {
    MAIN_LOGGER.logInfo("load de.elia.Main");
    main = this;
    MAIN_LOGGER.logInfo("Checks if " + Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + "loaded...");
    if (Bukkit.getPluginManager().getPlugin("SoulLibrary") == null) {
      MAIN_LOGGER.logError(Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + "is not loaded!");
      try {
        throw new NullPointerException("SoulLibrary is null!");
      }
      catch (NullPointerException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_47=null");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
        return;
      }
    }
    if (Bukkit.getPluginManager().getPlugin("WorldEdit") == null) {
      MAIN_LOGGER.logError(Bukkit.getPluginManager().getPlugin("WorldEdit").getName() + "is not loaded!");
      try {
        throw new NullPointerException("WorldEdit is null!");
      }
      catch (NullPointerException exception) {
        MAIN_LOGGER.logError("An error occurred while loading TheZepserAPI! (See Stacktrace)");
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_61=null");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
        return;
      }
    }
    MAIN_LOGGER.logInfo(Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + "is loaded!");
    MAIN_LOGGER.logInfo("Try to load Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");
    try {
      MAIN_LOGGER.logInfo("Load Achievements plugin...");
      ACHIEVEMENT_MAIN.enable();
      MAIN_LOGGER.logInfo("Achievements plugin loaded!");
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Achievements plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_77=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      MAIN_LOGGER.logInfo("Load Item...");
      ITEM_MAIN.enable(this);
      MAIN_LOGGER.logInfo("Item loaded!");
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Item plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_90=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      MAIN_LOGGER.logInfo("Load BossFightCreator...");
      BOSS_FIGHT_CREATOR.enable();
      MAIN_LOGGER.logInfo("BossFightCreator loaded!");
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading BossFightCreator plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_103=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      MAIN_LOGGER.logInfo("Load SoulBoss...");
      SOUL_BOSS.enable(this);
      MAIN_LOGGER.logInfo("SoulBoss loaded!");
    }
    catch (Exception exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_115=exception");
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
    }
    catch (Exception exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_128=exception");
      MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem loaded!");
    try {
      CommandRegister.registerCommands();
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Commands! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_141=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      EventRegister.registerEvents(Bukkit.getPluginManager(), this);
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading Events (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_152=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      this.worldMain = new WorldMain((Plugin)this);
      this.generateWorld("world_bossfight");
      ArenaHandler.init();
    }
    catch (Exception exception) {
      MAIN_LOGGER.logError("An error occurred while loading the world and the arenas (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_164=exception");
      MAIN_LOGGER.stacktrace(exception);
      MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    MAIN_LOGGER.logInfo("Loaded de.elia.Main");
  }

  /**
   * @description Unload this System.
   */
  @Override
  public void onDisable() {
    MAIN_LOGGER.logInfo("Stop de.elia.Main");
    MAIN_LOGGER.logInfo("Checks if " + Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + " and " + Bukkit.getPluginManager().getPlugin("WorldEdit").getName() + " loaded...");
    if (Bukkit.getPluginManager().getPlugin("SoulLibrary") != null || Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
      MAIN_LOGGER.logInfo(Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + " and " + Bukkit.getPluginManager().getPlugin("WorldEdit").getName() + "is loaded!");
      MAIN_LOGGER.logInfo("Try to unload Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");
      try {
        MAIN_LOGGER.logInfo("Unload achievement plugin...");
        ACHIEVEMENT_MAIN.disable();
        MAIN_LOGGER.logInfo("Achievement plugin unloaded!");
      }
      catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_190=exception");
        MAIN_LOGGER.logError("An error occurred while disabling achievement plugin! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload BossFightCreator...");
        BOSS_FIGHT_CREATOR.disable();
        MAIN_LOGGER.logInfo("BossFightCreator unloaded!");
      }
      catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_201=exception");
        MAIN_LOGGER.logError("An error occurred while disabling BossFightCreator! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload Item...");
        ITEM_MAIN.disable();
        MAIN_LOGGER.logInfo("Item unloaded!");
      }
      catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_212=exception");
        MAIN_LOGGER.logError("An error occurred while disabling Item! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload SoulBoss...");
        SOUL_BOSS.disable();
        MAIN_LOGGER.logInfo("SoulBoss unloaded!");
      }
      catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_223=exception");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBoss! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        MAIN_LOGGER.logInfo("Unload SoulBossSystem...");
        SOUL_BOSS_SYSTEM_MAIN.disable(this);
        MAIN_LOGGER.logInfo("SoulBossSystem unloaded!");
      }
      catch (Exception exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_234=exception");
        MAIN_LOGGER.logError("An error occurred while disabling SoulBossSystem! (See Stacktrace)");
        MAIN_LOGGER.stacktrace(exception);
        MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem unloaded!");
      MAIN_LOGGER.logInfo("de.elia.Main stopped!");
      return;
    }
    MAIN_LOGGER.logWarning("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem not corect unloaded!");
    MAIN_LOGGER.logError("SoulLibrary and WorldEdit is not loaded!");
    MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
  }

  /**
   * @description Reload this System.
   */
  public void onReload() {
    this.onDisable();
    this.onEnable();
  }

  /**
   * @description Generate the world.
   * @param id Requires the id.
   */
  public void generateWorld(String id) {
    this.worldMain.logInfo("0%");
    this.worldMain.logInfo("Create a new World... (" + id + ")");
    this.worldMain.logInfo("Load org.bukkit.WorldGenerator...");
    worldCreator = new WorldCreator(id);
    this.worldMain.logInfo("org.bukkit.WorldGenerator loaded!");
    this.worldMain.logInfo("25%");
    this.worldMain.logInfo("Load de.elia.bossfightcreator.world.creator.CustomChunkGenerator...");
    CustomChunkGenerator generator = new CustomChunkGenerator();
    this.worldMain.logInfo("de.elia.bossfightcreator.world.creator.CustomChunkGenerator loaded!");
    this.worldMain.logInfo("50%");
    this.worldMain.logInfo("Set the custom generator to the WorldGenerator...");
    worldCreator.generator(generator);
    this.worldMain.logInfo("The custom generator to the WorldGenerator sets!");
    this.worldMain.logInfo("75%");
    this.worldMain.logInfo("Create a new org.bukkit.World...");
    world = Bukkit.createWorld(worldCreator);
    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
    world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
    world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
    this.worldMain.logInfo("A new org.bukkit.World is created!");
    this.worldMain.logInfo("Ending world creation progress...");
    this.worldMain.logInfo("100%");
    this.worldMain.logInfo("world creation progress ended!");
  }

  /**
   * @description Gets a instance of the class worldmain.
   * @return a instance of the class worldmain.
   */
  public WorldMain worldMain() {
    return this.worldMain;
  }

  /**
   * @description Gets the instance of this class.
   * @return this class
   */
  @NotNull
  public static PluginMain main() {
    return main;
  }

  /**
   * @description Gets the plugin soul main
   * @return the {@link Plugin} soulmain
   */
  @Nullable
  public Plugin soulMain() {
    return Bukkit.getPluginManager().getPlugin("SoulLibrary");
  }

  /**
   * @description Disable this plugin
   */
  public void disable() {
    Bukkit.getPluginManager().disablePlugin(this);
  }

  /**
   * @description Gets the plugin worldedit
   * @return the {@link Plugin} worldedit
   */
  @Nullable
  public Plugin worldEditPlugin() {
    return Bukkit.getPluginManager().getPlugin("WorldEdit");
  }
}
