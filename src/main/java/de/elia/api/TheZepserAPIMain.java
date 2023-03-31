package de.elia.api;

import de.elia.Main;
import de.elia.PluginLogger;
import de.elia.api.spells.spells.gravitation.GRAVITATION_SUPER;
import de.elia.api.spells.spells.weather.WEATHER_DEFENSE;
import de.elia.api.spells.spells.weather.WEATHER_SUPER;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class TheZepserAPIMain {

  public static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
  private static TheZepserAPIMain theZepserAPIMain;

  public TheZepserAPIMain(){
    //...
  }

  public void enable(JavaPlugin plugin){
    this.theZepserAPILogger().logInfo("Load TheZepserAPI...");
    theZepserAPIMain = this;
    this.theZepserAPILogger().logInfo("Register TheZepserAPI events...");
    TheZepserAPI.registerAPIEvents();
    this.theZepserAPILogger().logInfo("TheZepserAPI events registered!");
    this.theZepserAPILogger().logInfo("load the magic Gravitation_Super...");
    Bukkit.getPluginManager().registerEvents(new GRAVITATION_SUPER(), plugin);
    this.theZepserAPILogger().logInfo("Gravitation_Super loadded!");
    this.theZepserAPILogger().logInfo("load the magic Weather_Super...");

    Bukkit.getPluginManager().registerEvents(new WEATHER_SUPER(), plugin);
    this.theZepserAPILogger().logInfo("Weather_Super loadded!");
    this.theZepserAPILogger().logInfo("load the magic Weather_Defense...");
    Bukkit.getPluginManager().registerEvents(new WEATHER_DEFENSE(), plugin);
    this.theZepserAPILogger().logInfo("Weather_Defense loadded!");
    this.theZepserAPILogger().logInfo("TheZepserAPI loaded!");
  }

  public void disable(){
    this.theZepserAPILogger().logInfo("Unload TheZepserAPI...");
    this.theZepserAPILogger().logInfo("TheZepserAPI unloaded!");
  }

  @NotNull
  public static TheZepserAPIMain thZepserAPIMain(){
    return theZepserAPIMain;
  }

  @NotNull
  public Main main(){
    return Main.main();
  }

  @NotNull
  public PluginLogger theZepserAPILogger(){
    return Main.THE_ZEPSER_API_LOGGER;
  }

}
