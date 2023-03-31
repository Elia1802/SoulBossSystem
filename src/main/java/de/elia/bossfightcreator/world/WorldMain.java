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
    this.arenaFolder();
  }

  @Override
  public void logInfo(@NotNull String message){
    this.logger().info(message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create/Gets the Path for the Schematics
   * @return {@link File}
   */
  public File arenaFolder(){
    return new File(plugin.getDataFolder(), "arenas/");
  }
}
