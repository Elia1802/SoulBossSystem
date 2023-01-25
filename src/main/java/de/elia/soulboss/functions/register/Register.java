package de.elia.soulboss.functions.register;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.block.BreakBlock;
import de.elia.soulboss.spawn.SpawnEgg;
import de.elia.soulmain.allplugins.messages.builder.MessageBuilder;
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
    public static void load(@NotNull String command, @NotNull CommandExecutor commandClass) {
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
    public static void load(@NotNull PluginManager manager, Listener listener, Plugin plugin){
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

    private static final MessageBuilder log = new MessageBuilder(); //Gets the Message class of SoulMain

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Register the Utils and other things
     */
    public static void load(Plugin plugin){
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

    private static final MessageBuilder log = new MessageBuilder(); //Gets the Message class of SoulMain

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Disable and save all thinks
     */
    public static void disable(){
      log.infoLog("Stop SoulBoss...");
        log.infoLog("Save all Configurations...");
          Register.Configuration.save();
        log.infoLog("Configuration saved");
      log.infoLog("SoulBoss stopped!");
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Configuration Class to save thinks in a file.
   */
  public static class Configuration {
    private static final SoulConfiguration achievementConfiguration = new SoulConfiguration(SoulBoss.soulBoss(), "achievement/" , "BossAchievement.yml");
    private static final SoulConfiguration discordWebhookConfiguration = new SoulConfiguration(SoulBoss.soulBoss(), "discord/", "WebhookURL.yml");

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Load all Configuration files
     */
    public static void load(){
      achievementConfiguration.copyDefaults(true);
      achievementConfiguration.save();
      discordWebhookConfiguration.copyDefaults(true);
      discordWebhookConfiguration.addDefault("WebhookURL" , "https://discord.com/api/webhooks/YOUR_WEBHOOK");
      discordWebhookConfiguration.save();
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Save all Configuration files
     */
    public static void save(){
      achievementConfiguration.save();
      discordWebhookConfiguration.save();
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets the Configuration for the achievements
     */
    @NotNull
    public static SoulConfiguration achievementConfiguration() {
      return achievementConfiguration;
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Gets the Configuration for the Discord Webhook.
     */
    public static SoulConfiguration discordWebhookConfiguration() {
      return discordWebhookConfiguration;
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
    public static void load(Plugin plugin){
      new SpawnEgg(plugin).registerEggRecipe();
    }
  }
}
