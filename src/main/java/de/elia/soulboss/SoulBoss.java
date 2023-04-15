package de.elia.soulboss;

import de.elia.PluginInstances;
import de.elia.PluginMain;
import de.elia.PluginLogger;
import de.elia.soulboss.plugin.load.Load;
import de.elia.soulboss.plugin.load.stop.disable.Disable;
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

  private final Load loader = new Load();//the loader of this Plugin
  private final MiniMessage miniMessage = MiniMessage.miniMessage();//Gets the MiniMessage API.
  private static SoulBoss soulboss;

  public SoulBoss(){
    //...
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the Plugin SoulBoss
   * @param plugin Requires the main Main class of this Plugin
   */
  public void enable(@NotNull JavaPlugin plugin){
    soulboss = this;
    loader.loadPlugin(plugin, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded this Plugin
   * @param plugin Requires the main Main class of this Plugin
   */
  public void reload(@NotNull JavaPlugin plugin) {
    soulboss = this;
    loader.reloadPlugin(plugin, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded the Configurations of this Plugin
   * @param plugin Requires the main Main class of this Plugin
   */
  public void reloadConfiguration(@NotNull JavaPlugin plugin) {
    soulboss = this;
    loader.reloadConfiguration(plugin);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Disabled this Plugin
   * @param plugin Requires the main Main class of this Plugin
   */
  public void disable(@NotNull JavaPlugin plugin){
    new Disable().disable(plugin);
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
