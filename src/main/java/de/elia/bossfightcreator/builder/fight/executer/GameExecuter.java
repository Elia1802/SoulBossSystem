package de.elia.bossfightcreator.builder.fight.executer;

import de.elia.systemclasses.logging.PluginLogger.SaveError;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemLoadException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.api.Complex;
import de.elia.api.TheZepserAPI;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.arena.sender.ArenaSender;
import de.elia.bossfightcreator.builder.fight.game.builder.GameBuilder;
import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.achievement.process.BossFightAchievements;
import de.elia.soulboss.achievement.storage.BossFightAchievementStorage;
import de.elia.soulboss.items.DropUtils;
import de.elia.soulboss.spawn.SpawnEgg;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link Listener}, {@link CommandExecutor} and {@link TabCompleter}
 * @description This class is for the commands end events while the game.
 */
public class GameExecuter implements Listener, CommandExecutor, TabCompleter {

  private final Plugin plugin;
  private GameBuilder builder;
  private final ArenaSender sender = new ArenaSender();

  public GameExecuter(Plugin plugin){
    this.plugin = plugin;
  }

  public GameExecuter(){
    this(BossFightCreator.main());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This event checks if the player click the spawnegg and if this true load this a new game with the {@link GameBuilder}
   */
  @EventHandler
  public void onLoad(@NotNull PlayerInteractEvent event) throws SoulBossSystemNullException, SoulBossSystemLoadException {
    ArenaSender sender = new ArenaSender();
    Player player = event.getPlayer();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    SpawnEgg spawnEgg = new SpawnEgg(this.plugin);
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.ZOMBIE_SPAWN_EGG))) {
        event.setCancelled(true);
        new BossFightAchievements(this.plugin).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT_ZOMBIE);
        builder = new GameBuilder(player, sender.arena());
        player.getInventory().remove(event.getItem());
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description This event checks if the boss die and unload the game
   */
  @EventHandler
  public void onEnd(@NotNull EntityDeathEvent event) throws SoulBossSystemNullException {
    if (event.getEntityType() == EntityType.ZOMBIE) {
      if (event.getEntity().getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
        event.getEntity().remove();
        Collection<ItemStack> itemStacks = event.getDrops();
        event.getDrops().removeAll(itemStacks);
        //Zopnote ends!
        //You have to let your own drops spawn after this one.
        //Elia starts...
        Location location = event.getEntity().getLocation();
        location.getWorld().strikeLightningEffect(location);
        new DropUtils().drop(location);
        builder.game().end();
        //Elia ends!
      }
    }
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description The command to trust other players
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return {@link Boolean}
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (builder == null)return false;
    if (sender instanceof Player owner) {
      if (args.length == 1){
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          try {
            builder.trustPlayer(target);
          } catch (SoulBossSystemNullException exception) {
            new SaveError().saveError(exception, "GameExecuter-onCommand-line_129=null");
            exception.stacktrace();
          }
        }
      }
    }
    return true;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description The tab complete for the trust command
   * @param sender Source of the command.  For players tab-completing a
   *     command inside of a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return {@link List<String>}
   */
  @Override
  @Nullable
  public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    if (args.length == 1) {
      List<String> players = new ArrayList<>();
      for (Player p : Bukkit.getOnlinePlayers()) {
        players.add(p.getName());
      }
      return players;
    }
    return null;
  }
}
