package de.elia.bossfightcreator.commands.world;

import de.elia.api.logging.PluginLogger;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Elia
 * @description This command teleport a player in a other world
 * @extends {@link Command}
 */
public class BossFightCreatorWorldCommand extends Command {
  public BossFightCreatorWorldCommand() {
    this("bossfightcreatoworld", "The bossfightcreatorworld teleported a specify player in the worlds of this plugin and back to the normal world", "Use /bossfightcreatorhelp [PLAYER] [WORLD]", Arrays.asList("bossfightcreatorw", "bfchelw", "world", "tpworld"));
  }

  public BossFightCreatorWorldCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginLogger logger = BossFightCreatorMain.bossFightCreator().bossFightCreatorLogger();
    PluginMessages messages = new PluginMessages();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.bossfightgenerator.tpworld")) {
        if (args.length == 2) {
          Player target = Bukkit.getPlayer(args[0]);
          if (target.getName() == null) {
            messages.message(player, messages.red(args[0] + "ist nicht online oder existiert nicht!"));
            return false;
          }
          if (args[1].equalsIgnoreCase("world_bossfight")) {
            target.teleport(Bukkit.getWorld((String)"world_bossfight").getSpawnLocation());
            messages.messageWithPrefix(target, messages.gray("Du wurdest in die Welt ").append(messages.aqua("world_bossfight")).append(messages.gray(" teleportiert!")));
            messages.messageWithPrefix(player, messages.gray("Du hast den Spieler ").append(messages.aqua(target.getName())).append(messages.gray(" in die Welt ")).append(messages.aqua("world_bossfight")).append(messages.gray(" teleportiert!")));
            return true;
          }
          if (args[1].equalsIgnoreCase("world")) {
            target.teleport(Bukkit.getWorld((String)"world").getSpawnLocation());
            messages.messageWithPrefix(target, messages.gray("Du wurdest in die Welt ").append(messages.aqua("world")).append(messages.gray(" teleportiert!")));
            messages.messageWithPrefix(player, messages.gray("Du hast den Spieler ").append(messages.aqua(target.getName())).append(messages.gray(" in die Welt ")).append(messages.aqua("world")).append(messages.gray(" teleportiert!")));
            return true;
          }
          messages.messageWithPrefix(player, messages.red("Dieser Command existiert nicht!"));
          return false;
        }
        messages.messageWithPrefix(player, messages.red("/tpworld world_bossfight"));
        messages.messageWithPrefix(player, messages.red("/tpworld world"));
        return false;
      }
      messages.messageWithPrefix(player, messages.red("Du hast keine Rechte für diesen Command!"));
      return false;
    }
    logger.logWarning("You have to be a Player!");
    return false;
  }

  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      Bukkit.getOnlinePlayers().forEach(player -> tab1.add(player.getName()));
      return tab1;
    }else if (args.length == 2) {
      tab2.add("world");
      tab2.add("world_bossfight");
      return tab2;
    }
    return null;
  }
}
