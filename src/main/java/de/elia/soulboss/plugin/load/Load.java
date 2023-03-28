package de.elia.soulboss.plugin.load;

import de.elia.soulboss.commands.help.HelpCommand;
import de.elia.soulboss.commands.idea.IdeaCommand;
import de.elia.soulboss.commands.items.ItemGiveCommand;
import de.elia.soulboss.commands.plugin.PluginCommand;
import de.elia.soulboss.commands.world.WorldCommand;
import de.elia.soulboss.entity.mobs.boss.mob.BossDeathEvent;
import de.elia.soulboss.events.achievements.bossfight.AchievementBossFightEvent;
import de.elia.soulboss.events.achievements.bossfightzombieend.AchievementBossFightZombieEndEvent;
import de.elia.soulboss.events.attacks.fire.AttackFireEvent;
import de.elia.soulboss.events.attacks.strikelightning.AttackStrikeLightningEvent;
import de.elia.soulboss.events.attacks.teleport.AttackTeleportEvent;
import de.elia.soulboss.events.connections.connection.ConnectionEvent;
import de.elia.soulboss.events.connections.disconnection.DisconnectEvent;
import de.elia.soulboss.events.spawn.ZombieSpawnEvent;
import de.elia.CustomMessages;
import de.elia.soulboss.plugin.load.start.register.commands.CommandLoader;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import de.elia.soulboss.plugin.load.start.register.events.EventLoader;
import de.elia.soulboss.plugin.load.start.register.recipes.RecipeLoader;
import de.elia.soulboss.plugin.load.start.register.utils.UtilsLoader;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Load Class for the Main Class.
 */
public class Load {

  private final CustomMessages log = new CustomMessages();
  private final CommandLoader command = new CommandLoader();
  private final ConfigurationLoader configuration = new ConfigurationLoader();
  private final EventLoader event = new EventLoader();
  private final RecipeLoader.SpawnEggLoader recipeSpawnEgg = new RecipeLoader.SpawnEggLoader();
  private final UtilsLoader utils = new UtilsLoader();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all things in this Plugin
   * @param plugin Requires the Main class
   * @param pluginManager Requires a {@link PluginManager}
   */
  public void loadPlugin(Plugin plugin, PluginManager pluginManager){
    log.infoLog("Load SoulBoss...");
      log.infoLog("Load Commands...");
        command.loadCommand("bosshelp", new HelpCommand());
        command.loadCommand("mobidea", new IdeaCommand());
        command.loadCommand("bossgive", new ItemGiveCommand());
        //command.loadCommand("bossfight", new SpawnMobCommand());
        command.loadCommand("soulboss", new PluginCommand());
        command.loadCommand("tpworld", new WorldCommand());
      log.infoLog("Commands loaded!");
      log.infoLog("Load Events...");
        event.loadEvents(pluginManager, new AchievementBossFightEvent(), plugin);
        event.loadEvents(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        event.loadEvents(pluginManager, new AttackFireEvent(), plugin);
        event.loadEvents(pluginManager, new AttackStrikeLightningEvent(), plugin);
        event.loadEvents(pluginManager, new AttackTeleportEvent(), plugin);
        event.loadEvents(pluginManager, new ConnectionEvent(), plugin);
        event.loadEvents(pluginManager, new DisconnectEvent(), plugin);
        event.loadEvents(pluginManager, new BossDeathEvent(), plugin);
        event.loadEvents(pluginManager, new ZombieSpawnEvent(), plugin);
      log.infoLog("Events loaded!");
      log.infoLog("Load Configurations...");
        configuration.loadFiles(plugin);
      log.infoLog("Configurations loaded!");
      log.infoLog("load Utils...");
        utils.loadUtils(plugin);
      log.infoLog("Utils loaded!");
      log.infoLog("load Recipes...");
        recipeSpawnEgg.loadRecipe(plugin);
      log.infoLog("Recipes loaded");
    log.infoLog("All things loaded! Good Luck with this Plugin!");
    log.infoLog("SoulBoss loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded all things in this Plugin
   * @param plugin Requires the Main class
   * @param pluginManager Requires a {@link PluginManager}
   */
  public void reloadPlugin(Plugin plugin, PluginManager pluginManager){
    log.infoLog("Reload SoulBoss...");
    log.infoLog("Remove Recipes...");
      Bukkit.removeRecipe(new NamespacedKey(plugin, "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));
    log.infoLog("Recipes Removed!");
    log.infoLog("Save Configurations...");
      configuration.save(plugin);
    log.infoLog("Configurations saved!");
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
    //configuration.playerRegisterStorage().clear();
    log.infoLog("Bosses removed!");
      log.infoLog("Load Commands...");
        command.loadCommand("bosshelp", new HelpCommand());
        command.loadCommand("mobidea", new IdeaCommand());
        command.loadCommand("bossgive", new ItemGiveCommand());
        //command.loadCommand("bossfight", new SpawnMobCommand());
        command.loadCommand("soulboss", new PluginCommand());
        command.loadCommand("tpworld", new WorldCommand());
      log.infoLog("Commands loaded!");
      log.infoLog("Load Events...");
        event.loadEvents(pluginManager, new AchievementBossFightEvent(), plugin);
        event.loadEvents(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        event.loadEvents(pluginManager, new AttackFireEvent(), plugin);
        event.loadEvents(pluginManager, new AttackStrikeLightningEvent(), plugin);
        event.loadEvents(pluginManager, new AttackTeleportEvent(), plugin);
        event.loadEvents(pluginManager, new ConnectionEvent(), plugin);
        event.loadEvents(pluginManager, new DisconnectEvent(), plugin);
        event.loadEvents(pluginManager, new BossDeathEvent(), plugin);
        event.loadEvents(pluginManager, new ZombieSpawnEvent(), plugin);
      log.infoLog("Events loaded!");
      log.infoLog("Load Configurations...");
        configuration.loadFiles(plugin);
      log.infoLog("Configurations loaded!");
      log.infoLog("load Utils...");
        utils.loadUtils(plugin);
      log.infoLog("Utils loaded!");
      log.infoLog("load Recipes...");
        recipeSpawnEgg.loadRecipe(plugin);
      log.infoLog("Recipes loaded");
    log.infoLog("All things loaded! Good Luck with this Plugin!");
    log.infoLog("SoulBoss reloaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded all config files in this plugin
   * @param plugin Requires the Main class
   */
  public void reloadConfiguration(Plugin plugin){
    log.infoLog("Load Configurations...");
    configuration.loadFiles(plugin);
    log.infoLog("Configurations loaded!");
  }
}
