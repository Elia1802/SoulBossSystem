package de.elia.soulbosssystem.commands.plugin;

import de.elia.soulbosssystem.SoulBossSystemMain;
import de.elia.soulbosssystem.configuation.SoulBossSystemConfigurationLoader;
import de.elia.systemclasses.keys.PluginKeys;
import de.elia.systemclasses.messages.PluginMessages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @implements {@link CommandExecutor}, {@link TabCompleter}
 * @description This is the Command to reload the Plugin and other thinks
 */
public class SoulBossSystemCommand implements CommandExecutor, TabCompleter {

  private int time = 10;

  /**
   * Executes the given command, returning its success.
   * <br>
   * If false is returned, then the "usage" plugin.yml entry for this command
   * (if defined) will be sent to the player.
   *
   * @param sender  Source of the command
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    Passed command arguments
   * @return true if a valid command, otherwise false
   */
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    PluginMessages message = new PluginMessages();
    var logger = SoulBossSystemMain.soulBossSystemMain().soulBossSystemLogger();
    if (sender instanceof Player player) {
      if (player.hasPermission("soulbosssystem.plugin")) {
        if (args.length == 1) {
          if (args[0].equalsIgnoreCase("help")) {
            message.messageWithPrefix(player, message.gray("Mögliche Commands sind: "));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem help"));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem controls stop plugin/fights"));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem controls reload plugin/files"));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem information version plugin"));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem information version api worldguard/soullibrary"));
            message.messageWithPrefix(player, message.aqua("/soulbosssystem information description/authors"));
            return true;
          }
        }else if (args.length == 2){
          if (args[0].equalsIgnoreCase("information")) {
            if (args[1].equalsIgnoreCase("description")) {
              message.messageWithPrefix(player, message.gray(SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getDescription()));
              return true;
            }else if (args[1].equalsIgnoreCase("authors")) {
              message.messageWithPrefix(player, message.gray("Die Authoren sind "));
              SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getAuthors().forEach(author
                  -> message.messageWithPrefix(player, message.gray("- ")
                  .append(message.aqua(author))
                )
              );
              return true;
            }
          }
        }else if (args.length == 3) {
          if (args[0].equalsIgnoreCase("controls")) {
            if (args[1].equalsIgnoreCase("stop")) {
              if (args[2].equalsIgnoreCase("fights")) {
                message.broadcastWithPrefix(message.red("Das Teammitglied ")
                  .append(message.aqua(player.getName()))
                  .append(message.gray(" löscht alle BossFights!"))
                );
                new BukkitRunnable() {
                  @Override
                  public void run() {
                    time--;
                    if (time == 0) {
                      Bukkit.getWorld("world_bossfight").getEntities().forEach(boss
                        -> {if (boss.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key()))boss.remove();}
                      );
                    }
                  }
                }.runTaskTimer(SoulBossSystemMain.soulBossSystemMain().main(), 0L, 10*20L);
                return true;
              }else if (args[2].equalsIgnoreCase("plugin")) {
                message.messageWithPrefix(player, message.gold("Das Plugin wird beendet"));
                Bukkit.getWorld("world_bossfight").getPlayers().forEach(playerInWorld
                  -> playerInWorld.teleport(Bukkit.getWorld("world").getSpawnLocation()));
                Bukkit.getWorld("world_bossfight").getEntities().forEach(boss -> {
                  if (boss.getPersistentDataContainer().has(PluginKeys.ZOMBIE_KEY.key())) {
                    boss.remove();
                    logger.logInfo("The Boss " + boss.getName() + "/" + boss.getUniqueId() + " was removed!");
                  }
                });
                SoulBossSystemMain.soulBossSystemMain().main().disable();
                return true;
              }else {
                message.messageWithPrefix(player, message.red("/soulbosssystem controls stop plugin/fights"));
                return false;
              }
            }else if (args[1].equalsIgnoreCase("reload")) {
              if (args[2].equalsIgnoreCase("files")) {
                new SoulBossSystemConfigurationLoader().reload(SoulBossSystemMain.soulBossSystemMain().main());
                return true;
              }else if (args[2].equalsIgnoreCase("plugin")) {
                SoulBossSystemMain.soulBossSystemMain().main().onReload();
                return true;
              }else {
                message.messageWithPrefix(player, message.red("/soulbosssystem controls reload plugin/config"));
                return false;
              }
            } else {
              message.messageWithPrefix(player, message.red("/soulbosssystem controls stop"));
              message.messageWithPrefix(player, message.red("/soulbosssystem controls reload"));
              return false;
            }
          }else if (args[0].equalsIgnoreCase("information")) {
            if (args[1].equalsIgnoreCase("version")) {
              if (args[2].equalsIgnoreCase("plugin")) {
                message.messageWithPrefix(player, message.gray("Die Version ist ")
                  .append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().getPluginMeta().getVersion()))
                );
                return true;
              }
            }
          }
        }else if (args.length == 4) {
          if (args[0].equalsIgnoreCase("information")) {
            if (args[1].equalsIgnoreCase("version")) {
              if (args[2].equalsIgnoreCase("api")) {
                if (args[3].equalsIgnoreCase("worlguard")) {
                  message.messageWithPrefix(player, message.gray("Die Version ist ")
                    .append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().worldGuardPlugin().getPluginMeta().getVersion()))
                  );
                  return true;
                }else if (args[3].equalsIgnoreCase("soullibrary")) {
                  message.messageWithPrefix(player, message.gray("Die Version ist ")
                    .append(message.aqua(SoulBossSystemMain.soulBossSystemMain().main().library().getPluginMeta().getVersion()))
                  );
                  return true;
                }else {
                  message.messageWithPrefix(player, message.red("/soulbosssystem information version api thezepserapi"));
                  message.messageWithPrefix(player, message.red("/soulbosssystem information version api worlguard"));
                  message.messageWithPrefix(player, message.red("/soulbosssystem information version api soullibrary"));
                  return false;
                }
              }else {
                message.messageWithPrefix(player, message.red("/soulbosssystem information version api"));
                message.messageWithPrefix(player, message.red("/soulbosssystem information version plugin"));
                return false;
              }
            }else {
              message.messageWithPrefix(player, message.red("/soulbosssystem information authors"));
              message.messageWithPrefix(player, message.red("/soulbosssystem information version"));
              message.messageWithPrefix(player, message.red("/soulbosssystem information description"));
              return false;
            }
          }else {
            message.messageWithPrefix(player, message.red("/soulbosssystem help"));
            message.messageWithPrefix(player, message.red("/soulbosssystem controls"));
            message.messageWithPrefix(player, message.red("/soulbosssystem information"));
            return false;
          }
        }else return false;
      }else {
        message.messageWithPrefix(player, message.red("Du hast keine Berechtigung für diesen Command!"));
        return false;
      }
    }else {
      logger.logWarning("You have to be a Player!");
      return false;
    }
    return false;
  }

  /**
   * Requests a list of possible completions for a command argument.
   *
   * @param sender  Source of the command.  For players tab-completing a
   *                command inside of a command block, this will be the player, not
   *                the command block.
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    The arguments passed to the command, including final
   *                partial argument to be completed
   * @return A List of possible completions for the final argument, or null
   * to default to the command executor
   */
  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    List<String> tab1 = new ArrayList<>();
    List<String> tab2 = new ArrayList<>();
    List<String> tab3 = new ArrayList<>();
    List<String> tab4 = new ArrayList<>();
    if (args.length == 1) {
      tab1.add("help");
      tab1.add("controls");
      tab1.add("information");
      return tab1;
    }else if (args.length == 2) {
      if (args[0].equalsIgnoreCase("controls")) {
        tab2.add("stop");
        tab2.add("reload");
        return tab2;
      }else if (args[0].equalsIgnoreCase("information")) {
        tab2.add("version");
        tab2.add("description");
        tab2.add("authors");
        return tab2;
      }
    }else if (args.length == 3) {
      if (args[1].equalsIgnoreCase("stop")) {
        tab3.add("fights");
        tab3.add("plugin");
        return tab3;
      } else if (args[1].equalsIgnoreCase("reload")) {
        tab3.add("files");
        tab3.add("plugin");
        return tab3;
      } else if (args[1].equalsIgnoreCase("version")) {
        tab3.add("api");
        tab3.add("plugin");
        return tab3;
      }
    }else if (args.length == 4) {
      if (args[2].equalsIgnoreCase("api")) {
        tab4.add("worlguard");
        tab4.add("soullibrary");
        return tab4;
      }
    }
    return null;
  }
}
