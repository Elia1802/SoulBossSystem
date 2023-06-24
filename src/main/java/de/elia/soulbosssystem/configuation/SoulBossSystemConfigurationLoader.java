package de.elia.soulbosssystem.configuation;

import de.elia.api.configuration.SoulConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @description This class load all config files.
 */
public class SoulBossSystemConfigurationLoader {
  private static SoulConfiguration achievementStorage;
  private static SoulConfiguration ideasStorage;

  private static void setConfiguration(Plugin plugin) {
    achievementStorage = new SoulConfiguration(plugin, "achievements/", "Achievements.yml");
    ideasStorage = new SoulConfiguration(plugin, "ideas/", "Ideas.yml");
  }

  public static void loadFiles(Plugin plugin) {
    SoulBossSystemConfigurationLoader.setConfiguration(plugin);
    achievementStorage.copyDefaults(true);
    achievementStorage.save();
    ideasStorage.copyDefaults(true);
    ideasStorage.save();
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
  public SoulConfiguration achievementStorage() {
    return achievementStorage;
  }

  @NotNull
  public SoulConfiguration ideasStorage() {
    return ideasStorage;
  }
}
