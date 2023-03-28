package de.elia.bossfightcreator.world;

import de.elia.PluginLogger;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description All thinks for the world generation
 */
public class WorldMain {


  public static final PluginLogger LOGGER = new PluginLogger("SoulSMP-WorldGenerator");
  public static final PluginLogger LOGGER_PROGRESS = new PluginLogger("SoulSMP-World-creation-progress");
  private final Plugin plugin;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a WorldMain
   * @param plugin
   */
  public WorldMain(Plugin plugin) {
    this.plugin = plugin;
    this.arenaFolder();
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
