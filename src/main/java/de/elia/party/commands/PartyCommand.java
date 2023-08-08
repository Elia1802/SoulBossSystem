package de.elia.party.commands;

import de.elia.party.Party;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static de.elia.party.utils.PartyUtils.PARTYS;

public class PartyCommand extends Command {

  protected PartyCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  public PartyCommand(){
    this("party", "Thats the Party command to add or remove a Player", "party add/remove [PARTY] [PLAYER]", List.of("p"));
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    if (sender instanceof Player player){
      if (args.length == 3) {
        if (args[0].equalsIgnoreCase("add")) {
          String party = args[1];
          Player target = Bukkit.getPlayer(args[2]);
          if (target == null) {
            message.message(player, message.red("Dieser Spieler ist nicht da!"));
            return false;
          }
          if (PARTYS.containsKey(player)) {
            Party p = PARTYS.get(player);
            if (party.equals(p.name())) {
              p.addPlayer(target);
              message.message(target, message.aqua("Du wurdest der Party ").append(message.aqua(p.name())).append(message.gray(" eingewiesen!")));
              message.message(player, message.aqua("Du hast ").append(message.aqua(target.getName())).append(message.gray(" zu deiner Party eingewiesen!")));
              return true;
            }else {
              message.message(player, message.red("Dieses Party gibts nicht!"));
              return false;
            }
          }else {
            message.message(player, message.red("Du hast kein Party!"));
            return false;
          }
        }else if (args[0].equalsIgnoreCase("remove")) {
          String party = args[2];
          Player target = Bukkit.getPlayer(args[3]);
          if (target == null) {
            message.message(player, message.red("Dieser Spieler ist nicht da!"));
            return false;
          }
          if (PARTYS.containsKey(player)) {
            Party p = PARTYS.get(player);
            if (party.equals(p.name())) {
              p.removePlayer(target);
              message.message(target, message.aqua("Du wurdest von der Party ").append(message.aqua(p.name())).append(message.gray(" entfernt!")));
              message.message(player, message.aqua("Du hast ").append(message.aqua(target.getName())).append(message.gray(" aus deiner Party entfernt!")));
              return true;
            }else {
              message.message(player, message.red("Dieses Party gibts nicht!"));
              return false;
            }
          }else {
            message.message(player, message.red("Du hast kein Party!"));
            return false;
          }
        }else {
          message.message(player, message.red(usageMessage));
          return false;
        }
      }else {
        message.message(player, message.red("Dieser Command existiert nicht!"));
        return false;
      }
    }else {
      message.message(sender, message.red("You have to be a Player!"));
      return false;
    }
  }

  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    List<String> tab1 = new ArrayList<>();
    List<String> tab2 = new ArrayList<>();
    if (args.length == 1) {
      tab1.add("add");
      tab1.add("remove");
      return tab1;
    }else if (args.length == 3) {
      Bukkit.getOnlinePlayers().forEach(player -> tab2.add(player.getName()));
      return tab2;
    }else return null;
  }
}
