package de.elia.bossfightcreator.game;

import de.elia.api.timing.StartTasks;
import de.elia.api.timing.TimerUtils;
import de.elia.api.timing.TimerUtils.TimeRunnable;

import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.party.Party;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;
import de.elia.systemclasses.messages.PluginMessages;

import net.minecraft.world.damagesource.DamageSource;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;

/**
 * @description This class is the game for the bosses.
 */
public class Game implements Listener {

  private final Arena arena;
  private final Player gameOwner;
  private final Plugin plugin;
  private final String bossName;
  private final Location spawnLocation;
  public final Party party;
  private ZombieBoss boss;
  private boolean isBossDie = false;
  private final PluginMessages message = new PluginMessages();

  /**
   * @description This create a new game.
   * @param arena Requires the {@link Arena} for the game.
   * @param gameOwner Requires the game owner ({@link Player}) for the game.
   * @param plugin Requires a instance of the system main class.
   */
  public Game(@NotNull Arena arena, @NotNull Player gameOwner, Plugin plugin, @NotNull Party party) {
    this.arena = arena;
    this.gameOwner = gameOwner;
    this.plugin = plugin;
    this.spawnLocation = arena.getSpawnLocation();
    this.bossName = "boss_" + arena.getArenaID() + "_" + gameOwner.getUniqueId();
    this.party = party;
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start Timer GameStartTimer");
    new GameStartTimer().start(121*20, gameOwner, this.spawnLocation, plugin);
    Bukkit.getPluginManager().registerEvents(this, plugin);
  }

  /**
   * @description This start the game.
   */
  public void startGame() {
    new BukkitRunnable(){
      @Override
      public void run() {
        Game.this.boss = new ZombieBoss(Game.this.spawnLocation, Game.this.bossName){
          @Override
          public void die(@NotNull DamageSource damageSource) {
            Bukkit.getWorld("world_bossfight").strikeLightningEffect(this.getBukkitCreature().getLocation());
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Boss " + this + this.getName() + "is death!");
            BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Start the GameEndTimer!");
            Game.this.party.members().forEach(player -> new Achievement().giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE_END));
            Game.this.party.members().forEach(gamePlayer -> new GameEndTimer().start(60*20, gamePlayer, Bukkit.getWorld("world").getSpawnLocation(), Game.this.plugin));
            super.die(damageSource);
          }
        };
        BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game was started!");
      }
    }.runTask(this.plugin);
  }

  /**
   * @description This end the game
   * @param location Requires a {@link Location} for teleporting the {@link Player}s from the arena.
   */
  public void end(Location location) {
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Teleport all game players in the World!");
    this.party.members().forEach(player -> {
      player.teleport(location);
      BossFightCreatorMain.playerStatusMap().replace(player, 0);
    });
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Rebuild the arena " + this.arena.getArenaID());
    this.arena.reBuildArena(this.arena);
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Reset the status of the Player!");
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Game sucessful end!");
  }

  /**
   * @description Gets the {@link Arena} of this game.
   * @return the {@link Arena} of this game.
   */
  @NotNull
  public Arena getArena() {
    return this.arena;
  }

  /**
   * @description Gets the {@link ZombieBoss} of this game.
   * @return the {@link ZombieBoss} of this game.
   */
  @NotNull
  public ZombieBoss getBoss() {
    return this.boss;
  }

  /**
   * @description This event end this game if the game owner disconnect.
   */
  @EventHandler
  private void onPlayerQuitServer(@NotNull PlayerQuitEvent event) {
    Player player = event.getPlayer();
    this.party.removePlayer(player);
    if (player.getUniqueId() == this.gameOwner.getUniqueId()) {
      this.party.members().remove(this.gameOwner);
      this.party.members().forEach(gamePlayer -> {
        this.message.message(gamePlayer, this.message.darkRed("DER OWNER VON DIESEM SPIEL HAT DAS SPIEL VERLASSEN! ES WIRD BEENDET!"));
        new GameEndTimer().start(60*20, gamePlayer, Bukkit.getWorld("world").getSpawnLocation(), this.plugin);
      });
    }
  }

  private Location deathLocation;

  /**
   * @description This event save the location where the game owner die.
   */
  @EventHandler
  private void onPlayerDie(@NotNull PlayerDeathEvent event) {
    Player player = event.getPlayer();
    if (player.getUniqueId() == this.gameOwner.getUniqueId()) {
      deathLocation = player.getLocation().clone();
    }
  }

  /**
   * @description This event teleport the game owner to the death location.
   */
  @EventHandler
  private void onPlayerRespawn(@NotNull PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    if (player.getUniqueId() == this.gameOwner.getUniqueId()) {
      player.teleport(deathLocation);
    }
  }

  /**
   * This class create a timer for the start.
   */
  public class GameStartTimer extends StartTasks {

    private void timerMessage(int seconds) {
      String string = String.valueOf(seconds);
      Game.this.party.members().forEach(target -> Game.this.message.messageWithPrefix(target, Game.this.message.gray("Der Bossfight startet in ").append(Game.this.message.aqua(string).append(Game.this.message.gray(" Sekunden!")))));
    }

    @Override
    public void start(int time, final @NotNull Player player, final Location location, final Plugin plugin) {
      TimerUtils.countdownAndRun(time, new Runnable(){
        @Override
        public void run() {
          //...
        }
      }, plugin);
      TimerUtils.countdownInterval(time, new TimeRunnable(){
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
                  Game.this.party.members().forEach(gamePlayer -> gamePlayer.teleport(location));
                }
              }.runTask(plugin);
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
      }, plugin);
    }
  }

  /**
   * @description This class create a timer for the end of the game.
   */
  public class GameEndTimer extends StartTasks {

    private void timerMessage(int seconds, Player target) {
      String string = String.valueOf(seconds);
      Game.this.message.messageWithPrefix(target, Game.this.message.gray("Du wirst in ").append(Game.this.message.aqua(string).append(Game.this.message.gray(" Sekunden zurück teleportiert!"))));
    }
    @Override
    public void start(int time, @NotNull Player player, final Location location, final Plugin plugin) {
      TimerUtils.countdownAndRun(time, new Runnable(){

        @Override
        public void run() {
          if (Game.this.boss == null) {
            return;
          }
          if (!Game.this.isBossDie) {
            Game.this.boss.kill();
          }
        }
      }, plugin);
      TimerUtils.countdownInterval(time, new TimeRunnable(){
        @Override
        public void run(int ticks) {
          if (ticks % 20 == 0) {
            int seconds = ticks / 20;
            if (seconds == 60) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 30) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 20) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 10) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 5) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 3) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 2) {
              GameEndTimer.this.timerMessage(seconds, player);
            }
            if (seconds == 1) {
              GameEndTimer.this.timerMessage(seconds, player);
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
          }.runTask(plugin);
        }
      }, plugin);
    }
  }
}
