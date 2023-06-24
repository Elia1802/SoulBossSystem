package de.elia.bossfightcreator.game;

import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.api.timing.StartTasks;
import de.elia.api.timing.TimerUtils;
import de.elia.api.timing.TimerUtils.TimeRunnable;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.entity.mobs.drop.DropUtils;
import de.elia.systemclasses.messages.PluginMessages;
import net.minecraft.world.damagesource.DamageSource;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Elia
 * @description This is the class for a game of the {@link Player}. This class execute all function before, after and during the game.
 * @beta There may still be errors loading the arena.
 */
@Beta
public class Game implements Listener {

  private final PluginMessages message = new PluginMessages();
  private final String bossName;
  private final Arena arena;
  private ZombieBoss boss;
  public final ArrayList<Player> players;
  private final Location spawnLocation;
  private boolean isGameRunning = false;
  private boolean isBossDie = false;
  private final Player gameOwner;

  /**
   * @description This constructor create a new game
   * @param arena Requires the {@link Arena} of this Game
   * @param gameOwner Requires a game owner ({@link Player}) for this game.
   */
  public Game(@NotNull Arena arena, @NotNull Player gameOwner) {
    this.arena = arena;
    this.players = new ArrayList();
    this.spawnLocation = arena.getSpawnLocation();
    this.bossName = "boss_" + arena.getArenaID() + "_" + gameOwner.getUniqueId();
    this.gameOwner = gameOwner;
    AnnotationChecker.processAnnotations(Game.class);
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start Timer GameStartTimer");
    new GameStartTimer().start(2400, gameOwner, this.spawnLocation);
    Bukkit.getPluginManager().registerEvents(this, BossFightCreatorMain.bossFightCreator().main());
  }

  /**
   * @description This methode start the game
   */
  public void startGame() {
    new BukkitRunnable(){
      @Override
      public void run() {
        boss = new ZombieBoss(Game.this.spawnLocation, Game.this.bossName){
          /**
           * @description I modified the death of the boss.
           * @param damageSource Requires the source of the damgage
           */
          @Override
          public void die(@NotNull DamageSource damageSource) {
            DropUtils.drop(this.getBukkitCreature().getLocation());
            Bukkit.getWorld((String)"world_bossfight").strikeLightningEffect(this.getBukkitCreature().getLocation());
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Boss " + this + this.getName() + "is death!");
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start the GameEndTimer!");
            Game.this.players.forEach(player -> new Achievement().giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE_END));
            Game.this.players.forEach(gamePlayer -> new GameEndTimer().start(1200, gamePlayer, Bukkit.getWorld("world").getSpawnLocation()));
            super.die(damageSource);
          }
        };
        BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game was started!");
      }
    }.runTask(BossFightCreatorMain.bossFightCreator().main());
  }

  /**
   * @description This methode add a {@link Player} to the game.
   * @param player Requires the {@link Player}.
   */
  public void addPlayer(Player player) {
    this.players.add(player);
  }

  /**
   * This methode end the game.
   * @param location Requires the {@link Location} to teleport the {@link Player}s in the normal world.
   */
  public void end(Location location) {
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Teleport all game players in the World!");
    this.players.forEach(player -> {
      player.teleport(location);
      BossFightCreatorMain.playerStatusMap().replace(player, 0);
    });
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Rebuild the arena " + this.arena.getArenaID());
    this.arena.reBuildArena(this.arena);
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Reset the status of the Player!");
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game sucessful end!");
  }

  /**
   * @author Elia
   * @description In this class I create a start timer for the game.
   * @extends {@link StartTasks}
   */
  public class GameStartTimer extends StartTasks {

    private void timerMessage(int seconds) {
      String string = String.valueOf(seconds);
      Game.this.players.forEach(target -> Game.this.message.messageWithPrefix(target, Game.this.message.gray("Der Bossfight startet in ").append(Game.this.message.aqua(string).append(Game.this.message.gray(" Sekunden!")))));
    }

    @Override
    public void start(int time, final @NotNull Player player, final Location location) {
      TimerUtils.countdownAndRun(time, new Runnable(){
        @Override
        public void run() {
        }
      });
      TimerUtils.countdownInterval((int)time, new TimeRunnable(){
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks / 20;
            if (seconds == 120) {
              Game.this.message.messageWithPrefix(player, Game.this.message.gray("Dein Bossfight startet!"));
            }
            if (seconds == 100) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 80) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 60) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 40) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 30) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 20) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 15) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 10) {
              GameStartTimer.this.timerMessage(seconds);
              new BukkitRunnable(){
                @Override
                public void run() {
                  BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Teleport all Players in the Arena.");
                  Game.this.message.messageWithPrefix(player, Game.this.message.gray("Du und dein Team wurden in die Arena " + Game.this.arena.getArenaID() + " teleportiert!"));
                  Game.this.players.forEach(gamePlayer -> gamePlayer.teleport(location));
                }
              }.runTask(BossFightCreatorMain.bossFightCreator().main());
            }
            if (seconds == 5) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 4) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 3) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 2) {
              GameStartTimer.this.timerMessage(seconds);
            }
            if (seconds == 1) {
              GameStartTimer.this.timerMessage(seconds);
            }
          }
        }
      }, new Runnable(){
        @Override
        public void run() {
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("GameStartTimer was end!");
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start the Game...");
          Game.this.startGame();
        }
      });
    }
  }

  /**
   * @author Elia
   * @description In this class I create a end timer for the game.
   * @extends {@link StartTasks}
   */
  public class GameEndTimer extends StartTasks {

    private void timeMessage(String seconds) {
      Game.this.players.forEach(target -> Game.this.message.messageWithPrefix(target, Game.this.message.gray("Du wirst in ").append(Game.this.message.aqua(seconds).append(Game.this.message.gray(" Sekunden zurück teleportiert!")))));
    }

    @Override
    public void start(int time, @NotNull Player player, final Location location) {
      TimerUtils.countdownAndRun(time, new Runnable(){
        @Override
        public void run() {
          if (boss == null)return;
          if (!isBossDie) {
            boss.kill();
          }
        }
      });
      TimerUtils.countdownInterval(time, new TimeRunnable(){
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks / 20;
            if (seconds == 60) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 30) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 20) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 10) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 5) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 3) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 2) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
            if (seconds == 1) {
              GameEndTimer.this.timeMessage(String.valueOf(seconds));
            }
          }
        }
      }, new Runnable(){
        @Override
        public void run() {
          new BukkitRunnable(){
            @Override
            public void run() {
              BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("End the Game...");
              Game.this.end(location);
            }
          }.runTask(BossFightCreatorMain.bossFightCreator().main());
        }
      });
    }
  }

  /**
   * @description This event end this game if the game owner leave the server.
   */
  @EventHandler
  private void onPlayerQuitServer(@NotNull PlayerQuitEvent event) {
    Player player = event.getPlayer();
    if (player.getUniqueId() == this.gameOwner.getUniqueId()) {
      this.players.remove(this.gameOwner);
      this.players.forEach(gamePlayer -> {
        this.message.message(gamePlayer, this.message.darkRed("DER OWNER VON DIESEM SPIEL HAT DAS SPIEL VERLASSEN! ES WIRD BEENDET!"));
        new GameEndTimer().start(1200, gamePlayer, Bukkit.getWorld("world").getSpawnLocation());
      });
    }
  }
}
