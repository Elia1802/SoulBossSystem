package de.elia.soulboss.fight;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.messages.messages.CustomMessages;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link StartTasks}
 * @description This is the Utils for the Bossfight
 */
public class Utils extends StartTasks {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This is the Start methode for the Bossfight
   */
  @Override
  public void start(int time, Player player){
    CustomMessages message = new CustomMessages();
    TimerUtils.countdownAndRun(time,
      new Runnable() {
        @Override
        public void run() {}
      }
    );
    TimerUtils.countdownInterval(time,
      new TimerUtils.TimeRunnable() {
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks/20; //Use this to show seconds
            //run countdown
            String string = String.valueOf(seconds);
            if (seconds == 10)message.infoLog("Start Countdown with 10 seconds!");
            if (seconds == 10)message.broadcastWithPrefix(message.red("In").append(message.darkPurple(string).append(message.red("Sekunden wird ein Boss spawnen!"))));
            if (seconds == 3)message.broadcastWithPrefix(message.red("In").append(message.darkPurple(string).append(message.red("Sekunden wird ein Boss spawnen!"))));
            if (seconds == 2)message.broadcastWithPrefix(message.red("In").append(message.darkPurple(string).append(message.red("Sekunden wird ein Boss spawnen!"))));
            if (seconds == 1)message.broadcastWithPrefix(message.red("In").append(message.darkPurple(string).append(message.red("Sekunde wird ein Boss spawnen!"))));
          }
        }
      },
      new Runnable() {
        @Override
        public void run() {
          //if countdown 0 run this
          new BukkitRunnable() {
            @Override
            public void run(){
              message.infoLog("Countdown finished!");
              player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
              message.infoLog("Spawn the Boss...");
                new BossFight(player, player.getLocation());
              message.infoLog("Boss spawned!");
              message.infoLog("BossFight loaded! Good Luck with the new Mob! :)");
            }
          }.runTask(SoulBoss.soulBoss());
        }
      }
    );
  }

}
