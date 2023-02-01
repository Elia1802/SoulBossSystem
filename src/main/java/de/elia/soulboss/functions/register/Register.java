package de.elia.soulboss.functions.register;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.block.BreakBlock;
import de.elia.soulboss.fight.BossFightManager;
import de.elia.soulboss.messages.message.CustomMessages;
import de.elia.soulboss.spawn.SpawnEgg;
import de.elia.soulmain.thisplugin.configuration.SoulConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register anything.
 */
public class Register {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Register class to register all Commands.
   */
  public static class Commands {
    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Register the Command
     * @param command Requires the command name
     * @param commandClass Requires the Command class
     */
    public void load(@NotNull String command, @NotNull CommandExecutor commandClass) {
      Bukkit.getPluginCommand(command).setExecutor(commandClass);
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Register class to register all Events.
   */
  public static class Events {
    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Register the Event
     * @param manager Requires the command name
     * @param listener Requires the Command class
     * @param plugin Requires the Instance of the Main class
     */
    public void load(@NotNull PluginManager manager, Listener listener, Plugin plugin){
      manager.registerEvents(listener, plugin);
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Register class to register all Utils or other things.
   */
  public static class Utils {

    private final CustomMessages log = new CustomMessages(); //Gets the Message class of SoulMain

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Register the Utils and other things
     */
    public void load(Plugin plugin){
      {
        String key = "680035753";
        log.infoLog("load BreakTask with the NamespacedKey: " + key + "...");
        new BreakBlock(plugin).breakTask(new NamespacedKey(plugin, key));
        log.infoLog("BreakTasks loaded!");
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Disable Class to stop and save every thinks.
   */
  public static class Disable {

    private final CustomMessages log = new CustomMessages(); //Gets the Message class of SoulMain
    private final BossFightManager bossFightManager = new BossFightManager();

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Disable and save all thinks
     */
    public void disable(Plugin plugin){
      log.infoLog("Stop SoulBoss...");
        log.infoLog("Save all Configurations...");
          new Configuration().save();
        log.infoLog("Configuration saved");
        log.infoLog("Remove Bosses...");
          Bukkit.getServer().getWorld("world").getEntities().forEach((entity) ->{
            if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
              entity.remove();
            }
          });
          Bukkit.getServer().getWorld("world_nether").getEntities().forEach((entity) ->{
            if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
              entity.remove();
            }
          });
          Bukkit.getServer().getWorld("world_the_end").getEntities().forEach((entity) ->{
            if (entity.getPersistentDataContainer().has(new NamespacedKey(plugin, "680035753"))) {
              entity.remove();
            }
          });
        log.infoLog("Bosses removed!");
      log.infoLog("SoulBoss stopped!");
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Configuration Class to save things in a file.
   */
  public static class Configuration {
    private final SoulConfiguration achievementConfiguration = new SoulConfiguration(SoulBoss.soulBoss(), "boss/achievement/" , "BossAchievement.yml");
    private final SoulConfiguration playerRegisterConfiguration = new SoulConfiguration(SoulBoss.soulBoss(), "boss/register/", "PlayerRegister.yml");
    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Load all Configuration files
     */
    public void load(){
      achievementConfiguration.copyDefaults(true);
      achievementConfiguration.save();
      playerRegisterConfiguration.copyDefaults(true);
      playerRegisterConfiguration.save();
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Save all Configuration files
     */
    public void save(){
      achievementConfiguration.save();
      playerRegisterConfiguration.clear();
      playerRegisterConfiguration.save();
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets the Configuration for the achievements
     */
    @NotNull
    public SoulConfiguration achievementConfiguration() {
      return achievementConfiguration;
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets the Configuration for the Player Register for BossFights.
     */
    public SoulConfiguration playerRegisterConfiguration() {
      return playerRegisterConfiguration;
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Recipes Class to load all Recipes.
   */
  public static class Recipes{
    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description This is the load methode to load all Recipes.
     */
    public void load(Plugin plugin){
      this.registerEggRecipe(plugin);
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description This Methode register the EggRecipe
     */
    public void registerEggRecipe(Plugin plugin){
      SpawnEgg spawnEgg = new SpawnEgg(plugin);
      try {
        spawnEgg.eggRecipe();
        plugin.getServer().addRecipe(spawnEgg.bossSpawnEggRecipe());
      }catch (Exception exception) {
        exception.printStackTrace();
      }
    }

  }
}
