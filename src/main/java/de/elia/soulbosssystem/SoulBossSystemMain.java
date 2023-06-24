package de.elia.soulbosssystem;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.PluginInstances;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This main class is for the plugin soulbosssystem.
 */
public class SoulBossSystemMain {

  private static SoulBossSystemMain soulBossSystemMain;

  /**
   * @description Load this plugin.
   * @param main Requires the main class
   */
  public void enable(@NotNull JavaPlugin main) {
    soulBossSystemMain = this;
    this.soulBossSystemLogger().logInfo("Load SoulBossSystem plugin...");
    this.soulBossSystemLogger().logInfo("Load files...");
    SoulBossSystemConfigurationLoader.loadFiles(main);
    this.soulBossSystemLogger().logInfo("files loaded!");
    this.soulBossSystemLogger().logInfo("SoulBossSystem plugin loaded!");
  }

  /**
   * @description Unload this plugin.
   * @param main Requires the main class
   */
  public void disable(@NotNull JavaPlugin main) {
    SoulBossSystemConfigurationLoader.save(main);
  }

  /**
   * @description Gets the logger for this plugin.
   * @return the logger for this plugin
   */
  @NotNull
  public PluginLogger soulBossSystemLogger() {
    return PluginInstances.SOUL_BOSS_SYSTEM_LOGGER;
  }

  /**
   * @description Gets a instance of this class
   * @return this class
   */
  @NotNull
  public static SoulBossSystemMain soulBossSystemMain() {
    return soulBossSystemMain;
  }

  /**
   * @description Gets the instance of the main class of this system.
   * @return the instance of the main class of this system.
   */
  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }
}
