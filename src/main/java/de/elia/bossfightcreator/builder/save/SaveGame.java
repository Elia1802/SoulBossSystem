package de.elia.bossfightcreator.builder.save;


import de.elia.bossfightcreator.builder.fight.game.Game;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static de.elia.bossfightcreator.BossFightCreator.bossFightCreator;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This class saved the games in a {@link Map}
 */
public class SaveGame {

  private static Map<UUID, Game> saveGame;//the map

  private final UUID player;
  private final Game game;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load the map
   */
  public static void loadMap(){
    saveGame = new HashMap<>();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Save a new {@link Game} to a player uniqueID ({@link UUID})
   * @param player Requires the {@link Player#getUniqueId()}
   * @param game Requires the {@link Game}
   */
  public SaveGame(UUID player, Game game){
    this.player = player;
    this.game = game;
    if (saveGame == null) {
      bossFightCreator().bossFightCreatorLogger().logError("LOAD THE MAP WITH de.elia.bossfightcreator.builder.save.SaveGame#loadMap()");
      return;
    }
    saveGame.put(player, game);
    bossFightCreator().bossFightCreatorLogger().logInfo("A new game is saved!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the {@link Map}
   * @return {@link Map<UUID, Game>}
   */
  public static Map<UUID, Game> getSaveGame() {
    return saveGame;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Removed the game of the player
   * @param player Requires the {@link Player#getUniqueId()}
   */
  public void removeGame(UUID player){
    if (saveGame == null) {
      bossFightCreator().bossFightCreatorLogger().logError("LOAD THE MAP WITH de.elia.bossfightcreator.builder.save.SaveGame#loadMap()");
      return;
    }
    saveGame.remove(player);
    bossFightCreator().bossFightCreatorLogger().logInfo("the game" + game + "is removed!");
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description checks if a specify game in the {@link Map}
   * @param game Requires a{@link Game}
   * @return {@link Boolean}
   */
  public boolean containsGame(Game game){
    return saveGame.containsValue(game);
  }
}
