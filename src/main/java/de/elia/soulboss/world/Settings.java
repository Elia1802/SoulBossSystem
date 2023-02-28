package de.elia.soulboss.world;

import de.elia.soulboss.world.generator.WorldGenerator;
import de.elia.soulboss.world.settings.WorldSettings;
import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;

import java.lang.Boolean;

/**
 * @author Elia
 * @version 1.0
 * @since 1.0
 * @description is the Interface the world settings for the {@link WorldGenerator}
 * @impementationOf {@link WorldSettings}
 */
public interface Settings {

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate biomes in the world.
   * @impementationOf {@link WorldSettings#biome()}
   * @return null
   */
  @NotNull Biome biome();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate bedrock in the world.
   * @impementationOf {@link WorldSettings#bedrock()}
   * @return {@link Boolean}
   */
  @NotNull Boolean bedrock();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate caves in the world.
   * @impementationOf {@link WorldSettings#caves()}
   * @return {@link Boolean}
   */
  @NotNull Boolean caves();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate decoration in the world.
   * @impementationOf {@link WorldSettings#decoration()}
   * @return {@link Boolean}
   */
  @NotNull Boolean decoration();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if spawn mobs in the world.
   * @impementationOf {@link WorldSettings#mobs()}
   * @return {@link Boolean}
   */
  @NotNull Boolean mobs();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate noises in the world.
   * @impementationOf {@link WorldSettings#noise()}
   * @return {@link Boolean}
   */
  @NotNull Boolean noise();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate structures in the world.
   * @impementationOf {@link WorldSettings#structures()}
   * @return {@link Boolean}
   */
  @NotNull Boolean structures();

  /**
   * @author Elia
   * @version 1.0
   * @since 1.0
   * @description Set if generate surfaces in the world.
   * @impementationOf {@link WorldSettings#surface()}
   * @return {@link Boolean}
   */
  @NotNull Boolean surface();

}
