package de.elia.achivements;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description The main class of the achievement class
 */
public class AchievementMain {

  private static AchievementMain achievementMain;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description The load methode to load achievement plugin
   * @param main Requires the Main class
   */
  public void enable(@NotNull JavaPlugin main){
    this.achievementPluginLogger().logInfo("Load Achievement plugin...");
    achievementMain = this;
    this.achievementPluginLogger().logInfo("Achievement plugin loaded!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description The disable methode to load achievement plugin
   */
  public void disable(){
    this.achievementPluginLogger().logInfo("Achievement disabled!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets this main class
   * @return this class
   */
  @NotNull
  public static AchievementMain achievementMain(){
    return achievementMain;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the logger for this plugin
   * @return a instance of the {@link PluginLogger}
   */
  @NotNull
  public PluginLogger achievementPluginLogger(){
    return PluginInstances.ACHIEVEMENT_LOGGER;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the main class
   * @return
   */
  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }
}
