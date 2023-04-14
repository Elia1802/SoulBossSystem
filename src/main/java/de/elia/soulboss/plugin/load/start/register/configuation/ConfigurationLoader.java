package de.elia.soulboss.plugin.load.start.register.configuation;

import de.elia.api.configuration.SoulConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Configuration Class to save things in a file.
 */
public class ConfigurationLoader {

  private static SoulConfiguration achievementStorage;
  private static SoulConfiguration ideasStorage;

  @Deprecated private static SoulConfiguration playerRegisterStorage;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set all Configuration files
   */
  private static void setConfiguration(Plugin plugin){
    achievementStorage = new SoulConfiguration(plugin, "achievements/", "Achievements.yml");
    ideasStorage = new SoulConfiguration(plugin, "ideas/", "Ideas.yml");
    //playerRegisterStorage = new SoulConfiguration(plugin, "register/", "PlayerRegisterFight.yml");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all Configuration files
   */
  public void loadFiles(Plugin plugin){
    setConfiguration(plugin);
    if (achievementStorage == null) setConfiguration(plugin);
    achievementStorage.copyDefaults(true);
    achievementStorage.save();
    if (ideasStorage == null) setConfiguration(plugin);
    ideasStorage.copyDefaults(true);
    ideasStorage.save();
    /*
    if (playerRegisterStorage == null) setConfiguration(plugin);
    playerRegisterStorage.copyDefaults(true);
    playerRegisterStorage.save();
     */
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Save all Configuration files
   */
  public void save(Plugin plugin){
    if (achievementStorage == null) setConfiguration(plugin);
    achievementStorage.save();
    if (ideasStorage == null) setConfiguration(plugin);
    ideasStorage.save();
    /*
    if (ideasStorage == null) setConfiguration(plugin);
    playerRegisterStorage.save();
     */
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Configuration for the achievements
   */
  @NotNull
  public SoulConfiguration achievementStorage() {
    return achievementStorage;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Configuration for ideas
   */
  @NotNull
  public SoulConfiguration ideasStorage() {
    return ideasStorage;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Configuration for the Player Register for BossFights.
   */
  @NotNull
  @Deprecated
  public SoulConfiguration playerRegisterStorage() {
    return playerRegisterStorage;
  }
}
