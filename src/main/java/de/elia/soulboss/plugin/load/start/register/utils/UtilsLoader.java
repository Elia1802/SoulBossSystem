package de.elia.soulboss.plugin.load.start.register.utils;

import de.elia.PluginKeys;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.block.BreakBlock;
import de.elia.PluginMessages;
import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register all Utils or other things.
 */
public class UtilsLoader {

  private final PluginMessages message = new PluginMessages();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register the Utils and other things
   */
  public void loadUtils(Plugin plugin){
    var log = SoulBoss.soulBoss().soulBossLogger();
    String key = "680035753";
    log.logInfo("load BreakTask with the NamespacedKey: " + PluginKeys.ZOMBIE_KEY.key());
    new BreakBlock(plugin).breakTask(PluginKeys.ZOMBIE_KEY.key());
    log.logInfo("BreakTasks loaded!");
  }

}
