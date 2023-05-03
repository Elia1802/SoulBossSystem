package de.elia.items;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.systemclasses.logging.PluginLogger;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ItemMain {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static ItemMain itemMain;

  public void enable(JavaPlugin plugin) throws SoulBossSystemNullException {
    this.itemLogger().logInfo("Load Item...");
    itemMain = this;
    this.itemLogger().logInfo("Register Items...");
    Item.registerAll(plugin);
    this.itemLogger().logInfo("Items registered!");
    this.itemLogger().logInfo("Item loaded!");
  }

  public void disable(){
    this.itemLogger().logInfo("Unnload Item...");
    this.itemLogger().logInfo("Item unloaded!");
  }

  @NotNull
  public static ItemMain itemMain(){
    return itemMain;
  }

  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger itemLogger(){
    return PluginInstances.ITEM_LOGGER;
  }

}
