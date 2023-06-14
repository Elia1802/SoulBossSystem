package de.elia.systemclasses.messages.prefix;

import de.elia.api.messages.prefix.PrefixClass;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @extends {@link PrefixClass}
 * @description This is the PrefixClass for this Plugin.
 */
public class PluginPrefix extends PrefixClass {

  /**
   * @return {@link Component}
   * @author Elia
   * @version 1.0.0.pre1
   * @description This Methode set a prefix for this Plugin
   * @since 1.0.0.pre1
   */
  @NotNull
  @Override
  public Component prefix() {
    MiniMessage miniMessage = MiniMessage.miniMessage();
    Component component = miniMessage.deserialize("<dark_gray>[</dark_gray><dark_purple>Soul-BossFight</dark_purple><dark_gray>]</dark_gray> ");
    return component;
  }
}
