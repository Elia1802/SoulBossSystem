package de.elia.bossfightcreator.game;

import de.elia.api.drop.DropUtils;
import de.elia.api.timing.StartTasks;
import de.elia.api.timing.TimerUtils;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;

import net.minecraft.world.damagesource.DamageSource;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Bossfight (Game) of a {@link Player}
 */
public class Game {

  private final PluginMessages message = new PluginMessages();
  private final Arena arena;
  public final ArrayList<Player> players;
  private final @NotNull Location spawnLocation;
  private boolean isGameRunning = false; // is game running

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game (BossFight)
   * @param arena Requires the {@link Arena} to get the gameArena
   * @param gameOwner Requires the {@link Player}
   */
  public Game(@NotNull final Arena arena, @NotNull final Player gameOwner) {
    this.arena = arena;
    this.players = new ArrayList<>();
    this.spawnLocation = arena.getSpawnLocation();
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start Timer GameStartTimer");
    new GameStartTimer().start(120*20, gameOwner, this.spawnLocation);
  }

  public void startGame() {
    new BukkitRunnable() {
      @Override
      public void run() {
        ZombieBoss boss = new ZombieBoss(Game.this.spawnLocation, "ZombieLol") {
          @Override
          public void die(@NotNull DamageSource damageSource) {
            DropUtils.drop(this.getBukkitCreature().getLocation());
            Bukkit.getWorld("world_bossfight").strikeLightningEffect(this.getBukkitCreature().getLocation());
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Boss " + this + this.getName() + "is death!");
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start the GameEndTimer!");
            Game.this.players.forEach(gamePlayer -> new GameEndTimer().start(60*20, gamePlayer, Bukkit.getWorld("world").getSpawnLocation()));
            super.die(damageSource);
          }
        };
        BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game was started!");
      }
    }.runTask(BossFightCreatorMain.bossFightCreator().main());
  }

  public void addPlayer(Player player) {
    this.players.add(player);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description start the end timer
   */
  public void end(Location location){
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Teleport all game players in the World!");
    players.forEach(player -> {
      player.teleport(location);
      BossFightCreatorMain.playerStatusMap().replace(player, 0);
    });
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Rebuild the arena " + arena.getArenaID());
    this.arena.reBuildArena(this.arena);
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Reset the status of the Player!");
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game sucessful end!");
  }

  public class GameStartTimer extends StartTasks {

    private void timerMessage(int seconds){
      String string = String.valueOf(seconds);
      players.forEach(target ->  message.messageWithPrefix(target, message.gray("Der Bossfight startet in ").append(message.aqua(string).append(message.gray(" Sekunden!")))));
    }

    @Override
    public void start(int time, @NotNull Player player, Location location) {
      TimerUtils.countdownAndRun(time, new Runnable() {
        public void run() {
        }
      });
      TimerUtils.countdownInterval(time, new TimerUtils.TimeRunnable() {
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks / 20;
            if (seconds == 120) {
              message.messageWithPrefix(player,
                message.gray("Dein Bossfight startet! Mit")
                  .append(message.aqua(" /bossfightcreatoraddplayer [PLAYER] "))
                  .append(message.gray("kannst du weitere Spieler hinzufügen."))
              );
            }
            if (seconds == 100)timerMessage(seconds);
            if (seconds == 80)timerMessage(seconds);
            if (seconds == 60)timerMessage(seconds);
            if (seconds == 40)timerMessage(seconds);
            if (seconds == 30)timerMessage(seconds);
            if (seconds == 20)timerMessage(seconds);
            if (seconds == 15)timerMessage(seconds);
            if (seconds == 10) {
              timerMessage(seconds);
              new BukkitRunnable() {
                @Override
                public void run() {
                  BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Teleport all Players in the Arena.");
                  Game.this.message.messageWithPrefix(player, Game.this.message.gray("Du und dein Team wurden in die Arena " + arena.getArenaID() + " teleportiert!"));
                  Game.this.players.forEach(gamePlayer -> gamePlayer.teleport(location));
                }
              }.runTask(BossFightCreatorMain.bossFightCreator().main());
            }
            if (seconds == 5) timerMessage(seconds);
            if (seconds == 4)timerMessage(seconds);
            if (seconds == 3)timerMessage(seconds);
            if (seconds == 2)timerMessage(seconds);
            if (seconds == 1)timerMessage(seconds);
          }
        }
      }, new Runnable() {
        public void run() {
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("GameStartTimer was end!");
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start the Game...");
          startGame();
        }
      });
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
     * @description Send a message
     * @param seconds Requires the secounds
     */
    private void timeMessage(String seconds){
      players.forEach(target ->  message.messageWithPrefix(target, message.gray("Du wirst in ").append(message.aqua(seconds).append(message.gray(" Sekunden zurück teleportiert!")))));
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
    public void start(int time, @NotNull Player player, Location location){
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
                BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("End the Game...");
                Game.this.end(location);
              }
            }.runTask(BossFightCreatorMain.bossFightCreator().main());
          }
        }
      );
    }
  }
}
