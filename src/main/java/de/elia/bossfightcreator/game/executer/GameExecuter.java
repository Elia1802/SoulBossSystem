package de.elia.bossfightcreator.game.executer;

import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.api.annotation.AnnotationChecker;
import de.elia.api.annotation.Beta;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.game.Game;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Elia
 * @description This class create a new {@link Game} for the {@link Player} and load a free {@link Arena}.
 * @beta There may still be errors loading the arena.
 */
@Beta
public class GameExecuter {

  private final PluginMessages message = new PluginMessages();
  private Game game;

  public GameExecuter() {
    AnnotationChecker.processAnnotations(GameExecuter.class);
  }

  /**
   * @description This methode create a new {@link Game} for the {@link Player}.
   * @param player Requires the {@link Player}
   */
  public void init(@NotNull Player player) {
    new Achievement().giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE);
    Optional<Arena> optionalArena = ArenaHandler.getFreeArena();
    if (optionalArena.isPresent()) {
      Arena arena = optionalArena.get();
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena Information:");
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena: " + arena);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena schematic: " + arena.getSchematicName());
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena ID: " + arena.getArenaID());
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena spawn location: " + arena.getSpawnLocation());
      arena.reBuildArena(arena);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena " + arena.getArenaID() + " is " + arena.getState() + "!");
      this.game = new Game(arena, player);
      arena.setGame(this.game);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Game of " + arena.getArenaID() + " is " + this.game);
      arena.addPlayer(player);
      BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("A new GameExecuter was created for " + player.getName());
      return;
    }
    this.message.messageWithPrefix(player, this.message.red("Zur Zeit ist keine Arena verfügbar!"));
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logWarning("There was a problem by creating a new GameExecuter");
    BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logError("Problem: NO ARENAS AVIABLE");
  }
}

