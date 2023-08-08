package de.elia.soulbosssystem.configuation;

import de.elia.api.configuration.SoulConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SoulBossSystemConfigurationLoader {
  private static SoulConfiguration achievementStorage;
  private static SoulConfiguration ideasStorage;
  private static SoulConfiguration ifArenasLoaded;

  private static void setConfiguration(Plugin plugin) {
    achievementStorage = new SoulConfiguration(plugin, "achievements/", "Achievements.yml");
    ideasStorage = new SoulConfiguration(plugin, "ideas/", "Ideas.yml");
    ifArenasLoaded = new SoulConfiguration(plugin, "ifArenasloaded.yml");
  }

  public static void loadFiles(Plugin plugin) {
    SoulBossSystemConfigurationLoader.setConfiguration(plugin);
    achievementStorage.copyDefaults(true);
    achievementStorage.save();
    ideasStorage.copyDefaults(true);
    ideasStorage.save();
    ifArenasLoaded.copyDefaults(true);
    ifArenasLoaded.addDefault("loaded", (Object)false);
    ifArenasLoaded.save();
  }

  public static void save(Plugin plugin) {
    achievementStorage.save();
    if (ideasStorage == null) {
      SoulBossSystemConfigurationLoader.setConfiguration(plugin);
    }
    ideasStorage.save();
  }

  public void reload(Plugin plugin) {
    SoulBossSystemConfigurationLoader.save(plugin);
    SoulBossSystemConfigurationLoader.loadFiles(plugin);
  }

  @NotNull
  public static SoulConfiguration achievementStorage() {
    return achievementStorage;
  }

  @NotNull
  public static SoulConfiguration ideasStorage() {
    return ideasStorage;
  }

  @NotNull
  public static SoulConfiguration ifArenasLoaded() {
    return ifArenasLoaded;
  }
}
