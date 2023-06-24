package de.elia.soulboss;

import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.soulboss.utils.UtilsLoader;
import de.elia.systemclasses.PluginInstances;
import de.elia.systemclasses.keys.PluginKeys;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This is the main class of the plugin soulboss
 */
public class SoulBoss {

  private static SoulBoss soulboss;

  /**
   * @description This methode load this plugin.
   * @param main Requires a instance of tha main class of this system.
   */
  public void enable(@NotNull JavaPlugin main) {
    this.soulBossLogger().logInfo("Load SoulBoss...");
    soulboss = this;
    this.soulBossLogger().logInfo("Load utils...");
    UtilsLoader.loadUtils(main);
    this.soulBossLogger().logInfo("Utils loaded!");
    this.soulBossLogger().logInfo("SoulBoss loaded!");
  }

  /**
   * @description This methode unload this plugin.
   */
  public void disable() {
    Bukkit.getServer().getWorld("world_bossfight").getEntities().forEach(entity -> {
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.removeRecipe(PluginKeys.RECIPE_KEY.key());
  }

  /**
   * @description Gets a instance of this class
   * @return this class
   */
  @NotNull
  public static SoulBoss soulBoss() {
    return soulboss;
  }

  /**
   * @description Gets the main class of this system.
   * @return the main class of this system.
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
  public PluginLogger soulBossLogger() {
    return PluginInstances.SOUL_BOSS_LOGGER;
  }
}
