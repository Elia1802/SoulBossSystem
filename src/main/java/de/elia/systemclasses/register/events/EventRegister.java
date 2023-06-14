package de.elia.systemclasses.register.events;

import de.elia.achivements.events.bossfight.AchievementBossFightEvent;
import de.elia.achivements.events.bossfightzombieend.AchievementBossFightZombieEndEvent;
import de.elia.bossfightcreator.game.executer.NewGameExecuter;
import de.elia.bossfightcreator.events.connect.ConnectionEvent;
import de.elia.bossfightcreator.events.disconnect.DisconnectEvent;
import de.elia.items.events.magicbook.MagicBookEvent;
import de.elia.soulboss.events.attacks.fire.AttackFireEvent;
import de.elia.soulboss.events.attacks.strikelightning.AttackStrikeLightningEvent;
import de.elia.soulboss.events.attacks.teleport.AttackTeleportEvent;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description This is the Register class to register all Events.
 */
public class EventRegister {

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description Register the Event
   * @param pluginManager Requires a {@link PluginManager} name
   * @param main Requires the Instance of the Main class
   */
  public static void registerEvents(@NotNull PluginManager pluginManager, @NotNull JavaPlugin main){
    pluginManager.registerEvents(new AchievementBossFightEvent(), main);
    pluginManager.registerEvents(new AchievementBossFightZombieEndEvent(), main);
    pluginManager.registerEvents(new ConnectionEvent(), main);
    pluginManager.registerEvents(new DisconnectEvent(), main);
    pluginManager.registerEvents(new MagicBookEvent(main), main);
    pluginManager.registerEvents(new AttackFireEvent(), main);
    pluginManager.registerEvents(new AttackStrikeLightningEvent(), main);
    pluginManager.registerEvents(new AttackTeleportEvent(), main);
    pluginManager.registerEvents(new NewGameExecuter(), main);
  }

}
