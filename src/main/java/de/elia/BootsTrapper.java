package de.elia;

import de.elia.api.annotation.Planned;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@Planned("See comment in paper.yml")
public class BootsTrapper implements PluginBootstrap {

  public void bootstrap(@NotNull BootstrapContext context) {
  }

  @NotNull
  public JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
    return new PluginMain();
  }
}
