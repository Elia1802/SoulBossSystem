package de.elia;

import org.jetbrains.annotations.NotNull;

import java.lang.String;
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
   * @description log a info message
   * @param message Requires the Message
   */
  public void logInfo(@NotNull String message){
    logger.info("[INFO]" + message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a warning message
   * @param message Requires the Message
   */
  public void logWarning(@NotNull String message){
    logger.warning("[WARNING]" + message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a error message
   * @param message Requires the Message
   */
  public void logError(@NotNull String message){
    logger.severe("[ERROR]" + message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the Logger
   */
  @NotNull
  public Logger logger() {
    return logger;
  }
}
