package de.elia.bossfightcreator.builder.fight.game;

import com.sk89q.worldedit.WorldEditException;
import de.elia.systemclasses.logging.PluginLogger.SaveError;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemSpawnException;
import de.elia.systemclasses.messages.PluginMessages;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.arena.Arenas;
import de.elia.bossfightcreator.arena.sender.ArenaSender;
import de.elia.bossfightcreator.builder.fight.game.builder.GameBuilder;
import de.elia.bossfightcreator.builder.fight.game.maps.GameList;
import de.elia.bossfightcreator.builder.save.Saver;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.soulboss.utils.timers.StartTasks;
import de.elia.soulboss.utils.timers.TimerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description The Bossfight (Game) of a {@link Player}
 */
public class Game {

  private final ArenaSender sender = new ArenaSender();
  private final Arenas arena;
  public final Player player;
  private final String gameKey;
  private final GameBuilder gameBuilder;
  private Saver.SaveGame saveGame;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create a Game (BossFight)
   * @param arena Requires the {@link Arenas} to get the gameArena
   * @param player Requires the {@link Player}
   * @param gameKey Requires a key
   */
  public Game(@NotNull Arenas arena, @NotNull Player player, String gameKey, GameBuilder gameBuilder) throws SoulBossSystemNullException {
    this.arena = arena;
    this.player = player;
    this.gameKey = gameKey;
    this.gameBuilder = gameBuilder;
    if (player == null)return;
    this.saveGame = new Saver.SaveGame(player.getUniqueId(), this);
    new BukkitRunnable() {
      @Override
      public void run() {
        try {
          new ZombieBoss(arena.location());
        } catch (SoulBossSystemSpawnException exception) {
          new SaveError().saveError(exception, "Game-Game-line_66=spawn");
          exception.stacktrace();
        }catch (SoulBossSystemNullException exception) {
          new SaveError().saveError(exception, "Game-Game-line_66=null");
          exception.stacktrace();
        }
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
  public void killThis(@NotNull Player player) {
    Collection<LivingEntity> entities = player.getLocation().getNearbyLivingEntities(5);
    for (Entity entity : entities){
      if (entity.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
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
  public void killGame() throws SoulBossSystemNullException, IOException, WorldEditException {
    if (!new CheckVariable().check(saveGame, "Game#killGame()") == true)return;
    if (!new CheckVariable().check(player, "Game#killGame()") == true)return;
    if (!new CheckVariable().check(gameBuilder, "Game#killGame()") == true)return;
    if (!new CheckVariable().check(sender, "Game#killGame()") == true)return;
    if (!new CheckVariable().check(arena, "Game#killGame()") == true)return;
    if (saveGame.containsGame(this)) {
      this.killThis(player);
      gameBuilder.saveGameBuilder.gamePlayers.forEach(player -> player.teleport(Bukkit.getWorld("world").getSpawnLocation()));
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
  public void end() throws SoulBossSystemNullException {
    if (!new CheckVariable().check(gameBuilder, "Game#end()") == true)return;
    gameBuilder.saveGameBuilder.gamePlayers.forEach(player -> new GameEndTimer()
      .start(60*20, player, Bukkit.getWorld("world").getSpawnLocation()));
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Removed this game of all Lists maps and other thinks and reset the arena
   */
  public void removeGame(Location location) throws SoulBossSystemNullException, IOException, WorldEditException {
    if (!new CheckVariable().check(saveGame, "Game#removeGame(Location)") == true)return;
    if (!new CheckVariable().check(player, "Game#removeGame(Location)") == true)return;
    if (!new CheckVariable().check(gameBuilder, "Game#removeGame(Location)") == true)return;
    if (!new CheckVariable().check(sender, "Game#removeGame(Location)") == true)return;
    if (!new CheckVariable().check(arena, "Game#removeGame(Location)") == true)return;
    if (saveGame.containsGame(this)) {
      gameBuilder.saveGameBuilder.gamePlayers.forEach(player -> player.teleport(location));
      sender.resetArena(arena);
      saveGame.removeGame(player.getUniqueId());
      Saver.saveGameBuilder.remove(player.getUniqueId());
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
                try {
                  removeGame(location);
                } catch (SoulBossSystemNullException exception) {
                  new SaveError().saveError(exception, "Game-GameEndTimer-line_206=null");
                  exception.stacktrace();
                } catch (IOException | WorldEditException exception) {
                  new SaveError().saveError(exception, "Game-GameEndTimer-line_206=worldedit_or_io");
                  exception.printStackTrace();
                }
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
    PluginMessages message = new PluginMessages();
    String permission = gameKey;
    gameBuilder.saveGameBuilder.gamePlayers.forEach(player -> message.messageWithPrefix(player, message.gray("Du wirst in ").append(message.aqua(seconds).append(message.gray(" Sekunden zurück teleportiert!")))));
  }
}
