package de.elia;

import de.elia.api.Initialize;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.error.SaveError;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.world.WorldMain;
import de.elia.bossfightcreator.world.creator.CustomChunkGenerator;
import de.elia.systemclasses.PluginInstances;
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

public class PluginMain extends JavaPlugin {

  public static PluginMain main;
  private static WorldCreator worldCreator;
  public static World world;
  private WorldMain worldMain;

  public void onEnable() {
    PluginInstances.MAIN_LOGGER.logInfo("load de.elia.Main");
    main = this;
    PluginInstances.MAIN_LOGGER.logInfo("Checks if SoulLibrary loaded...");
    if (Bukkit.getPluginManager().getPlugin("SoulLibrary") == null) {
      PluginInstances.MAIN_LOGGER.logError("SoulLibrary is not loaded!");
      try {
        throw new NullPointerException("SoulLibrary is null!");
      } catch (NullPointerException exception) {
        PluginInstances.MAIN_LOGGER.logError("An error occurred while loading SoulLibrary! (See Stacktrace)");
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_38=null");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
        return;
      }
    }
    Initialize.init(Bukkit.getPluginManager(),this);
    PluginInstances.MAIN_LOGGER.logInfo("SoulLibrary is loaded!");
    PluginInstances.MAIN_LOGGER.logInfo("Try to load Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss and SoulBossSystem...");
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Load Achievements plugin...");
      PluginInstances.ACHIEVEMENT_MAIN.enable(this);
      PluginInstances.MAIN_LOGGER.logInfo("Achievements plugin loaded!");
    } catch (SoulPluginLoadException exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading Achievements plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_53=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Load Item...");
      PluginInstances.ITEM_MAIN.enable(this);
      PluginInstances.MAIN_LOGGER.logInfo("Item loaded!");
    } catch (SoulPluginLoadException exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading Item plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_66=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Load BossFightCreator...");
      PluginInstances.BOSS_FIGHT_CREATOR.enable(this);
      PluginInstances.MAIN_LOGGER.logInfo("BossFightCreator loaded!");
    } catch (SoulPluginLoadException exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading BossFightCreator plugin! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_78=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Load SoulBoss...");
      PluginInstances.SOUL_BOSS.enable(this);
      PluginInstances.MAIN_LOGGER.logInfo("SoulBoss loaded!");
    } catch (SoulPluginLoadException exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_89=exception");
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Load SoulBoss...");
      PluginInstances.SOUL_BOSS_SYSTEM_MAIN.enable(this);
      PluginInstances.MAIN_LOGGER.logInfo("SoulBoss loaded!");
    } catch (SoulPluginLoadException exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_101=exception");
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading SoulBoss plugin! (See Stacktrace)");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      PluginInstances.MAIN_LOGGER.logInfo("Unload Party...");
      PluginInstances.PARTY_MAIN.disable(this);
      PluginInstances.MAIN_LOGGER.logInfo("Party unloaded!");
    }catch (SoulPluginLoadException exception) {
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_113=null");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
    }
    PluginInstances.MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem loaded!");
    try {
      CommandRegister.registerCommands();
    } catch (Exception exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading Commands! (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_123=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      EventRegister.registerEvents(Bukkit.getPluginManager(), this);
    } catch (Exception exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading Events (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_133=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    try {
      this.worldMain = new WorldMain((Plugin)this);
      this.generateWorld("world_bossfight");
      ArenaHandler.init();
    } catch (Exception exception) {
      PluginInstances.MAIN_LOGGER.logError("An error occurred while loading the world and the arenas (See Stacktrace)");
      new SaveError().saveError(this, exception, "PluginMain-onEnable-line_145=exception");
      PluginInstances.MAIN_LOGGER.stacktrace(exception);
      PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
      this.disable();
      return;
    }
    PluginInstances.MAIN_LOGGER.logInfo("Loaded de.elia.Main");
  }

  public void onDisable() {
    PluginInstances.MAIN_LOGGER.logInfo("Stop de.elia.Main");
    PluginInstances.MAIN_LOGGER.logInfo("Checks if " + Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + " and " + Bukkit.getPluginManager().getPlugin("WorldEdit").getName() + " loaded...");
    if (Bukkit.getPluginManager().getPlugin("SoulLibrary") != null || Bukkit.getPluginManager().getPlugin("WorldEdit") != null) {
      PluginInstances.MAIN_LOGGER.logInfo(Bukkit.getPluginManager().getPlugin("SoulLibrary").getName() + " and " + Bukkit.getPluginManager().getPlugin("WorldEdit").getName() + "is loaded!");
      PluginInstances.MAIN_LOGGER.logInfo("Try to unload Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem...");
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload achievement plugin...");
        PluginInstances.ACHIEVEMENT_MAIN.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("Achievement plugin unloaded!");
      } catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_165=exception");
        PluginInstances.MAIN_LOGGER.logError("An error occurred while disabling achievement plugin! (See Stacktrace)");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload BossFightCreator...");
        PluginInstances.BOSS_FIGHT_CREATOR.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("BossFightCreator unloaded!");
      } catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_175=exception");
        PluginInstances.MAIN_LOGGER.logError("An error occurred while disabling BossFightCreator! (See Stacktrace)");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload Item...");
        PluginInstances.ITEM_MAIN.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("Item unloaded!");
      } catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_185=exception");
        PluginInstances.MAIN_LOGGER.logError("An error occurred while disabling Item! (See Stacktrace)");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload SoulBoss...");
        PluginInstances.SOUL_BOSS.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("SoulBoss unloaded!");
      } catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_195=exception");
        PluginInstances.MAIN_LOGGER.logError("An error occurred while disabling SoulBoss! (See Stacktrace)");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload Party...");
        PluginInstances.PARTY_MAIN.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("Party unloaded!");
      }catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onEnable-line_205=null");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("Disable SoulBossSystem");
        this.disable();
      }
      try {
        PluginInstances.MAIN_LOGGER.logInfo("Unload SoulBossSystem...");
        PluginInstances.SOUL_BOSS_SYSTEM_MAIN.disable(this);
        PluginInstances.MAIN_LOGGER.logInfo("SoulBossSystem unloaded!");
      } catch (SoulPluginLoadException exception) {
        new SaveError().saveError(this, exception, "PluginMain-onDisable-line_215=exception");
        PluginInstances.MAIN_LOGGER.logError("An error occurred while disabling SoulBossSystem! (See Stacktrace)");
        PluginInstances.MAIN_LOGGER.stacktrace(exception);
        PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
      }
      PluginInstances.MAIN_LOGGER.logInfo("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem unloaded!");
      PluginInstances.MAIN_LOGGER.logInfo("de.elia.Main stopped!");
      return;
    }
    PluginInstances.MAIN_LOGGER.logWarning("Achievements, TheZepserAPI, BossFightCreator, Items, SoulBoss, Party and SoulBossSystem not corect unloaded!");
    PluginInstances.MAIN_LOGGER.logError("SoulLibrary and WorldEdit is not loaded!");
    PluginInstances.MAIN_LOGGER.logWarning("de.elia.Main not corect stopped!");
  }

  public void onReload() {
    this.onDisable();
    this.onEnable();
  }

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
    world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
    world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
    world.setGameRule(GameRule.KEEP_INVENTORY, true);
    this.worldMain.logInfo("A new org.bukkit.World is created!");
    this.worldMain.logInfo("Ending world creation progress...");
    this.worldMain.logInfo("100%");
    this.worldMain.logInfo("world creation progress ended!");
  }

  public WorldMain worldMain() {
    return this.worldMain;
  }

  @NotNull
  public static PluginMain main() {
    return main;
  }

  @Nullable
  public Plugin soulMain() {
    return Bukkit.getPluginManager().getPlugin("SoulLibrary");
  }

  public void disable() {
    Bukkit.getPluginManager().disablePlugin(this);
  }

  @Nullable
  public Plugin worldEditPlugin() {
    return Bukkit.getPluginManager().getPlugin("WorldEdit");
  }
}
