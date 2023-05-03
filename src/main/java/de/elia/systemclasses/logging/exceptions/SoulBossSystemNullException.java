package de.elia.systemclasses.logging.exceptions;

import de.elia.systemclasses.logging.PluginLogger;
import org.jetbrains.annotations.NotNull;

public class SoulBossSystemNullException extends Exception {

  private final String message;
  private final PluginLogger pluginLogger = new PluginLogger("SoulBossSystemNullException");

  public SoulBossSystemNullException(){
    super();
    this.message = null;
  }

  public SoulBossSystemNullException(String message){
    super(message);
    this.message = message;
  }

  public SoulBossSystemNullException(String message, Throwable cause){
    super(message, cause);
    this.message = message;
  }

  public SoulBossSystemNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
    super(message, cause, enableSuppression, writableStackTrace);
    this.message = message;
  }

  public void stacktrace(){
    pluginLogger.stackstrace(message, this);
  }

  @NotNull
  public String message(){
    return this.message;
  }

  public static class CheckVariable {

    public boolean check(Object x, String variableName) throws SoulBossSystemNullException {
      if (x == null){
        throw new SoulBossSystemNullException(variableName + " is null!");
      }
      return true;
    }

  }

}
