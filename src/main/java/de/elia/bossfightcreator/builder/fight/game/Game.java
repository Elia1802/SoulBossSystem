package de.elia.bossfightcreator.builder.fight.game;

import de.elia.CustomMessages;
import de.elia.Keys;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.arena.sender.ArenaSender;
import de.elia.bossfightcreator.builder.fight.game.maps.GameList;
import de.elia.bossfightcreator.builder.save.SaveGame;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
import net.minecraft.world.entity.monster.Zombie;
import org.bukkit.Bukkit;
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

  private final ArenaSender sender = new ArenaSender();
  private final SaveGame saveGame;
  private final Arenas arena;
  public final Player player;
  private final String gameKey;
  private Zombie boss;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game (BossFight)
   * @param arena Requires the {@link Arenas} to get the gameArena
   * @param player Requires the {@link Player}
   * @param gameKey Requires a key
   */
  public Game(@NotNull Arenas arena, @NotNull Player player, String gameKey){
    this.arena = arena;
    this.player = player;
    this.gameKey = gameKey;
    this.saveGame = new SaveGame(player.getUniqueId(), this);
    if (player == null)return;
    new BukkitRunnable() {
      @Override
      public void run() {
        boss = new ZombieBoss(arena.location());
      }
    }.runTask(Plugin.instance);
    GameList.GAMES.add(this);
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
   * @description killed this Game
   */
  public void killGame(){
    if (saveGame.containsGame(this)) {
      this.killThis(player);
      String permission = gameKey;
      for (Player targets : Bukkit.getOnlinePlayers()) {
        if (targets.hasPermission(permission))targets.teleport(Bukkit.getWorld("world").getSpawnLocation());
      }
      saveGame.removeGame(player.getUniqueId());
      GameList.GAMES.remove(this);
      sender.resetArena(arena);
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description start the end timer
   */
  public void end(){
    new GameEndTimer().start(60*20, player, Bukkit.getWorld("world").getSpawnLocation());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Removed this game of all Lists maps and other thinks and reset the arena
   */
  public void removeGame(){
    if (saveGame.containsGame(this)) {
      sender.resetArena(arena);
      saveGame.removeGame(player.getUniqueId());
      GameList.GAMES.remove(this);
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @extends {@link StartTasks}
   * @description
   */
  public class GameEndTimer extends StartTasks {

    public GameEndTimer(){

    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Run the countdown
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
              if (seconds == 60)timeMessage(String.valueOf(seconds));
              if (seconds == 30)timeMessage(String.valueOf(seconds));
              if (seconds == 20)timeMessage(String.valueOf(seconds));
              if (seconds == 10)timeMessage(String.valueOf(seconds));
              if (seconds == 5)timeMessage(String.valueOf(seconds));
              if (seconds == 3)timeMessage(String.valueOf(seconds));
              if (seconds == 2)timeMessage(String.valueOf(seconds));
              if (seconds == 1)timeMessage(String.valueOf(seconds));
            }
          }
        },
        new Runnable() {
          @Override
          public void run() {
            //if countdown 0 run this
            new BukkitRunnable() {
              @Override
              public void run() {
                String permission = gameKey;
                for (Player targets : Bukkit.getOnlinePlayers()) {
                  if (targets.hasPermission(permission)) {
                    player.teleport(location);
                  }
                }
                removeGame();
              }
            }.runTask(BossFightCreator.main());
          }
        }
      );
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Send a message
   * @param seconds Requires the secounds
   */
  private void timeMessage(String seconds){
    CustomMessages message = new CustomMessages();
    String permission = gameKey;
    for (Player targets : Bukkit.getOnlinePlayers()) {
      if (targets.hasPermission(permission)) {
        message.messageWithPrefix(targets, message.gray("Du wirst in ").append(message.aqua(seconds).append(message.gray(" Sekunden zurück teleportiert!"))));
      }
    }
  }
}
