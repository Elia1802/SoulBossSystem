package de.elia.bossfightcreator.fight.game;

import de.elia.Keys;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.fight.game.maps.GameMaps;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Bossfight (Game) of a {@link Player}
 */
public class Game {

  private final Location location;
  public final Player player;
  private final String gameKey;
  private ZombieBoss boss;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game (BossFight)
   * @param location Requires the {@link Location} to spawn the {@link ZombieBoss}
   * @param player Requires the {@link Player}
   * @param gameKey Requires a key
   */
  public Game(@NotNull Location location, @NotNull Player player, String gameKey){
    this.location = location;
    this.player = player;
    this.gameKey = gameKey;
    GameMaps.EXIST_GAME_OF_PLAYER.put(player.getUniqueId(), this);
    if (player == null)return;
    new BukkitRunnable() {
      @Override
      public void run() {
        boss = new ZombieBoss(location);
        GameMaps.BOSS_KEY.put(boss, gameKey);
      }
    }.runTask(Plugin.instance);
    GameMaps.GAMES.add(this);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Kill a Boss in the near of the player of 5 Blocks
   * @param player Requires the {@link Player}
   */
  public void killThis(@NotNull Player player){
    Collection<LivingEntity> entities = player.getLocation().getNearbyLivingEntities(5);
    for (Entity entity : entities){
      if (entity.getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
        entity.remove();
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description End this Game
   */
  public void killGame(){
    var map = GameMaps.EXIST_GAME_OF_PLAYER;
    if (map.containsValue(this)) {
      this.killThis(player);
      map.remove(player.getUniqueId());
      GameMaps.GAMES.remove(this);
    }
  }

  public void removeGame(){
    var gameMap = GameMaps.EXIST_GAME_OF_PLAYER;
    if (gameMap.containsValue(this)) {
      new GameEndTimer().start(60*20, player, null);
      gameMap.remove(player.getUniqueId());
      GameMaps.GAMES.remove(this);
      System.out.println("ERFOLGREICH");
    }
    new GameEndTimer().start(60*20, player, null);
    gameMap.remove(player.getUniqueId());
    GameMaps.GAMES.remove(this);
    System.out.println("NICHT ERFOLGREICH");
  }

  public class GameEndTimer extends StartTasks {

    public GameEndTimer(){

    }

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

  private void timeMessage(){

  }
}
