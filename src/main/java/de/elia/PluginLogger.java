package de.elia;

import org.jetbrains.annotations.NotNull;

import java.lang.String;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description A logger for this Plugin
 */
public class PluginLogger {

  private final @NotNull String loggerName;
  private final Logger logger;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a new Logger
   * @param loggerName Requires a name for the {@link Logger}
   */
  public PluginLogger(@NotNull String loggerName){
    this.loggerName = loggerName;
    this.logger = Logger.getLogger(loggerName);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a message
   * @param level Requires the log {@link Level} to log the messsage
   * @param message Requires the Message
   */
  public void log(@NotNull Level level, @NotNull String message){
    logger.log(level, message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a info message
   * @param message Requires the Message
   */
  public void logInfo(@NotNull String message){
    logger.info(message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a warning message
   * @param message Requires the Message
   */
  public void logWarning(@NotNull String message){
    logger.warning(message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a error message
   * @param message Requires the Message
   */
  public void logError(@NotNull String message){
    logger.severe(message);
  }

}
