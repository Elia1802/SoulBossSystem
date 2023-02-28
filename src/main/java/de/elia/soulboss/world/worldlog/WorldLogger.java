package de.elia.soulboss.world.worldlog;

import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The logger for the world generation of this Plugin
 */
public class WorldLogger {

  private final String name;

  public WorldLogger(String name){
    this.name = name;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description logt a message in the console
   * @param message Requires the message
   */
  public void log(String message){
    Logger logger = Logger.getLogger(name);
    logger.log(Level.FINE, message);
  }

}
