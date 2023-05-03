package de.elia.soulboss.plugin.load;

import de.elia.systemclasses.keys.PluginKeys;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.commands.help.HelpCommand;
import de.elia.soulboss.commands.idea.IdeaCommand;
import de.elia.soulboss.commands.items.ItemGiveCommand;
import de.elia.soulboss.commands.plugin.PluginCommand;
import de.elia.soulboss.commands.world.WorldCommand;
import de.elia.soulboss.events.achievements.bossfight.AchievementBossFightEvent;
import de.elia.soulboss.events.achievements.bossfightzombieend.AchievementBossFightZombieEndEvent;
import de.elia.soulboss.events.attacks.fire.AttackFireEvent;
import de.elia.soulboss.events.attacks.strikelightning.AttackStrikeLightningEvent;
import de.elia.soulboss.events.attacks.teleport.AttackTeleportEvent;
import de.elia.soulboss.events.connections.connection.ConnectionEvent;
import de.elia.soulboss.plugin.load.start.register.commands.CommandLoader;
import de.elia.soulboss.plugin.load.start.register.configuation.ConfigurationLoader;
import de.elia.soulboss.plugin.load.start.register.events.EventLoader;
import de.elia.soulboss.plugin.load.start.register.recipes.RecipeLoader;
import de.elia.soulboss.plugin.load.start.register.utils.UtilsLoader;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Load Class for the Main Class.
 */
public class Load {

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
  public void loadPlugin(Plugin plugin, PluginManager pluginManager) throws SoulBossSystemNullException {
    var log = SoulBoss.soulBoss().soulBossLogger();
    log.logInfo("Load SoulBoss...");
      log.logInfo("Load Commands...");
        command.loadCommand("bosshelp", new HelpCommand());
        command.loadCommand("mobidea", new IdeaCommand());
        command.loadCommand("bossgive", new ItemGiveCommand());
        //command.loadCommand("bossfight", new SpawnMobCommand());
        command.loadCommand("soulboss", new PluginCommand());
        command.loadCommand("tpworld", new WorldCommand());
      log.logInfo("Commands loaded!");
      log.logInfo("Load Events...");
        event.loadEvents(pluginManager, new AchievementBossFightEvent(), plugin);
        event.loadEvents(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        event.loadEvents(pluginManager, new AttackFireEvent(), plugin);
        event.loadEvents(pluginManager, new AttackStrikeLightningEvent(), plugin);
        event.loadEvents(pluginManager, new AttackTeleportEvent(), plugin);
        //event.loadEvents(pluginManager, new BossDeathEvent(), plugin);
        event.loadEvents(pluginManager, new ConnectionEvent(), plugin);
        //event.loadEvents(pluginManager, new DisconnectEvent(), plugin); (Depraceted)
        //event.loadEvents(pluginManager, new ZombieSpawnEvent(), plugin);
      log.logInfo("Events loaded!");
      log.logInfo("Load Configurations...");
        configuration.loadFiles(plugin);
      log.logInfo("Configurations loaded!");
      log.logInfo("load Utils...");
        utils.loadUtils(plugin);
      log.logInfo("Utils loaded!");
      log.logInfo("load Recipes...");
        recipeSpawnEgg.loadRecipe(plugin);
      log.logInfo("Recipes loaded");
    log.logInfo("All things loaded! Good Luck with this Plugin!");
    log.logInfo("SoulBoss loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded all things in this Plugin
   * @param plugin Requires the Main class
   * @param pluginManager Requires a {@link PluginManager}
   */
  public void reloadPlugin(Plugin plugin, PluginManager pluginManager) throws SoulBossSystemNullException {
    var log = SoulBoss.soulBoss().soulBossLogger();
    log.logInfo("Reload SoulBoss...");
    log.logInfo("Remove Recipes...");
      Bukkit.removeRecipe(PluginKeys.RECIPE_KEY.key());
    log.logInfo("Recipes Removed!");
    log.logInfo("Save Configurations...");
      configuration.save(plugin);
    log.logInfo("Configurations saved!");
    log.logInfo("Remove Bosses...");
    Bukkit.getServer().getWorld("world").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_nether").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    Bukkit.getServer().getWorld("world_the_end").getEntities().forEach((entity) ->{
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    });
    //configuration.playerRegisterStorage().clear();
    log.logInfo("Bosses removed!");
      log.logInfo("Load Commands...");
        command.loadCommand("bosshelp", new HelpCommand());
        command.loadCommand("mobidea", new IdeaCommand());
        command.loadCommand("bossgive", new ItemGiveCommand());
        //command.loadCommand("bossfight", new SpawnMobCommand());
        command.loadCommand("soulboss", new PluginCommand());
        command.loadCommand("tpworld", new WorldCommand());
      log.logInfo("Commands loaded!");
      log.logInfo("Load Events...");
        event.loadEvents(pluginManager, new AchievementBossFightEvent(), plugin);
        event.loadEvents(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        event.loadEvents(pluginManager, new AttackFireEvent(), plugin);
        event.loadEvents(pluginManager, new AttackStrikeLightningEvent(), plugin);
        event.loadEvents(pluginManager, new AttackTeleportEvent(), plugin);
        //event.loadEvents(pluginManager, new BossDeathEvent(), plugin);
        event.loadEvents(pluginManager, new ConnectionEvent(), plugin);
        //event.loadEvents(pluginManager, new DisconnectEvent(), plugin); (Depraceted)
        //event.loadEvents(pluginManager, new ZombieSpawnEvent(), plugin);
      log.logInfo("Events loaded!");
      log.logInfo("Load Configurations...");
        configuration.loadFiles(plugin);
      log.logInfo("Configurations loaded!");
      log.logInfo("load Utils...");
        utils.loadUtils(plugin);
      log.logInfo("Utils loaded!");
      log.logInfo("load Recipes...");
        recipeSpawnEgg.loadRecipe(plugin);
      log.logInfo("Recipes loaded");
    log.logInfo("All things loaded! Good Luck with this Plugin!");
    log.logInfo("SoulBoss reloaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded all config files in this plugin
   * @param plugin Requires the Main class
   */
  public void reloadConfiguration(Plugin plugin) throws SoulBossSystemNullException {
    var log = SoulBoss.soulBoss().soulBossLogger();
    log.logInfo("Load Configurations...");
    configuration.loadFiles(plugin);
    log.logInfo("Configurations loaded!");
  }
}
