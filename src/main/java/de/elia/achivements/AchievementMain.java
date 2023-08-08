package de.elia.achivements;

import de.elia.PluginMain;
import de.elia.systemclasses.PluginInstances;

import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This class load the achievement Plugin.
 * @implements {@link SoulPlugin}
 */
public class AchievementMain implements SoulPlugin {

  private static AchievementMain achievementMain;

  /**
   * @description Load this plugin
   * @param javaPlugin Requires the main class of this system.
   * @throws SoulPluginLoadException If a error ocurred by load create a new {@link SoulPluginLoadException}
   */
  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.achievementPluginLogger().logInfo("Load Achievement plugin...");
    achievementMain = this;
    this.achievementPluginLogger().logInfo("Achievement plugin loaded!");
  }

  /**
   * @description Unload this plugin
   * @param javaPlugin Requires the main class of this system.
   * @throws SoulPluginLoadException If a error ocurred by unload create a new {@link SoulPluginLoadException}
   */
  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.achievementPluginLogger().logInfo("Achievement disabled!");
  }

  /**
   * @description Gets this main class.
   * @return Return a instance of this plugin.
   */
  @NotNull
  public static AchievementMain achievementMain() {
    return achievementMain;
  }

  /**
   * @description Gets the logger of this class.
   * @return Return the instance of the logger for this plugin.
   */
  @NotNull
  public PluginLogger achievementPluginLogger() {
    return PluginInstances.ACHIEVEMENT_LOGGER;
  }

  /**
   * @description Gets the instance of the main class of this system.
   * @return Return the instance of the system main class.
   */
  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }
}
