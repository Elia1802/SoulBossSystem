package de.elia.bossfightcreator.world;

import de.elia.api.logging.PluginLogger;
import java.io.File;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description In this class i set a logger for the world creation and load the path for the arena files.
 */
public class WorldMain {

  private final Plugin plugin;
  private File file;
  private final PluginLogger logger = new PluginLogger("SoulBossSystem -> World");

  public WorldMain(Plugin plugin) {
    this.plugin = plugin;
    this.loadFolder();
  }

  /**
   * @description log a message in the console.
   * @param message Requires the message.
   */
  public void logInfo(@NotNull String message) {
    this.logger.logInfo(message);
  }

  /**
   * @description Load the path for the arena
   */
  private void loadFolder() {
    this.file = new File(this.plugin.getDataFolder() + "/arenas/");
    if (!this.file.exists()) {
      this.file.mkdir();
    }
  }

  /**
   * @description Gets the arena path
   * @return the path for the arena files
   */
  public File arenaFolder() {
    return this.file;
  }
}

