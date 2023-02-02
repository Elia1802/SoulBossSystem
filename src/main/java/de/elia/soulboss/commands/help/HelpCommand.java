package de.elia.soulboss.commands.help;

import de.elia.soulboss.SoulBoss;
import de.elia.soulboss.messages.message.CustomMessages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    CustomMessages message = new CustomMessages();
    MiniMessage miniMessage = SoulBoss.soulBoss().miniMessage();
    if (sender instanceof Player player) {
      if (args.length == 1) {
        if (args[0].equalsIgnoreCase("bosses")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Bosse/Zombie.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen der Bosse zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("commands")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Commands/Commands.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen über die Commands zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("items")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Items/Items.txt><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Informationen über die Items zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else if (args[0].equalsIgnoreCase("recipes")) {
          Component component1 = miniMessage.deserialize("<click:open_url:https://github.com/Elia1802/Bossfight-Information/blob/main/Erkl%C3%A4rungen/Rezept/Zombie%20SpawnEgg%20Rezept.png><green>Öffne</green></click>");
          Component component2 = miniMessage.deserialize("<aqua> um die Rezepte für die Items zu bekommen.</aqua>");
          message.messageWithPrefix(player, component1.append(component2));
        }else {
          message.messageWithPrefix(player, message.red("Dieser Command existiert nicht!"));
          return false;
        }
      }else {
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp bosses"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp commands"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp items"));
        message.messageWithPrefix(player, message.red("Der Command is /bosshelp recipes"));
        return false;
      }
    }else {
      message.errorLog("You have to be a Player!");
      return false;
    }
    return true;
  }

  @Override
  @Nullable
  public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    return Arrays.asList("bosses", "commands", "items", "recipes");
  }
}
