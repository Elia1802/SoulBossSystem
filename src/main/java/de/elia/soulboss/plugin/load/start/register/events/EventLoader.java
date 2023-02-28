package de.elia.soulboss.plugin.load.start.register.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register all Events.
 */
public class EventLoader {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register the Event
   * @param pluginManager Requires the command name
   * @param listener Requires the Command class
   * @param plugin Requires the Instance of the Main class
   */
  public void loadEvents(@NotNull PluginManager pluginManager, @NotNull Listener listener, @NotNull Plugin plugin){
    pluginManager.registerEvents(listener, plugin);
  }

}
