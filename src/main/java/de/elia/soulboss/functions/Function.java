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
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Load Class for the Main Class.
 */
public class Function {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all things in this Plugin
   * @param plugin Requires the Instance of the Main Class
   * @param pluginManager Requires the PluginManager of Bukkit
   */
  public void loadFunctions(Plugin plugin, PluginManager pluginManager){
    MessageBuilder log = new MessageBuilder();
    log.infoLog("Load SoulBoss...");
      log.infoLog("Load Commands...");
        log.infoLog("Load Command mobidea...");
          Register.Commands.load("mobidea", new IdeaCommand());
          Register.Commands.load("bossgive", new ItemGiveCommand());
          Register.Commands.load("bossfight", new SpawnMobCommand());
          Register.Commands.load("soulboss", new PluginCommand());
        log.infoLog("Command mobidea loaded!");
        log.infoLog("Load Command bossfight...");
          Register.Commands.load("bossfight", new SpawnMobCommand());
        log.infoLog("Command bossfight loaded!");
        log.infoLog("Load Command soulboss...");
          Register.Commands.load("soulboss", new PluginCommand());
        log.infoLog("Command soulboss loaded!");
      log.infoLog("Commands loaded!");
      log.infoLog("Load Events...");
        Register.Events.load(pluginManager, new AchievementBossFightEvent(), plugin);
        Register.Events.load(pluginManager, new AchievementBossFightZombieEvent(), plugin);
        Register.Events.load(pluginManager, new AchievementBossFightZombieEndEvent(), plugin);
        Register.Events.load(pluginManager, new AttackFireEvent(), plugin);
        Register.Events.load(pluginManager, new AttackStrikeLightningEvent(), plugin);
        Register.Events.load(pluginManager, new AttackTeleportEvent(), plugin);
        Register.Events.load(pluginManager, new ZombieSpawnEvent(), plugin);
      log.infoLog("Events loaded!");
      log.infoLog("Load Configurations...");
        Register.Configuration.load();
      log.infoLog("Configurations loaded!");
      log.infoLog("load Utils...");
        Register.Utils.load(plugin);
      log.infoLog("Utils loaded!");
      log.infoLog("load Recipes...");
        Register.Recipes.load(plugin);
      log.infoLog("Recipes loaded");
    log.infoLog("All things loaded! Good Luck with this Plugin!");
    log.infoLog("SoulBoss loaded!");
  }
}
