package de.elia.bossfightcreator.load.start;

import de.elia.Main;
import de.elia.bossfightcreator.Instances;
import de.elia.bossfightcreator.fight.arena.map.ArenaMaps;
import de.elia.bossfightcreator.fight.events.LeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Start Methode to load the Plugin
 */
public class StartPlugin {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Start Methode to load the Plugin
   * @param plugin Requires the main main class of this Plugin
   */
  public static void start(@NotNull Plugin plugin){
    Instances.Plugin.instance = plugin;
    Main main = (Main) Instances.Plugin.instance;
    var world_status = Instances.Files.WORLD_STATUS;
    world_status.copyDefaults(true);
    world_status.save();
    main.getDefaultWorldGenerator("bossfight", "world_bossfight");
    Bukkit.getPluginManager().registerEvents(new LeaveEvent(), plugin);
    ArenaMaps.load();
  }
}
