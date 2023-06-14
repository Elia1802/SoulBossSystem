package de.elia;

import de.elia.achivements.AchievementMain;
import de.elia.api.logging.PluginLogger;
import de.elia.bossfightcreator.BossFightCreatorMain;
import de.elia.items.ItemMain;
import de.elia.soulboss.SoulBoss;
import de.elia.soulbosssystem.SoulBossSystemMain;

/**
 * @author Elia
 * @version 1.0.0.pre1
 * @since 1.0.0.pre1
 * @description Gets instances of all mains and loggers
 */
public class PluginInstances {

  public static final AchievementMain ACHIEVEMENT_MAIN = new AchievementMain();//Gets the Main class of the AchievementMain
  public static final BossFightCreatorMain BOSS_FIGHT_CREATOR = new BossFightCreatorMain();//Gets the Main class of the bossfightcreator Plugin
  public static final ItemMain ITEM_MAIN = new ItemMain();//Gets the the Main class of Item
  public static final SoulBoss SOUL_BOSS = new SoulBoss();//Gets the Main class of the SoulBoss Plugin
  public static final SoulBossSystemMain SOUL_BOSS_SYSTEM_MAIN = new SoulBossSystemMain();//Gets the Main class of the SoulBossSystemMain
  public static final PluginLogger MAIN_LOGGER = new PluginLogger("SoulBossSystem -> Main");//A logger for the main
  public static final PluginLogger ACHIEVEMENT_LOGGER = new PluginLogger("SoulBossSystem -> Achievement");//A logger for the  Achievement plugin
  public static final PluginLogger THE_ZEPSER_API_LOGGER = new PluginLogger("SoulBossSystem -> TheZepserAPI");//A logger for the TheZepserAPI
  public static final PluginLogger BOSS_FIGHT_CREATOR_LOGGER = new PluginLogger("SoulBossSystem -> BossFightCreator");//A logger for the  BossFightCreator
  public static final PluginLogger ITEM_LOGGER = new PluginLogger("SoulBossSystem -> Item");
  public static final PluginLogger SOUL_BOSS_LOGGER = new PluginLogger("SoulBossSystem -> SoulBoss");//A logger for the SoulBoss Plugin
  public static final PluginLogger SOUL_BOSS_SYSTEM_LOGGER = new PluginLogger("SoulBossSystem -> SoulBossSystem");
}
