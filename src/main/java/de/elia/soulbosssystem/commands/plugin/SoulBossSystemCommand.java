package de.elia.soulbosssystem.commands.plugin;

import de.elia.api.logging.PluginLogger;
import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.systemclasses.messages.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SoulBossSystemCommand extends Command {

  private int time = 10;

  public SoulBossSystemCommand() {
    this("soulbosssystem", "All information about this Plugin and all functions to reload or restart this plugin", "Use /soulbosssystem", Arrays.asList("sbs", "soulbosssys"));
  }

  public SoulBossSystemCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    PluginLogger logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.plugin")) {
        if (args.length == 1) {
          if (!args[0].equalsIgnoreCase("help")) {
            return false;
          }
          message.messageWithPrefix(player, message.gray("Mögliche Commands sind: "));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem help"));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem controls stop plugin/fights"));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem controls reload plugin/files"));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem information version plugin"));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem information version api worldguard/soullibrary"));
          message.messageWithPrefix(player, message.aqua("/soulbosssystem information description/authors"));
          return true;
        }
        if (args.length == 2) {
          if (!args[0].equalsIgnoreCase("information")) {
            return false;
          }
          if (args[1].equalsIgnoreCase("description")) {
            message.messageWithPrefix(player, message.gray(SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getDescription()));
            return true;
          }
          if (!args[1].equalsIgnoreCase("authors")) {
            return false;
          }
          message.messageWithPrefix(player, message.gray("Die Authoren sind "));
          SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getAuthors().forEach(author -> message.messageWithPrefix(player, message.gray("- ").append(message.aqua(author))));
          return true;
        }
        if (args.length == 3) {
          if (args[0].equalsIgnoreCase("controls")) {
            if (args[1].equalsIgnoreCase("stop")) {
              if (args[2].equalsIgnoreCase("fights")) {
                message.broadcastWithPrefix(message.red("Das Teammitglied ").append(message.aqua(player.getName())).append(message.gray(" löscht alle BossFights!")));
                new BukkitRunnable(){
                  @Override
                  public void run() {
                    --SoulBossSystemCommand.this.time;
                    if (SoulBossSystemCommand.this.time == 0) {
                      Bukkit.getWorld((String)"world_bossfight").getEntities().forEach(boss -> {
                        if (boss.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
                          boss.remove();
                        }
                      });
                    }
                  }
                }.runTaskTimer((Plugin)SoulBossSystemMain.soulBossSystemMain().main(), 0L, 200L);
                return true;
              }
              if (args[2].equalsIgnoreCase("plugin")) {
                message.messageWithPrefix(player, message.gold("Das Plugin wird beendet"));
                Bukkit.getWorld((String)"world_bossfight").getPlayers().forEach(playerInWorld -> playerInWorld.teleport(Bukkit.getWorld((String)"world").getSpawnLocation()));
                Bukkit.getWorld((String)"world_bossfight").getEntities().forEach(boss -> {
                  if (boss.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
                    boss.remove();
                    logger.logInfo("The Boss " + boss.getName() + "/" + boss.getUniqueId() + " was removed!");
                  }
                });
                SoulBossSystemMain.soulBossSystemMain().main().disable();
                return true;
              }
              message.messageWithPrefix(player, message.red("/soulbosssystem controls stop plugin/fights"));
              return false;
            }
            if (args[1].equalsIgnoreCase("reload")) {
              if (args[2].equalsIgnoreCase("files")) {
                new SoulBossSystemConfigurationLoader().reload((Plugin)SoulBossSystemMain.soulBossSystemMain().main());
                return true;
              }
              if (args[2].equalsIgnoreCase("plugin")) {
                SoulBossSystemMain.soulBossSystemMain().main().onReload();
                return true;
              }
              message.messageWithPrefix(player, message.red("/soulbosssystem controls reload plugin/config"));
              return false;
            }
            message.messageWithPrefix(player, message.red("/soulbosssystem controls stop"));
            message.messageWithPrefix(player, message.red("/soulbosssystem controls reload"));
            return false;
          }
          if (!(args[0].equalsIgnoreCase("information") && args[1].equalsIgnoreCase("version") && args[2].equalsIgnoreCase("plugin"))) {
            return false;
          }
          message.messageWithPrefix(player, message.gray("Die Version ist ").append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getVersion())));
          return true;
        }
        if (args.length != 4) {
          return false;
        }
        if (args[0].equalsIgnoreCase("information")) {
          if (args[1].equalsIgnoreCase("version")) {
            if (args[2].equalsIgnoreCase("api")) {
              if (args[3].equalsIgnoreCase("worlguard")) {
                message.messageWithPrefix(player, message.gray("Die Version ist ").append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().worldEditPlugin().getPluginMeta().getVersion())));
                return true;
              }
              if (args[3].equalsIgnoreCase("soullibrary")) {
                message.messageWithPrefix(player, message.gray("Die Version ist ").append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().soulMain().getPluginMeta().getVersion())));
                return true;
              }
              message.messageWithPrefix(player, message.red("/soulbosssystem information version api thezepserapi"));
              message.messageWithPrefix(player, message.red("/soulbosssystem information version api worlguard"));
              message.messageWithPrefix(player, message.red("/soulbosssystem information version api soullibrary"));
              return false;
            }
            message.messageWithPrefix(player, message.red("/soulbosssystem information version api"));
            message.messageWithPrefix(player, message.red("/soulbosssystem information version plugin"));
            return false;
          }
          message.messageWithPrefix(player, message.red("/soulbosssystem information authors"));
          message.messageWithPrefix(player, message.red("/soulbosssystem information version"));
          message.messageWithPrefix(player, message.red("/soulbosssystem information description"));
          return false;
        }
        message.messageWithPrefix(player, message.red("/soulbosssystem help"));
        message.messageWithPrefix(player, message.red("/soulbosssystem controls"));
        message.messageWithPrefix(player, message.red("/soulbosssystem information"));
        return false;
      }
      message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
      return false;
    }
    logger.logWarning("You have to be a Player!");
    return false;
  }

  @NotNull
  public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    ArrayList<String> tab1 = new ArrayList<>();
    ArrayList<String> tab2 = new ArrayList<>();
    ArrayList<String> tab3 = new ArrayList<>();
    ArrayList<String> tab4 = new ArrayList<>();
    if (args.length == 1) {
      tab1.add("help");
      tab1.add("controls");
      tab1.add("information");
      return tab1;
    }
    if (args.length == 2) {
      if (args[0].equalsIgnoreCase("controls")) {
        tab2.add("stop");
        tab2.add("reload");
        return tab2;
      }
      if (args[0].equalsIgnoreCase("information")) {
        tab2.add("version");
        tab2.add("description");
        tab2.add("authors");
        return tab2;
      }
    } else if (args.length == 3) {
      if (args[1].equalsIgnoreCase("stop")) {
        tab3.add("fights");
        tab3.add("plugin");
        return tab3;
      }
      if (args[1].equalsIgnoreCase("reload")) {
        tab3.add("files");
        tab3.add("plugin");
        return tab3;
      }
      if (args[1].equalsIgnoreCase("version")) {
        tab3.add("api");
        tab3.add("plugin");
        return tab3;
      }
    } else if (args.length == 4 && args[2].equalsIgnoreCase("api")) {
      tab4.add("worlguard");
      tab4.add("soullibrary");
      return tab4;
    }
    return null;
  }
}
