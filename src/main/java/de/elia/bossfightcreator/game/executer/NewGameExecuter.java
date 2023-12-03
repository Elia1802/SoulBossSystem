package de.elia.bossfightcreator.game.executer;

import de.elia.achivements.achievement.process.Achievement;
import de.elia.achivements.achievement.storage.Achievements;
import de.elia.api.thezepserapi.Complex;
import de.elia.api.thezepserapi.TheZepserAPI;

import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.bossfightcreator.arena.Arena;
import de.elia.bossfightcreator.arena.ArenaHandler;
import de.elia.bossfightcreator.game.Game;
import de.elia.party.Party;
import de.elia.party.utils.PartyUtils;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author Elia
 * @description This event create a new {@link Game} for the {@link Player}.
 * @implements {@link Listener}
 * @beta Because: Its unstable
 */
public class NewGameExecuter implements Listener {

  private final Plugin plugin;
  private Game game;

  public NewGameExecuter(Plugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  private void newGameExecuter(@NotNull PlayerInteractEvent event) {
    Player player = event.getPlayer();
    PluginMessages message = new PluginMessages();
    if ((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) && TheZepserAPI.Item.hasKey(event.getItem(), TheZepserAPI.Item.createKey(Complex.ZOMBIE_SPAWN_EGG), BossFightCreatorMain.bossFightCreator().main())) {
      if (BossFightCreatorMain.playerStatusMap().get(player) == 0) {
        event.setCancelled(true);
        Party party = new Party(player, player.getName() + "-Party");
        PartyUtils.PARTYS.put(player, party);
        player.getInventory().remove(event.getItem());
        BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Create a new GameExecuter for " + player.getName() + "...");
        new Achievement().giveAchievement(player, Achievements.BOSSFIGHT_ZOMBIE);
        Optional<Arena> optionalArena = ArenaHandler.getFreeArena();
        if (optionalArena.isPresent()) {
          Arena arena = optionalArena.get();
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena Information:");
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena: " + arena);
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena schematic: " + arena.getName());
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena ID: " + arena.getArenaID());
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("  -> Arena spawn location: " + arena.getSpawnLocation());
          arena.reBuildArena(arena);
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("Arena " + arena.getArenaID() + " is " + arena.getState() + "!");
          this.game = new Game(arena, player, plugin, party);
          arena.setGame(this.game);
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("The Game of " + arena.getArenaID() + " is " + this.game);
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logInfo("A new GameExecuter was created for " + player.getName());
          return;
        }else {
          message.message(player, message.red("Ein Fehler beim starten des Spiels ist vorgefallen! (ERROR: NO ARENAS AVIABLE)"));
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logWarning("There was a problem by creating a new GameExecuter");
          BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger().logError("Problem: NO ARENAS AVIABLE");
        }
      }else {
        message.message(player, message.red("Du hast grade ein Spiel am laufen!"));
      }
    }
  }
}
