package de.elia.soulboss;

import de.elia.soulboss.functions.Function;
import de.elia.soulboss.functions.register.Register;
import de.elia.soulmain.SoulMain;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @extends {@link JavaPlugin}
 * @description This is the Main Class of the Plugin SoulBoss.
 */
public class SoulBoss extends JavaPlugin {

  private static SoulBoss soulBoss;

  private final MiniMessage miniMessage = MiniMessage.miniMessage();

  private final SoulMain soulMain = (SoulMain) Bukkit.getPluginManager().getPlugin("SoulMain"); //Gets the Soul API of Elia

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Load all thinks in this Plugin.
   */
  @Override
  public void onEnable(){
    if (soulMain == null)return;
    Function.loadFunctions(this, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Reload all thinks in this Plugin.
   */
  public void onReload(){
    if (soulMain == null)return;
    Function.loadFunctions(this, Bukkit.getPluginManager());
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Unload all thinks in this Plugin.
   */
  @Override
  public void onDisable(){
    if (soulMain == null)return;
    Register.Disable.disable();
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Is the Instance of this Plugin
   * @return {@link #soulBoss}
   */
  @NotNull
  public static SoulBoss soulBoss() {
    return soulBoss;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the SoulMain Plugin
   * @return {@link #soulMain}
   */
  @NotNull
  public SoulMain soulMain() {
    return soulMain;
  }

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Gets the MiniMessage
   * @return {@link #miniMessage}
   */
  @NotNull
  public MiniMessage miniMessage() {
    return miniMessage;
  }

  @NotNull
  public String contact(){
    return "～Elia～#0001";
  }
}
