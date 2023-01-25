package de.elia.soulboss.messages.messages;

import de.elia.soulmain.SoulMain;
import de.elia.soulmain.allplugins.messages.prefix.PrefixClass;
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
public class CustomPrefix extends PrefixClass {

  public CustomPrefix() {

  }

  private final MiniMessage miniMessage = SoulMain.getMiniMessage();

  /**
   * @return component
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
   * @return "[SoulBoss ERROR] "
   * @author Elia
   * @version 1.0
   * @description This Methode set a console error prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  public String errorPrefix() {
    return "[SoulBoss ERROR] ";
  }

  /**
   * @return "[SoulBoss WARNING] "
   * @author Elia
   * @version 1.0
   * @description This Methode set a console warning prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  public String warningPrefix() {
    return "[SoulBoss WARNING] ";
  }

  /**
   * @return "[SoulBoss INFO] "
   * @author Elia
   * @version 1.0
   * @description This Methode set a console info prefix for this Plugin
   * @since 1.0
   */
  @NotNull
  @Override
  public String consolePrefix() {
    return "[SoulBoss INFO] ";
  }
}
