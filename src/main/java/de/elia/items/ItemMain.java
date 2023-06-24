package de.elia.items;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.systemclasses.PluginInstances;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This is the main class of the plugin item.
 *
 */
public class ItemMain {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static ItemMain itemMain;

  /**
   * @description This enable this plugin.
   * @param main Requires the main class of this system.
   */
  public void enable(@NotNull JavaPlugin main) {
    this.itemLogger().logInfo("Load Item Plugin...");
    itemMain = this;
    this.itemLogger().logInfo("Load items...");
    Item.registerAll(main);
    this.itemLogger().logInfo("Items loaded!");
    this.itemLogger().logInfo("Item plugin loaded!");
  }

  /**
   * @description Unload this plugin.
   */
  public void disable() {
    this.itemLogger().logInfo("Item unloaded!");
  }

  /**
   * @description Gets a instance of this class.
   * @return this class
   */
  @NotNull
  public static ItemMain itemMain() {
    return itemMain;
  }

  /**
   * @description Gets a instance of the main class of this system.
   * @return the instance of the main class.
   */
  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }

  /**
   * @description Gets the logger for this plugin.
   * @return the logger for this plugin.
   */
  @NotNull
  public PluginLogger itemLogger() {
    return PluginInstances.ITEM_LOGGER;
  }
}
