package de.elia.soulboss.utils;

import de.elia.api.logging.PluginLogger;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.block.BreakBlock;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @description This is the Register class to register all Utils or other things.
 */
public class UtilsLoader {

  private final PluginMessages message = new PluginMessages();
  
  public static void loadUtils(Plugin plugin){
    var log = SoulBoss.soulBoss().soulBossLogger();
    log.logInfo("load BreakTask with the NamespacedKey: " + PluginKeys.ZOMBIE_KEY.key());
    new BreakBlock(plugin).breakTask(PluginKeys.ZOMBIE_KEY.key());
    log.logInfo("BreakTasks loaded!");
  }

}
