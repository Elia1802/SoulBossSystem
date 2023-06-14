package de.elia.items;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description Load the Item plugin
 */
public class ItemMain {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static ItemMain itemMain;

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description enable this plugin
   * @param main Requires a Main class
   */
  public void enable(@NotNull JavaPlugin main){
    this.itemLogger().logInfo("Load Item Plugin...");
    itemMain = this;
    this.itemLogger().logInfo("Load items...");
    Item.registerAll(main);
    this.itemLogger().logInfo("Items loaded!");
    this.itemLogger().logInfo("Item plugin loaded!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Disable this plugin
   */
  public void disable(){
    this.itemLogger().logInfo("Item unloaded!");
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the main instance of this main
   * @return {@link ItemMain}
   */
  @NotNull
  public static ItemMain itemMain(){
    return itemMain;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the main instance of the {@link PluginMain}
   * @return {@link PluginMain#main()}
   */
  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Gets the logger for this Plugin
   * @return {@link PluginLogger}
   */
  @NotNull
  public PluginLogger itemLogger(){
    return PluginInstances.ITEM_LOGGER;
  }
}
