package de.elia.bossfightcreator.builder.save;

import de.elia.bossfightcreator.builder.fight.game.Game;
import de.elia.bossfightcreator.builder.fight.game.builder.GameBuilder;
import org.bukkit.entity.Player;

import java.util.*;

import static de.elia.bossfightcreator.BossFightCreator.bossFightCreator;

public class Saver {

  public static Map<UUID, Game> saveGame;//the map
  public static Map<UUID, GameBuilder> saveGameBuilder;//the map

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This class saved the games in a {@link Map}
   */
  public static class SaveGame {

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

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This class saved the games in a {@link Map}
   */
  public static class SaveGameBuilder {
    private final UUID player;
    private final GameBuilder gameBuilder;
    public final List<Player> gamePlayers = new ArrayList<>();

    public void addPlayer(Player player) {
      gamePlayers.add(player);
    }

    public void removePlayer(Player player) {
      gamePlayers.remove(player);
    }

    public void removeAllPlayers() {
      gamePlayers.clear();
    }

    /**
     * @author Elia
     * @version 1.0
     * @description Load the map
     * @since 1.0
     */
    public static void loadMap() {
      saveGameBuilder = new HashMap<>();
    }

    /**
     * @param player      Requires the {@link Player#getUniqueId()}
     * @param gameBuilder Requires the {@link GameBuilder}
     *
     * @author Elia
     * @version 1.0
     * @description Save a new {@link GameBuilder} to a player uniqueID ({@link UUID})
     * @since 1.0
     */
    public SaveGameBuilder(UUID player, GameBuilder gameBuilder) {
      this.player = player;
      this.gameBuilder = gameBuilder;
      if (saveGameBuilder == null) {
        bossFightCreator().bossFightCreatorLogger().logError("LOAD THE MAP WITH de.elia.bossfightcreator.builder.save.SaveGameBuilder#loadMap()");
        return;
      }
      saveGameBuilder.put(player, gameBuilder);
      bossFightCreator().bossFightCreatorLogger().logInfo("A new gameBuilder builder is saved!");
    }

    /**
     * @return {@link Map<UUID, GameBuilder>}
     *
     * @author Elia
     * @version 1.0
     * @description Gets the {@link Map}
     * @since 1.0
     */
    public static Map<UUID, GameBuilder> getSaveGameBilder() {
      return saveGameBuilder;
    }

    /**
     * @param player Requires the {@link Player#getUniqueId()}
     *
     * @author Elia
     * @version 1.0
     * @description Removed the game of the player
     * @since 1.0
     */
    public void removeGame(UUID player) {
      if (saveGameBuilder == null) {
        bossFightCreator().bossFightCreatorLogger().logError("LOAD THE MAP WITH de.elia.bossfightcreator.builder.save.SaveGameBuilder#loadMap()");
        return;
      }
      saveGameBuilder.remove(player);
      bossFightCreator().bossFightCreatorLogger().logInfo("the game" + gameBuilder + "is removed!");
    }

    /**
     * @param game Requires a{@link GameBuilder}
     *
     * @return {@link Boolean}
     *
     * @author Elia
     * @version 1.0
     * @description checks if a specify game in the {@link Map}
     * @since 1.0
     */
    public boolean containsGame(GameBuilder game) {
      return saveGameBuilder.containsValue(game);
    }
  }
}