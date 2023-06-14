package de.elia.soulboss;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.api.logging.PluginLogger;
import de.elia.soulboss.utils.UtilsLoader;
import de.elia.systemclasses.keys.PluginKeys;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Main class of the Plugin SoulBoss
 */
public class SoulBoss {

  private final MiniMessage miniMessage = MiniMessage.miniMessage();//Gets the MiniMessage API.
  private static SoulBoss soulboss;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Plugin SoulBoss
   * @param main Requires the main Main class of this Plugin
   */
  public void enable(@NotNull JavaPlugin main){
    this.soulBossLogger().logInfo("Load SoulBoss...");
    soulboss = this;
    this.soulBossLogger().logInfo("Load utils...");
    UtilsLoader.loadUtils(main);
    this.soulBossLogger().logInfo("Utils loaded!");
    this.soulBossLogger().logInfo("SoulBoss loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disabled this Plugin
   * @param main Requires the main Main class of this Plugin
   */
  public void disable(@NotNull JavaPlugin main) {
    Bukkit.getServer().getWorld("world_bossfight").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.removeRecipe(PluginKeys.RECIPE_KEY.key());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the MiniMessage API
   * @return {@link MiniMessage}
   */
  @NotNull
  public MiniMessage miniMessage() {
    return this.miniMessage;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description a Instance of this Class
   * @return {@link SoulBoss}
   */
  @NotNull
  public static SoulBoss soulBoss() {
    return soulboss;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description A instance of the main Main class of this Plugin
   * @return {@link PluginMain}
   */
  @NotNull
  public static PluginMain main(){
    return PluginMain.main();
  }

  @NotNull
  public PluginLogger soulBossLogger(){
    return PluginInstances.SOUL_BOSS_LOGGER;
  }
}
