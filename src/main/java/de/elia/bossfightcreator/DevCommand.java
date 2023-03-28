package de.elia.bossfightcreator;

import de.elia.bossfightcreator.Instances.Plugin;
import de.elia.bossfightcreator.fight.arena.sender.ArenaSender;
import de.elia.bossfightcreator.fight.game.builder.GameBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
//FOR THE TEST OF THE MATCHMAKING (Will be deleted on release)
public class DevCommand implements CommandExecutor {

  @Override
  public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (commandSender instanceof Player player) {
      ArenaSender sender = new ArenaSender();
      new GameBuilder(player, sender.arena(), Plugin.instance);
    }
    return true;
  }
}
