package de.elia.soulboss.commands.mob;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.fight.BossFight;
import de.elia.soulboss.fight.remove.RemoveNearBosses;
import de.elia.soulboss.messages.message.CustomMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;

/**
 * @author Elia
 * @version 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command for to spawn the mob.
 * @since 1.0
 */
public class SpawnMobCommand implements CommandExecutor, TabCompleter {

  private static BossFight bossFight;

  /**
   * @param sender  Source of the command
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    Passed command arguments
   * @return true
   * @author Elia
   * @version 1.0
   * @description Create the Command
   * @since 1.0
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    CustomMessages message = new CustomMessages();
    var status = SoulBoss.playerStatusMap();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulboss.fight")) {
        if (args.length == 1) {
          if (args[0].equalsIgnoreCase("start")) {
            if (status.get(player) == 0) {
              status.replace(player, 1);
              bossFight = new BossFight(player, player.getLocation());
            } else {
              message.messageWithPrefix(player, message.red("Du hast ein BossFight aktuell am laufen!"));
            }
          } else if (args[0].equalsIgnoreCase("stop")) {
            new RemoveNearBosses(player);
          }
        }
      } else {
        message.messageWithPrefix(player, message.red("Du hast keine Rechte für diesen Command!"));
      }
    } else {
      message.log(Level.WARNING, "You have to be a Player!");
    }
    return true;
  }

  public static BossFight getBossFight() {
    return bossFight;
  }

  /**
   * @param sender  Source of the command. For players tab-completing a
   *                command inside a command block, this will be the player, not
   *                the command block.
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    The arguments passed to the command, including final
   *                partial argument to be completed
   * @return null
   * @author Elia
   * @version 1.0
   * @description Create the tab completer for this command
   * @since 1.0
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    return Arrays.asList("start", "stop");
  }
}
