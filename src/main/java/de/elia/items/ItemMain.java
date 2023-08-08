package de.elia.items;

import de.elia.PluginMain;
import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;
import de.elia.items.recipes.loader.RecipeLoader;
import de.elia.systemclasses.PluginInstances;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ItemMain implements SoulPlugin {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static ItemMain itemMain;

  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.itemLogger().logInfo("Load Item Plugin...");
    itemMain = this;
    this.itemLogger().logInfo("Load items...");
    Item.registerAll(javaPlugin);
    new RecipeLoader.SpawnEggLoader().loadRecipe(javaPlugin);
    this.itemLogger().logInfo("Items loaded!");
    this.itemLogger().logInfo("Item plugin loaded!");
  }

  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.itemLogger().logInfo("Item unloaded!");
  }

  @NotNull
  public static ItemMain itemMain() {
    return itemMain;
  }

  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger itemLogger() {
    return PluginInstances.ITEM_LOGGER;
  }
}
