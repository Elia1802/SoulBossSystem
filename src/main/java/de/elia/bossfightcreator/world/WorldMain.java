package de.elia.bossfightcreator.world;

import de.elia.PluginLogger;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description All thinks for the world generation
 */
public class WorldMain extends PluginLogger {

  private final Plugin plugin;
  private File file;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a WorldMain
   * @param plugin
   */
  public WorldMain(Plugin plugin){
    super("SoulBossSystem -> BossFightCreator -> World");
    this.plugin = plugin;
    this.loadFolder();
  }

  @Override
  public void logInfo(@NotNull String message){
    this.logger().info(message);
  }

  private void loadFolder(){
    file = new File(plugin.getDataFolder() + "/arenas/");
    if (!file.exists()) {
      file.mkdir();
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create/Gets the Path for the Schematics
   * @return {@link File}
   */
  public File arenaFolder(){
    return file;
  }
}
