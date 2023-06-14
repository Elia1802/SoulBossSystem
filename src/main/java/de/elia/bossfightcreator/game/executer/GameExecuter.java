package de.elia.bossfightcreator.game.executer;

import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.arena.ArenaState;
import de.elia.bossfightcreator.game.Game;
import de.elia.achivements.achievement.process.AchievementClass;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link Listener}, {@link CommandExecutor}, {@link TabCompleter}-
 * @description This class is for the commands end events while the game.
 */
public class GameExecuter implements CommandExecutor, TabCompleter {

  private final PluginMessages message = new PluginMessages();
  private final Plugin plugin;
  private Game game;

  public GameExecuter(Plugin plugin) {
    this.plugin = plugin;
  }

  /**
   * @author Elia
   * @version 1.0.0.pre1
   * @since 1.0.0.pre1
   * @description This event checks if the player click the spawnegg and if this true load this a new {@link Game}
   */

  public void init(Player player){
    new AchievementClass(this.plugin).giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE);
    Optional<Arena> optionalArena = ArenaHandler.getFreeArena();
    if(optionalArena.isPresent()) {
      Arena arena = optionalArena.get();
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena Information:");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena: " + arena);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena schematic: " + arena.getSchematicName());
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena ID: " + arena.getArenaID());
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena spawn location: " + arena.getSpawnLocation());
      arena.setState(ArenaState.USED);
      arena.reBuildArena(arena);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena " + arena.getArenaID() + " is " + arena.getState() + "!");
      this.game = new Game(arena, player);
      arena.setGame(this.game);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Game of " + arena.getArenaID() + " is " + game);
      arena.addPlayer(player);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("A new GameExecuter was created for " + player.getName());
      return;
    }else {
      message.messageWithPrefix(player, message.red("Zur Zeit ist keine Arena verfügbar!"));//Inform the player no arena is free
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logWarning("There was a problem by creating a new GameExecuter");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logError("Problem: NO ARENAS AVIABLE");
    }
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (this.game == null)return false;
    if (sender instanceof Player owner) {
      if (args.length == 1){
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          this.game.addPlayer(target);
        }
      }
    }
    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    if (args.length == 1) {
      List<String> players = new ArrayList<>();
      for (Player player : Bukkit.getOnlinePlayers()) {
        players.add(player.getName());
      }
      return players;
    }

    return null;
  }
}
