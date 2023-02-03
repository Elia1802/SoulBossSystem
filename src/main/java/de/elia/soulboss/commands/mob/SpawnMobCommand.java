package de.elia.soulboss.commands.mob;

import de.elia.soulboss.fight.BossFight;
import de.elia.soulboss.fight.BossFightManager;
import de.elia.soulboss.messages.message.CustomMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command for to spawn the mob.
 */
public class SpawnMobCommand implements CommandExecutor, TabCompleter {

  private static BossFight bossFight;

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the Command
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return false
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    CustomMessages message = new CustomMessages();
    if (sender instanceof Player player) {
      BossFightManager bossFightManager = new BossFightManager();
      if (player.hasPermission("")) {
        if (args.length == 1){
          if (args[0].equalsIgnoreCase("start")) {
            if (!bossFightManager.playerHasStart(player)) {
              bossFight = new BossFight(player, player.getLocation());
            }else {
              message.messageWithPrefix(player, message.red("Du hast ein BossFight aktuell am laufen!"));
            }
          }else if (args[0].equalsIgnoreCase("stop")) {
            if (bossFightManager.playerHasStart(player)){BossFight.bossFight.stopFight(true);}else message.messageWithPrefix(player, message.red("Du hast zur zeit keinen BossFight am laufen!"));
          }
        }
      }else {
        message.messageWithPrefix(player, message.red("Du hast keine Rechte für diesen Command!"));
      }
    }else {
      message.messageWithPrefix(sender, message.red("You have to be a Player!"));
    }
    return false;
  }

  public static BossFight getBossFight() {
    return bossFight;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Create the tab completer for this command
   * @param sender Source of the command. For players tab-completing a
   *     command inside a command block, this will be the player, not
   *     the command block.
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args The arguments passed to the command, including final
   *     partial argument to be completed
   * @return null
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    return Arrays.asList("start", "stop");
  }
}
