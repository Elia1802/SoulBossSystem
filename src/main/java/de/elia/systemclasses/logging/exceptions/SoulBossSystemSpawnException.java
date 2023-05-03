package de.elia.systemclasses.logging.exceptions;

import de.elia.systemclasses.logging.PluginLogger;

public class SoulBossSystemSpawnException extends Exception {

  private final String message;
  private final PluginLogger pluginLogger = new PluginLogger("SoulBossSystemSpawnException");

  public SoulBossSystemSpawnException(){
    super();
    this.message = null;
  }

  public SoulBossSystemSpawnException(String message){
    super(message);
    this.message = message;
  }

  public SoulBossSystemSpawnException(String message, Throwable cause){
    super(message, cause);
    this.message = message;
  }

  public SoulBossSystemSpawnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
    super(message, cause, enableSuppression, writableStackTrace);
    this.message = message;
  }

  public void stacktrace(){
    pluginLogger.stackstrace(message, this);
  }

  public static class Check {

    public void check(int code) throws SoulBossSystemSpawnException {
      if (code == 0){
        System.out.println(code);
      }else if (code == 3) {
        throw new SoulBossSystemSpawnException("Error by create properties of the boss");
      }else if (code == 2) {
        throw new SoulBossSystemSpawnException("Error by set armor of the boss");
      }else if (code == 1) {
        throw new SoulBossSystemSpawnException("Error by spawn the boss");
      }else if (code == 4) {
        throw new SoulBossSystemSpawnException("Error the boss is not loaded");
      }
    }
  }

}
