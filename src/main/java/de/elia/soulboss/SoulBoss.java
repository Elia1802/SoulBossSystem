package de.elia.soulboss;

import de.elia.PluginMain;
import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;
import de.elia.soulboss.utils.UtilsLoader;
import de.elia.systemclasses.PluginInstances;
import de.elia.systemclasses.keys.PluginKeys;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SoulBoss implements SoulPlugin {

  private static SoulBoss soulboss;

  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    this.soulBossLogger().logInfo("Load SoulBoss...");
    soulboss = this;
    this.soulBossLogger().logInfo("Load utils...");
    UtilsLoader.loadUtils(javaPlugin);
    this.soulBossLogger().logInfo("Utils loaded!");
    this.soulBossLogger().logInfo("SoulBoss loaded!");
  }

  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    Bukkit.getServer().getWorld("world_bossfight").getEntities().forEach(entity -> {
      if (entity.getType() == EntityType.ZOMBIE) {
        if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
          entity.remove();
        }
      }
    });
    Bukkit.removeRecipe(PluginKeys.RECIPE_KEY.key());
  }

  @NotNull
  public static SoulBoss soulBoss() {
    return soulboss;
  }

  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger soulBossLogger() {
    return PluginInstances.SOUL_BOSS_LOGGER;
  }
}
