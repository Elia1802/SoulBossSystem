package de.elia.bossfightcreator.builder.fight.game.builder;

import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Files.CreateBossfightFile;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.builder.fight.game.Game;
import de.elia.CustomMessages;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
import de.elia.soulmain.thisplugin.configuration.SoulConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.Integer;
import java.lang.Runnable;
import java.lang.String;
import java.util.Random;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description Create a Game
 */
public class GameBuilder {

  private final CustomMessages message = Plugin.MESSAGES;
  private final GameMessages gameMessage = new GameMessages();
  private final Random random = new Random(); //to generate a ID
  private final SoulConfiguration gameFile; //to save game information
  private final Player gameOwner; //the game creator
  private final String gameName; //the Name is for the permission and the gameFile
  private final Integer gameID; // the generated id
  private final Arenas arena; //the game location of the game arena
  private final org.bukkit.plugin.Plugin plugin;
  private PermissionAttachment trustGamePermission;
  private Game game;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game
   * @param gameOwner Requires a {@link Player}
   * @param arena Requires a {@link Arenas}
   * @param plugin Requires a instance of the main Main class
   */
  public GameBuilder(final @NotNull Player gameOwner, final @NotNull Arenas arena, @NotNull org.bukkit.plugin.Plugin plugin){
    this.gameOwner = gameOwner;
    this.gameID = this.random.nextInt(1, 999999999);
    this.gameName = "bossfight" + this.gameID + arena.getName() + gameOwner.getName();
    this.gameFile = new CreateBossfightFile(this.gameName).getFile();
    this.plugin = plugin;
    this.arena = arena;
    this.createFight();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game
   * @param gameOwner Requires a {@link Player}
   * @param arena Requires a {@link Arenas}
   */
  public GameBuilder(final @NotNull Player gameOwner, final @NotNull Arenas arena) {
    this(gameOwner, arena, BossFightCreator.main());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Save all fight informations in a file and create a game permission with the {@link PermissionAttachment}
   */
  private void createFight(){
    this.gameFile.addDefault("name", this.gameName);
    this.gameFile.addDefault("permission", this.gameName);
    this.gameFile.addDefault("owner", this.gameOwner.getName());
    this.gameFile.addDefault("players", this.gameOwner.getName());
    this.trustGamePermission = this.gameOwner.addAttachment(this.plugin);
    this.trustGamePermission.setPermission(this.gameName, true);
    new StartFightTimer().start(60*20, gameOwner, null);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Trusted a {@link Player} to this Game
   * @param target The target {@link Player}
   */
  public void trustPlayer(@NotNull Player target){
    this.trustGamePermission = target.addAttachment(this.plugin);
    this.trustGamePermission.setPermission(this.gameName, true);
    this.gameFile.addDefault("players", target.getName());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Teleport the Game{@link Player}s to the Arena
   */
  public void teleportToGame(){
    for (Player gamePlayer : Bukkit.getOnlinePlayers()) {
      if (gamePlayer.hasPermission(this.gameName)) {
        new BukkitRunnable() {
          @Override
          public void run() {
            gamePlayer.teleport(arena.location());
            new SpawnZombieTimer().start(16*20, gamePlayer, arena.location());
          }
        }.runTask(plugin);
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the gameOwner
   * @return {@link Player}
   */
  @NotNull
  public Player gameOwner(){
    return this.gameOwner;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @extends {@link StartTasks}
   * @description Create a countdown for the creation to the game
   */
  private class StartFightTimer extends StartTasks {
    public StartFightTimer(){
      //...
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @overridesOf {@link StartTasks}
     * @description Create a countdown for the creation to the game
     * @param time Requires the seconds
     * @param player Requires a {@link Player}
     * @param location Requires a {@link Location} (Not used in this runnable)
     */
    @Override
    public void start(int time, Player player, Location location){
      TimerUtils.countdownAndRun(time,
        new Runnable() {
          @Override
          public void run() {
            //...
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
              if (seconds == 60)gameMessage.startMessage();
              if (seconds == 30)gameMessage.sendTrustConfirmation();
            }
          }
        },
        new Runnable() {
          @Override
          public void run() {
            //if countdown 0 run this
            gameMessage.sendFinishMessage();
            teleportToGame();
          }
        }
      );
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a countdown for the spawn of the {@link ZombieBoss}
   */
  private class SpawnZombieTimer extends StartTasks {

    public SpawnZombieTimer(){
      //...
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description
     * @param time Requires the seconds
     * @param player Requires all {@link Player}s of this Game
     * @param location Requires a {@link Location}
     */
    @Override
    public void start(int time, Player player, Location location){
      TimerUtils.countdownAndRun(time,
        new Runnable() {
          @Override
          public void run() {
            //...
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
              if (seconds == 15)gameMessage.bossSpawnMessage(seconds);
              if (seconds == 10)gameMessage.bossSpawnMessage(seconds);
              if (seconds == 5)gameMessage.bossSpawnMessage(seconds);
              if (seconds == 3)gameMessage.bossSpawnMessage(seconds);
              if (seconds == 2)gameMessage.bossSpawnMessage(seconds);
              if (seconds == 1)gameMessage.bossSpawnMessage(seconds);
            }
          }
        },
        new Runnable() {
          @Override
          public void run() {
            //if countdown 0 run this
            game = new Game(arena, player, gameName);
          }
        }
      );
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description All Game Messages for the Game
   */
  private class GameMessages {
    public GameMessages(){
      //...
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Send the Finished Messages
     */
    public void sendFinishMessage(){
      message.messageWithPrefix(gameOwner, message.aqua("Bossfight fertig geladen!"));
      message.messageWithPrefix(gameOwner, message.aqua("Zeit bis zum spawnen: 20sec"));
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Send the trust confirmation messages
     */
    public void sendTrustConfirmation() {
      message.messageWithPrefix(gameOwner, message.aqua("Gib").append(message.green(" /trustfight [PLAYER_NAME] ")).append(message.aqua("ein um ein Spieler zu trusten fuer den Fight.")));
      message.messageWithPrefix(gameOwner, message.gray("Dauer: 30sec"));
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description send the creation start messages
     */
    public void startMessage(){
      var log = BossFightCreator.bossFightCreator().bossFightCreatorLogger();
      message.message(gameOwner, message.aqua("Lade dein Bossfight... ").append(message.gray("(Dauer: 1min)")));
      log.logInfo("Lade ein neuen Bossfight (" + gameName + ") von " + gameOwner.getName() + "...");
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Send the boss spawn Messages
     * @param time Requires the time
     */
    public void bossSpawnMessage(int time){
      String timeMessage = time + "sec";
      for (Player gamePlayer : Bukkit.getOnlinePlayers()) {
        if (gamePlayer.hasPermission(gameName)) {
          message.messageWithPrefix(gamePlayer, message.gray("Der Boss Spawnt in ").append(message.green(timeMessage)));
        }
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the game
   * @return {@link Game}
   */
  @NotNull
  public Game game() {
    return game;
  }
}
