package de.elia.soulbosssystem.commands.idea;

import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class SoulBossSystemIdeaCommand extends Command {

  public SoulBossSystemIdeaCommand() {
    this("soulbosssystemidea", "The Player can send a idea for the server team.", "Use /soulbosssystemidea", Arrays.asList("sbsidea", "sbsi", "idea"));
  }

  public SoulBossSystemIdeaCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    PluginLogger logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    if (sender instanceof Player player) {
      if (args.length > 1) {
        StringBuilder idea = new StringBuilder();
        for (int i = 0; i < args.length; ++i) {
          idea.append(args[i]).append(" ");
        }
        if (SoulBossSystemConfigurationLoader.ideasStorage().get(player.getName()) == null) {
          String string = idea.toString();
          SoulBossSystemConfigurationLoader.ideasStorage().set(player.getName(), (Object)string);
          message.messageWithPrefix(player, message.gray("Du hast die Idee erfolgreich abgesendet!"));
          message.messageWithPrefix(player, message.gray("Deine Idee: "));
          message.messageWithPrefix(player, message.aqua(string));
          return true;
        }
        message.messageWithPrefix(player, message.red("Du hast eine Idee schon abgegeben!"));
        message.messageWithPrefix(player, message.gold("Eine neue Idee kannst du erst eingeben, wenn ein Teammitglied dein Antrag aus der Configuration ausgelesen hat!"));
        return false;
      }
      message.messageWithPrefix(player, message.red("/soulbosssystemidea [IDEA]"));
      return false;
    }
    logger.logWarning("You have to be a Player!");
    return false;
  }
}
