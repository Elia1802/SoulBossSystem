package de.elia.items;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.systemclasses.logging.PluginLogger;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Load the Item plugin
 */
public class ItemMain {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static ItemMain itemMain;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description enable this plugin
   * @param plugin Requires a Main class
   * @throws SoulBossSystemNullException Generate a exception if a something null in {@link Item#registerAll(Plugin)}
   */
  public void enable(@NotNull JavaPlugin plugin) throws SoulBossSystemNullException {
    this.itemLogger().logInfo("Load Item...");
    itemMain = this;
    this.itemLogger().logInfo("Register Items...");
    Item.registerAll(plugin);
    this.itemLogger().logInfo("Items registered!");
    this.itemLogger().logInfo("Item loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disable this plugin
   */
  public void disable(){
    this.itemLogger().logInfo("Unnload Item...");
    this.itemLogger().logInfo("Item unloaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the main instance of this main
   * @return {@link ItemMain}
   */
  @NotNull
  public static ItemMain itemMain(){
    return itemMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the main instance of the {@link PluginMain}
   * @return {@link PluginMain#main()}
   */
  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the logger for this Plugin
   * @return {@link PluginLogger}
   */
  @NotNull
  public PluginLogger itemLogger(){
    return PluginInstances.ITEM_LOGGER;
  }

}
