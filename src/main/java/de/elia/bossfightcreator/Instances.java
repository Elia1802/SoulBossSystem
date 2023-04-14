package de.elia.bossfightcreator;

import de.elia.CustomMessages;
import de.elia.api.configuration.SoulConfiguration;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description All Instances and function for all functions in the plugin bossfightloader
 */
public class Instances {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description All Instances for the Plugin
   */
  public static class Plugin {
    public static org.bukkit.plugin.Plugin instance; //the instance of this Plugin
    public static World world_bossfight; //the bossfight world
    public static final CustomMessages MESSAGES = new CustomMessages(); //the message builder of this Plugin (overrides in the plugin SoulBoss)
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description All functions for the files in this File
   */
  public static class Files {
    public static final SoulConfiguration WORLD_STATUS = new SoulConfiguration(Plugin.instance, "worldstatus.yml"); //the file to save the status if the world loaded or not

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description A class to crate a bossfight file
     */
    public static class CreateBossfightFile {

      private final SoulConfiguration bossfightFile;

      /**
       * @author Elia
       * @version 1.0
       * @since 1.0
       * @description Create a bossfightfile
       * @param fileName Requires the name of the file
       */
      public CreateBossfightFile(String fileName){
        bossfightFile = new SoulConfiguration(Plugin.instance, "bossfights/", fileName + ".yml");
        bossfightFile.copyDefaults(true);
        bossfightFile.save();
      }

      /**
       * @author Elia
       * @version 1.0
       * @since 1.0
       * @description Gets the File
       * @return {@link SoulConfiguration}
       */
      @NotNull
      public SoulConfiguration getFile() {
        return this.bossfightFile;
      }
    }
  }
}
