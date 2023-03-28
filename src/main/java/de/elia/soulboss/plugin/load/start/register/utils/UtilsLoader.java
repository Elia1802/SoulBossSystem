package de.elia.soulboss.plugin.load.start.register.utils;

import de.elia.soulboss.block.BreakBlock;
import de.elia.CustomMessages;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register all Utils or other things.
 */
public class UtilsLoader {

  private final CustomMessages message = new CustomMessages();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register the Utils and other things
   */
  public void loadUtils(Plugin plugin){
    String key = "680035753";
    message.infoLog("load BreakTask with the NamespacedKey: " + key + "...");
    new BreakBlock(plugin).breakTask(new NamespacedKey(plugin, key));
    message.infoLog("BreakTasks loaded!");
  }

}
