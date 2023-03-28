package de.elia.bossfightcreator.load.stop;

import de.elia.bossfightcreator.Instances.Files;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Stop Methode to stop the Plugin
 */
public class StopPlugin {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Stop Methode to stop the Plugin
   */
  public static void stop(){
    var world_status = Files.WORLD_STATUS;
    world_status.save();
  }
}
