package de.elia.party;

import de.elia.PluginMain;
import de.elia.api.loader.SoulPlugin;
import de.elia.api.loader.exceptions.SoulPluginLoadException;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PartyMain implements SoulPlugin {

  private static PartyMain partyMain;

  @Override
  public void enable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {
    partyMain = this;
  }

  @Override
  public void disable(@NotNull JavaPlugin javaPlugin) throws SoulPluginLoadException {

  }

  @NotNull
  public static PartyMain partyMain(){
    return partyMain;
  }

  @NotNull
  public PluginMain main(){
    return PluginMain.main();
  }

}
