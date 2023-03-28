package de.elia.bossfightcreator.fight.game.maps;

import de.elia.bossfightcreator.fight.game.Game;
import de.elia.bossfightcreator.fight.game.builder.GameBuilder;
import de.elia.soulboss.entity.mobs.boss.mob.ZombieBoss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description All {@link List}s and {@link Map}s for the {@link Game} and {@link GameBuilder}
 */
public class GameMaps {

   public static final Map<UUID, Game> EXIST_GAME_OF_PLAYER = new HashMap<>(); //Save a Game to a UUID of a Player
   public static final List<Game> GAMES = new ArrayList<>(); //Save all Games in a List
   public static final Map<ZombieBoss, String> BOSS_KEY = new HashMap<>();

}
