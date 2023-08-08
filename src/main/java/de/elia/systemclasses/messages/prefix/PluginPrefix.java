package de.elia.systemclasses.messages.prefix;

import de.elia.api.messages.prefix.PrefixClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class PluginPrefix
  extends PrefixClass {
  @NotNull
  public Component prefix() {
    MiniMessage miniMessage = MiniMessage.miniMessage();
    return miniMessage.deserialize("<dark_gray>[</dark_gray><dark_purple>Soul-BossFight</dark_purple><dark_gray>]</dark_gray> ");
  }
}
