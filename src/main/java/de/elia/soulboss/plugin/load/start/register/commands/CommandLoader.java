package de.elia.soulboss.plugin.load.start.register.commands;

import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException;
import de.elia.systemclasses.logging.exceptions.SoulBossSystemNullException.CheckVariable;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description This is the Register class to register all Commands.
 */
public class CommandLoader {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Register the Command
   * @param command Requires the command name
   * @param commandClass Requires the Command class
   */
  public void loadCommand(String command, CommandExecutor commandClass) throws SoulBossSystemNullException {
    if (!new CheckVariable().check(command, "CommandLoader#loadCommand(String, CommandExecuter)"))return;
    if (!new CheckVariable().check(commandClass, "CommandLoader#loadCommand(String, CommandExecuter)"))return;
    Bukkit.getPluginCommand(command).setExecutor(commandClass);
  }
}
