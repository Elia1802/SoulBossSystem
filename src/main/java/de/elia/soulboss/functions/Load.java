package de.elia.soulboss.functions;

import de.elia.soulboss.commands.help.HelpCommand;
import de.elia.soulboss.commands.idea.IdeaCommand;
import de.elia.soulboss.commands.items.ItemGiveCommand;
import de.elia.soulboss.commands.mob.SpawnMobCommand;
import de.elia.soulboss.commands.plugin.PluginCommand;
import de.elia.soulboss.events.achievements.bossfight.AchievementBossFightEvent;
import de.elia.soulboss.events.achievements.bossfightzombie.AchievementBossFightZombieEvent;
import de.elia.soulboss.events.achievements.bossfightzombieend.AchievementBossFightZombieEndEvent;
import de.elia.soulboss.events.attacks.fire.AttackFireEvent;
import de.elia.soulboss.events.attacks.strikelightning.AttackStrikeLightningEvent;
import de.elia.soulboss.events.attacks.teleport.AttackTeleportEvent;
import de.elia.soulboss.events.connections.DisconnectEvent;
import de.elia.soulboss.events.death.EntityDeathEvent;
import de.elia.soulboss.events.spawn.ZombieSpawnEvent;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulboss.messages.message.CustomMessages;
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

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all things in this Plugin
   * @param plugin Requires the Instance of the Main Class
   * @param pluginManager Requires the PluginManager of Bukkit
   */
  public void loadPlugin(Plugin plugin, PluginManager pluginManager){
    CustomMessages log = new CustomMessages();
    Register.Commands command = new Register.Commands();
    Register.Events event = new Register.Events();
    Register.Utils utils = new Register.Utils();
    Register.Configuration configuration = new Register.Configuration();
    Register.Recipes recipes = new Register.Recipes();
    log.infoLog("Load SoulBoss...");
      log.infoLog("Load Commands...");
        command.load("bosshelp", new HelpCommand());
        command.load("mobidea", new IdeaCommand());
        command.load("bossgive", new ItemGiveCommand());
        command.load("bossfight", new SpawnMobCommand());
        command.load("soulboss", new PluginCommand());
      log.infoLog("Commands loaded!");
      log.infoLog("Load Events...");
        event.load(pluginManager, new AchievementBossFightEvent(), plugin);
        event.load(pluginManager, new AchievementBossFightZombieEvent(), plugin);
        event.load(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        event.load(pluginManager, new AttackFireEvent(), plugin);
        event.load(pluginManager, new AttackStrikeLightningEvent(), plugin);
        event.load(pluginManager, new AttackTeleportEvent(), plugin);
        event.load(pluginManager, new DisconnectEvent(), plugin);
        event.load(pluginManager, new EntityDeathEvent(), plugin);
        event.load(pluginManager, new ZombieSpawnEvent(), plugin);
      log.infoLog("Events loaded!");
      log.infoLog("Load Configurations...");
        configuration.load();
      log.infoLog("Configurations loaded!");
      log.infoLog("load Utils...");
        utils.load(plugin);
      log.infoLog("Utils loaded!");
      log.infoLog("load Recipes...");
        recipes.load(plugin);
      log.infoLog("Recipes loaded");
    log.infoLog("All things loaded! Good Luck with this Plugin!");
    log.infoLog("SoulBoss loaded!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reloaded all things in this Plugin.
   * @param plugin Requires the Instance of the Main Class
   * @param pluginManager Requires the PluginManager of Bukkit
   */
  public void reload(Plugin plugin, PluginManager pluginManager){
    CustomMessages log = new CustomMessages();
    Register.Commands command = new Register.Commands();
    Register.Events event = new Register.Events();
    Register.Utils utils = new Register.Utils();
    Register.Configuration configuration = new Register.Configuration();
    Register.Recipes recipes = new Register.Recipes();
    log.infoLog("Reload SoulBoss...");
    log.infoLog("Remove Recipes...");
      Bukkit.removeRecipe(new NamespacedKey(plugin, "v4j23hdu-df4e-36ta-r4sf-2n0c3n8cky9x"));
    log.infoLog("Recipes Removed!");
    log.infoLog("Save Configurations...");
      configuration.save();
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
    configuration.playerRegisterConfiguration().clear();
    log.infoLog("Bosses removed!");
    log.infoLog("Load Commands...");
    command.load("bosshelp", new HelpCommand());
    command.load("mobidea", new IdeaCommand());
    command.load("bossgive", new ItemGiveCommand());
    command.load("bossfight", new SpawnMobCommand());
    command.load("soulboss", new PluginCommand());
    log.infoLog("Commands loaded!");
    log.infoLog("Load Events...");
    event.load(pluginManager, new AchievementBossFightEvent(), plugin);
    event.load(pluginManager, new AchievementBossFightZombieEvent(), plugin);
    event.load(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
    event.load(pluginManager, new AttackFireEvent(), plugin);
    event.load(pluginManager, new AttackStrikeLightningEvent(), plugin);
    event.load(pluginManager, new AttackTeleportEvent(), plugin);
    event.load(pluginManager, new DisconnectEvent(), plugin);
    event.load(pluginManager, new EntityDeathEvent(), plugin);
    event.load(pluginManager, new ZombieSpawnEvent(), plugin);
    log.infoLog("Events loaded!");
    log.infoLog("Load Configurations...");
    configuration.load();
    log.infoLog("Configurations loaded!");
    log.infoLog("load Utils...");
    utils.load(plugin);
    log.infoLog("Utils loaded!");
    log.infoLog("load Recipes...");
    recipes.load(plugin);
    log.infoLog("Recipes loaded");
    log.infoLog("All things loaded! Good Luck with this Plugin!");
    log.infoLog("SoulBoss reloaded!");
  }
}
