package de.elia.soulbosssystem;

import de.elia.PluginMain;
import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.PluginInstances;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class SoulBossSystemMain
  implements SoulPlugin {
  private static SoulBossSystemMain soulBossSystemMain;

  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    soulBossSystemMain = this;
    this.soulBossSystemLogger().logInfo("Load SoulBossSystem plugin...");
    this.soulBossSystemLogger().logInfo("Load files...");
    SoulBossSystemConfigurationLoader.loadFiles((Plugin)javaPlugin);
    this.soulBossSystemLogger().logInfo("files loaded!");
    this.soulBossSystemLogger().logInfo("SoulBossSystem plugin loaded!");
  }

  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    SoulBossSystemConfigurationLoader.save((Plugin)javaPlugin);
  }

  @NotNull
  public PluginLogger soulBossSystemLogger() {
    return PluginInstances.SOUL_BOSS_SYSTEM_LOGGER;
  }

  @NotNull
  public static SoulBossSystemMain soulBossSystemMain() {
    return soulBossSystemMain;
  }

  @NotNull
  public PluginMain main() {
    return PluginMain.main();
  }
}
