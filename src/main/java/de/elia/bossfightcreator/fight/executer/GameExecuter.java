package de.elia.bossfightcreator.fight.executer;

import de.elia.CustomMessages;
import de.elia.Keys;
import de.elia.api.Complex;
import de.elia.api.TheZepserAPI;
import de.elia.bossfightcreator.BossFightCreator;
import de.elia.bossfightcreator.fight.arena.sender.ArenaSender;
import de.elia.bossfightcreator.fight.game.builder.GameBuilder;
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

  @EventHandler
  public void onLoad(@NotNull PlayerInteractEvent event){
    ArenaSender sender = new ArenaSender();
    Player player = event.getPlayer();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    CustomMessages message = new CustomMessages();
    SpawnEgg spawnEgg = new SpawnEgg(this.plugin);
    if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
      if (TheZepserAPI.item.hasKey(event.getItem(), TheZepserAPI.item.createKey(Complex.ZOMBIE_SPAWN_EGG))) {
        new BossFightAchievements(this.plugin).giveAchievement(player, BossFightAchievementStorage.BOSSFIGHT_ZOMBIE);
        builder = new GameBuilder(player, sender.arena());
      }
    }
  }

  @EventHandler
  public void onEnd(@NotNull EntityDeathEvent event){
    if (event.getEntityType() == EntityType.ZOMBIE) {
      if (event.getEntity().getPersistentDataContainer().has(Keys.ZOMBIE_KEY.key())) {
        event.getEntity().remove();
        Collection<ItemStack> itemStacks = event.getDrops();
        event.getDrops().removeAll(itemStacks);
        //Zopnote ends!
        //You have to let your own drops spawn after this one.
        //Elia starts...
        Location location = event.getEntity().getLocation();
        location.getWorld().strikeLightningEffect(location);
        new DropUtils().drop(location);
        builder.game().removeGame();
        //Elia ends!
      }
    }
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (builder == null)return false;
    if (sender instanceof Player owner) {
      if (args.length == 1){
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          builder.trustPlayer(target);
        }
      }
    }
    return true;
  }

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
