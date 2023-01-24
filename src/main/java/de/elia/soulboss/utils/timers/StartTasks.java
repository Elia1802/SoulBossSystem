package de.elia.soulboss.utils.timers;

import org.bukkit.entity.Player;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Start Class for start all thinks if the runnable finished.
 */
public class StartTasks {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Start a Countdown
   * @param time Requires the seconds
   */
  public void start(int time, Player player){
    TimerUtils.countdownAndRun(time,
      new Runnable() {
        @Override
        public void run() {

        }
      }
    );
    TimerUtils.countdownInterval(time,
      new TimerUtils.TimeRunnable() {
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks/20; //Use this to show seconds
            //run countdown
          }
        }
      },
      new Runnable() {
        @Override
        public void run() {
          //if countdown 0 run this
        }
      }
    );
  }

}
