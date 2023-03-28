package de.elia.soulboss.utils.timers;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author D1p4k, Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Start Class for start all thinks if the runnable finished.
 */
public class StartTasks {

  /**
   * @author D1p4k, Elia
   * @version 1.0
   * @since 1.0
   * @description Start a Countdown
   * @param time Requires the seconds
   * @param player Requires a {@link Player}
   * @param location Requires a {@link Location}
   */
  public void start(int time, Player player, Location location){
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
