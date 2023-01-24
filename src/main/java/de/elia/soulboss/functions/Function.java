package de.elia.soulboss.functions;

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
import de.elia.soulboss.events.spawn.ZombieSpawnEvent;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulmain.allplugins.messages.builder.MessageBuilder;
import de.elia.soulmain.allplugins.messages.builder.Messages;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Load Class for the Main Class.
 */
public class Function {

  private static final Messages log = new MessageBuilder(); //Gets the Message class of SoulMain

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all things in this Plugin
   * @param plugin Requires the Instance of the Main Class
   * @param pluginManager Requires the PluginManager of Bukkit
   */
  public static void loadFunctions(Plugin plugin, PluginManager pluginManager){
    log.log(Level.INFO, "Load SoulBoss...");
      log.log(Level.INFO, "Load Commands...");
        log.log(Level.INFO, "Load Command mobidea...");
          Register.Commands.load("mobidea", new IdeaCommand());
          Register.Commands.load("bossgive", new ItemGiveCommand());
          Register.Commands.load("bossfight", new SpawnMobCommand());
          Register.Commands.load("soulboss", new PluginCommand());
        log.log(Level.INFO, "Command mobidea loaded!");
        log.log(Level.INFO, "Load Command bossfight...");
          Register.Commands.load("bossfight", new SpawnMobCommand());
        log.log(Level.INFO, "Command bossfight loaded!");
        log.log(Level.INFO, "Load Command soulboss...");
          Register.Commands.load("soulboss", new PluginCommand());
        log.log(Level.INFO, "Command soulboss loaded!");
      log.log(Level.INFO, "Commands loaded!");
      log.log(Level.INFO, "Load Events...");
        Register.Events.load(pluginManager, new AchievementBossFightEvent(), plugin);
        Register.Events.load(pluginManager, new AchievementBossFightZombieEvent(), plugin);
        Register.Events.load(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        Register.Events.load(pluginManager, new AttackFireEvent(), plugin);
        Register.Events.load(pluginManager, new AttackStrikeLightningEvent(), plugin);
        Register.Events.load(pluginManager, new AttackTeleportEvent(), plugin);
        Register.Events.load(pluginManager, new ZombieSpawnEvent(), plugin);
      log.log(Level.INFO, "Events loaded!");
      log.log(Level.INFO, "Load Configurations...");
        Register.Configuration.load();
      log.log(Level.INFO, "Configurations loaded!");
      log.log(Level.INFO, "load Utils...");
        Register.Utils.load(plugin);
      log.log(Level.INFO, "Utils loaded!");
      log.log(Level.INFO, "load Recipes...");
        Register.Recipes.load(plugin);
      log.log(Level.INFO, "Recipes loaded");
    log.log(Level.INFO, "All things loaded! Good Luck with this Plugin!");
    log.log(Level.INFO, "SoulBoss loaded!");
  }
}
