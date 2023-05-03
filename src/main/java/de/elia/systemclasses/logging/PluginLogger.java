package de.elia.systemclasses.logging;

import de.elia.PluginMain;
import de.elia.api.configuration.SoulConfiguration;
import de.elia.systemclasses.logging.debug.PluginDebbuger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;

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
    this.logger = (Logger) LogManager.getLogger(loggerName);
  }

  public static class SaveError {
    private SoulConfiguration file;

    private void createFile(String name){
      SoulConfiguration x = new SoulConfiguration(PluginMain.main(), "errors/", name + ".txt");
      file = x;
      PluginDebbuger.debug(x);
      PluginDebbuger.debug(file);
    }

    public void saveError(@NotNull Exception exception, String name){
      createFile(name);
      try {
        PrintWriter writer = new PrintWriter(file.getFile());
        exception.printStackTrace(writer);
        file.save();
        writer.close();
      }catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Print a Stacktrace in the console
   * @param exception Requires the exception
   */
  public void stackstrace(Exception exception) {
    logger.error("Error ocurred: ", exception);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Print a Stacktrace in the console
   * @param message
   * @param exception
   */
  public void stackstrace(String message, Exception exception) {
    logger.error("Error ocurred: " + message, exception);
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
    logger.warn("[WARNING]" + message);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description log a error message
   * @param message Requires the Message
   */
  public void logError(@NotNull String message){
    logger.error("[ERROR]" + message);
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
