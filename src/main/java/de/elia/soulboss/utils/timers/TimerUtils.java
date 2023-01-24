package de.elia.soulboss.utils.timers;

import de.elia.soulboss.SoulBoss;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Timer class of the Plugin SoulBoss.
 */
public class TimerUtils {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Run a timer.
   * @param runnable Requires the runnable to be executed when ticks are 0.
   * @param ticks Requires the ticks how long the runnable goes
   */
  public static void countdownAndRun(int ticks, Runnable runnable){
    if (runnable == null)return;
    new BukkitRunnable() {
      private int internalTicks = ticks;
      @Override
      public void run() {
        if (internalTicks == 0) {
          runnable.run();
          cancel();
          return;
        }

        internalTicks--;
      }
    }.runTaskTimerAsynchronously(SoulBoss.soulBoss(), 1, 1);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Run a timer.
   * @param ticks Requires the ticks how long the runnable goes
   * @param intervalRunnable Requires a runnable counting down the timer
   * @param finalRunnable Requires a runnable to be executed when intervalRunnable has finished running.
   */
  public static void countdownInterval(int ticks, TimerUtils.TimeRunnable intervalRunnable, Runnable finalRunnable){
    if (intervalRunnable == null || finalRunnable == null)return;
    new BukkitRunnable() {
      private int internalTicks = ticks;
      @Override
      public void run() {
        if (internalTicks == 0) {
          finalRunnable.run();
          cancel();
          return;
        }
        intervalRunnable.run(internalTicks);
        internalTicks--;
      }
    }.runTaskTimerAsynchronously(SoulBoss.soulBoss(), 1, 1);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the TimerRunnable interface of the Plugin SoulBoss.
   */
  public interface TimeRunnable {
    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Just lets a countdown count down without what happens after
     * @param ticks Requires the ticks how long the runnable goes
     */
    void run(int ticks);
  }
}
