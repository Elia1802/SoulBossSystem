package de.elia.achivements;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.systemclasses.PluginInstances;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description A Main class for the achievement plugin in this system.
 */
public class AchievementMain {

  private static AchievementMain achievementMain;

  /**
   * @description Enable this plugin.
   */
  public void enable() {
    this.achievementPluginLogger().logInfo("Load Achievement plugin...");
    achievementMain = this;
    this.achievementPluginLogger().logInfo("Achievement plugin loaded!");
  }

  /**
   * @description Disable this plugin.
   */
  public void disable() {
    this.achievementPluginLogger().logInfo("Achievement disabled!");
  }

  /**
   * @description Gets the instance of this plugin.
   * @return this class
   */
  @NotNull
  public static AchievementMain achievementMain() {
    return achievementMain;
  }

  /**
   * @description  Gets the Logger of for this Plugin.
   * @return {@link PluginInstances#ACHIEVEMENT_LOGGER}
   */
  @NotNull
  public PluginLogger achievementPluginLogger() {
    return PluginInstances.ACHIEVEMENT_LOGGER;
  }

  /**
   * @description Gets the instance of the Main class of this system.
   * @return {@link PluginMain#main()}
   */
  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }
}

