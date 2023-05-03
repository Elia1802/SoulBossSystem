package de.elia.systemclasses.logging.exceptions;

import de.elia.systemclasses.logging.PluginLogger;

public class SoulBossSystemIntOutOfRangeException extends Exception {

  private final String message;
  private final PluginLogger pluginLogger = new PluginLogger("SoulBossSystemIntOutOfRangeException");

  public SoulBossSystemIntOutOfRangeException(){
    super();
    this.message = null;
  }

  public SoulBossSystemIntOutOfRangeException(String message){
    super(message);
    this.message = message;
  }

  public SoulBossSystemIntOutOfRangeException(String message, Throwable cause){
    super(message, cause);
    this.message = message;
  }

  public SoulBossSystemIntOutOfRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
    super(message, cause, enableSuppression, writableStackTrace);
    this.message = message;
  }

  public void stacktrace(){
    pluginLogger.stackstrace(message, this);
  }

  public static class Check {

    private final int min;
    private final int max;
    private final int value;

    public Check(int min, int max, int value){
      this.min = min;
      this.max = max;
      this.value = value;
    }

    public boolean check() throws SoulBossSystemIntOutOfRangeException {
      if (value > max){
        throw new SoulBossSystemIntOutOfRangeException(value + " cannot bigger than " + max);
      }else if (value < min) {
        throw new SoulBossSystemIntOutOfRangeException(value + " cannot smaller than " + min);
      }
      return true;
    }

  }

}
