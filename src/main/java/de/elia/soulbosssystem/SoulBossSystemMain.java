package de.elia.soulbosssystem;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description The main for the soulbosssystem plugin
 */
public class SoulBossSystemMain {

  private static SoulBossSystemMain soulBossSystemMain;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Load the soulbosssystem plugin
   * @param main Requires the main class
   */
  public void enable(@NotNull JavaPlugin main){
    soulBossSystemMain = this;
    this.soulBossSystemLogger().logInfo("Load SoulBossSystem plugin...");
    this.soulBossSystemLogger().logInfo("Load files...");
    SoulBossSystemConfigurationLoader.loadFiles(this.main());
    this.soulBossSystemLogger().logInfo("files loaded!");
    this.soulBossSystemLogger().logInfo("SoulBossSystem plugin loaded!");

  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Disable the soulbosssystem plugin
   * @param main Requires the main class
   */
  public void disable(@NotNull JavaPlugin main){
    SoulBossSystemConfigurationLoader.save(main);
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the logger for the soulbosssystem plugin
   * @return Gets a instance of the {@link PluginLogger}
   */
  @NotNull
  public PluginLogger soulBossSystemLogger(){
    return PluginInstances.SOUL_BOSS_SYSTEM_LOGGER;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets this class
   * @return this class
   */
  @NotNull
  public static SoulBossSystemMain soulBossSystemMain(){
    return soulBossSystemMain;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets a instance of the main class
   * @return {@link PluginMain#main()}
   */
  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }
}
