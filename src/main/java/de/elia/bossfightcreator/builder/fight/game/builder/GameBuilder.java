package de.elia.bossfightcreator.builder.fight.game.builder;

import de.elia.systemclasses.logging.PluginLogger.SaveError;
import de.elia.systemclasses.logging.debug.PluginDebbuger;
import de.elia.api.configuration.SoulConfiguration;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Files.CreateBossfightFile;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.builder.fight.game.Game;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.bossfightcreator.builder.save.Saver;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
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

  public final Saver.SaveGameBuilder saveGameBuilder;
  public final GameBuilder builder;
  private final PluginMessages message = Plugin.MESSAGES;
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
  public GameBuilder(final @NotNull Player gameOwner, final @NotNull Arenas arena, @NotNull org.bukkit.plugin.Plugin plugin) throws SoulBossSystemNullException {
    this.gameOwner = gameOwner;
    this.gameID = this.random.nextInt(1, 999999999);
    this.gameName = "bossfight" + this.gameID + arena.getName() + gameOwner.getName();
    this.gameFile = new CreateBossfightFile(this.gameName).getFile();
    this.arena = arena;
    this.plugin = plugin;
    builder = this;
    this.saveGameBuilder = new Saver.SaveGameBuilder(gameOwner.getUniqueId(), this);
    this.saveGameBuilder.addPlayer(gameOwner);
    PluginDebbuger.debug(this.saveGameBuilder);
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
  public GameBuilder(final @NotNull Player gameOwner, final @NotNull Arenas arena) throws SoulBossSystemNullException {
    this(gameOwner, arena, BossFightCreator.main());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Save all fight informations in a file and create a game permission with the {@link PermissionAttachment}
   */
  private void createFight() throws SoulBossSystemNullException {
    if (!new CheckVariable().check(gameOwner, "GameBuilder#createFight()") == true)return;
    if (!new CheckVariable().check(gameFile, "GameBuilder#createFight()") == true)return;
    if (!new CheckVariable().check(gameName, "GameBuilder#createFight()") == true)return;
    this.gameFile.addDefault("name", this.gameName);
    this.gameFile.addDefault("owner", this.gameOwner.getName());
    this.gameFile.addDefault("players", this.gameOwner.getName());
    new StartFightTimer().start(60*20, gameOwner, null);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Trusted a {@link Player} to this Game
   * @param target The target {@link Player}
   */
  public void trustPlayer(@NotNull Player target) throws SoulBossSystemNullException {
    if (!new CheckVariable().check(gameFile, "GameBuilder#trustPlayer(Player)") == true)return;
    if (!new CheckVariable().check(saveGameBuilder, "GameBuilder#trustPlayer(Player)") == true)return;
    this.gameFile.addDefault("players", target.getName());
    this.saveGameBuilder.addPlayer(target);
    PluginDebbuger.debug(this.saveGameBuilder);
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Teleport the Game{@link Player}s to the Arena
   */
  public void teleportToGame() throws SoulBossSystemNullException{
    if (!new CheckVariable().check(saveGameBuilder, "GameBuilder#teleportToGame()") == true)return;
    if (!new CheckVariable().check(arena, "GameBuilder#teleportToGame()") == true)return;
    if (!new CheckVariable().check(arena.location(), "GameBuilder#teleportToGame()") == true)return;
    saveGameBuilder.gamePlayers.forEach(player -> new BukkitRunnable() {
      @Override
      public void run() {
        PluginDebbuger.debug(arena.location().getWorld());
        player.teleport(arena.location());
        new SpawnZombieTimer().start(16*20, player, arena.location());
      }
    }.runTask(plugin));
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
     * @param player Requires a game owner ({@link Player})
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
              if (seconds == 60) {
                try {
                  gameMessage.startMessage();
                } catch (SoulBossSystemNullException exception) {
                  new SaveError().saveError(exception, "GameBuilder-StartFightTimer-start-line_189=null");
                  exception.stacktrace();
                }
              }
              if (seconds == 30) {
                try {
                  gameMessage.sendTrustConfirmation();
                } catch (SoulBossSystemNullException exception) {
                  new SaveError().saveError(exception, "GameBuilder-StartFightTimer-start-line_197=null");
                  exception.stacktrace();
                }
              }
            }
          }
        },
        new Runnable() {
          @Override
          public void run() {
            //if countdown 0 run this
            try {
              gameMessage.sendFinishMessage();
              teleportToGame();
            } catch (SoulBossSystemNullException exception) {
              new SaveError().saveError(exception, "GameBuilder-StartFightTimer-start-line_211-212=null");
              exception.stacktrace();
            }
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
              try {
                if (seconds == 15)gameMessage.bossSpawnMessage(seconds);
                if (seconds == 10)gameMessage.bossSpawnMessage(seconds);
                if (seconds == 5)gameMessage.bossSpawnMessage(seconds);
                if (seconds == 3)gameMessage.bossSpawnMessage(seconds);
                if (seconds == 2)gameMessage.bossSpawnMessage(seconds);
                if (seconds == 1)gameMessage.bossSpawnMessage(seconds);
              }catch (SoulBossSystemNullException exception) {
                new SaveError().saveError(exception, "GameBuilder-ZombieSpawnTimer-start-line_262-267=null");
                exception.stacktrace();
              }
            }
          }
        },
        new Runnable() {
          @Override
          public void run() {
            //if countdown 0 run this
            try {
              game = new Game(arena, player, gameName, gameBuilder());
            } catch (SoulBossSystemNullException exception) {
              new SaveError().saveError(exception, "GameBuilder-StartFightTimer-start-line_280=null");
              exception.stacktrace();
            }
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
    public void sendFinishMessage() throws SoulBossSystemNullException {
      if (!new CheckVariable().check(gameOwner, "GameBuilder#sendFinishMessage()") == true)return;
      message.messageWithPrefix(gameOwner, message.aqua("Bossfight fertig geladen!"));
      message.messageWithPrefix(gameOwner, message.aqua("Zeit bis zum spawnen: 20sec"));
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description Send the trust confirmation messages
     */
    public void sendTrustConfirmation() throws SoulBossSystemNullException {
      if (!new CheckVariable().check(gameOwner, "GameBuilder#sendTrustConfirmation()") == true)return;
      message.messageWithPrefix(gameOwner, message.aqua("Gib").append(message.green(" /trustfight [PLAYER_NAME] ")).append(message.aqua("ein um ein Spieler zu trusten fuer den Fight.")));
      message.messageWithPrefix(gameOwner, message.gray("Dauer: 30sec"));
    }

    /**
     * @author Elia
     * @version 1.0
     * @since 1.0
     * @description send the creation start messages
     */
    public void startMessage() throws SoulBossSystemNullException {
      if (!new CheckVariable().check(gameOwner, "GameBuilder#startMessage()") == true)return;
      if (!new CheckVariable().check(gameName, "GameBuilder#startMessaget()") == true)return;
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
    public void bossSpawnMessage(int time) throws SoulBossSystemNullException {
      if (!new CheckVariable().check(saveGameBuilder, "GameBuilder#bossSpawnMessage(int)") == true)return;
      String timeMessage = time + "sec";
      saveGameBuilder.gamePlayers.forEach(player -> message.messageWithPrefix(player, message.gray("Der Boss Spawnt in ").append(message.green(timeMessage))));
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

  private GameBuilder gameBuilder(){
    return this;
  }
}
