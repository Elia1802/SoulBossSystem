package de.elia.bossfightcreator;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.systemclasses.PluginInstances;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Elia
 * @description This is the main class for the bossfightcreator plugin.
 */
public class BossFightCreatorMain {

  private static BossFightCreatorMain bossFightCreatorMain;
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();

  /**
   * @description This methode load this plugin.
   */
  public void enable() {
    this.bossFightCreatorLogger().logInfo("Load BossFightCreator plugin...");
    bossFightCreatorMain = this;
    this.bossFightCreatorLogger().logInfo("BossFightCreator plugin loaded!");
  }

  /**
   * @description This methode unload this plugin.
   */
  public void disable() {
    this.bossFightCreatorLogger().logInfo("Disable BossFightCreator...");
    this.bossFightCreatorLogger().logInfo("BossFightCreator disabled!");
  }

  /**
   * @description Gets the status map.
   * @return a {@link Map}
   */
  @NotNull
  public static Map<Player, Integer> playerStatusMap() {
    return playerStatusMap;
  }

  /**
   * @description Gets this class.
   * @return a instance of this class.
   */
  public static BossFightCreatorMain bossFightCreator() {
    return bossFightCreatorMain;
  }

  /**
   * @description Gets the instance of the main class of this system.
   * @return the instance of the main class.
   */
  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }

  /**
   * @description Gets the logger for this logger.
   * @return the logger of this plugin.
   */
  @NotNull
  public PluginLogger bossFightCreatorLogger() {
    return PluginInstances.BOSS_FIGHT_CREATOR_LOGGER;
  }
}

