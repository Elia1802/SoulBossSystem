package de.elia.bossfightcreator;

import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;

import de.elia.PluginMain;
import de.elia.systemclasses.PluginInstances;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Elia
 * @description This is the main class for the bossfightcreator plugin.
 * @implememnts {@link SoulPlugin}
 */
public class BossFightCreatorMain implements SoulPlugin {

  private static BossFightCreatorMain bossFightCreatorMain;
  private static final Map<Player, Integer> playerStatusMap = new HashMap<>();

  /**
   * @description This methode load this plugin.
   * @param javaPlugin Requires the instance of the system main class.
   */
  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.bossFightCreatorLogger().logInfo("Load BossFightCreator plugin...");
    bossFightCreatorMain = this;
    this.bossFightCreatorLogger().logInfo("BossFightCreator plugin loaded!");
  }

  /**
   * @description This methode load this plugin.
   * @param javaPlugin Requires the instance of the system main class.
   */
  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
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
