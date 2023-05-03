package de.elia.systemclasses.messages.prefix;

import de.elia.api.messages.prefix.PrefixClass;
import de.elia.systemclasses.logging.PluginLogger;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link PrefixClass}
 * @description This is the PrefixClass for this Plugin.
 */
public class PluginPrefix extends PrefixClass {

  public PluginPrefix() {

  }

  private final MiniMessage miniMessage = MiniMessage.miniMessage();

  /**
   * @return {@link Component}
   * @author Elia
   * @version 1.0
   * @description This Methode set a prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  public Component prefix() {
    Component component = miniMessage.deserialize("<dark_gray>[</dark_gray><dark_purple>Soul-BossFight</dark_purple><dark_gray>]</dark_gray> ");
    return component;
  }

  /**
   * @return {@link String}
   * @author Elia
   * @version 1.0
   * @deprecated the {@link  PluginLogger} create the prefix itself
   * @description This Methode set a console error prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  @Deprecated //the PluginLogger create the prefix itself
  public String errorPrefix() {
    return "[SoulBoss ERROR] ";
  }

  /**
   * @return {@link String}
   * @author Elia
   * @version 1.0
   * @deprecated the {@link  PluginLogger} create the prefix itself
   * @description This Methode set a console warning prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  @Deprecated //the PluginLogger create the prefix itself
  public String warningPrefix() {
    return "[SoulBoss WARNING] ";
  }

  /**
   * @return {@link String}
   * @author Elia
   * @version 1.0
   * @deprecated the {@link  PluginLogger} create the prefix itself
   * @description This Methode set a console info prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  @Deprecated //the PluginLogger create the prefix itself
  public String infoPrefix() {
    return "[SoulBoss INFO] ";
  }
}
