package com.crimsonwarpedcraft.uhc.game;

import com.crimsonwarpedcraft.cwcommons.config.ConfigFile;
import com.crimsonwarpedcraft.cwcommons.config.ConfigOption;
import com.crimsonwarpedcraft.cwcommons.config.ConfigurationException;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the configuration for a UHC game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class GameConfig extends ConfigFile {
  private static final String WORLD_MAIN_WORLD_NAME = "world.main-world-name";
  private static final String WORLD_BORDER_START_SIZE = "world.border-start-size";
  private static final String WORLD_BORDER_SHRINK_RATE = "world.border-shrink-blocks-per-sec";
  private static final String WORLD_BORDER_FINAL_SIZE = "world.border-final-size";

  /**
   * Returns a new GameConfig instance.
   *
   * @param config the config file
   * @return a new GameConfig instance
   * @throws ConfigurationException if there is an error loading and updating the config
   */
  public static GameConfig getNewGameConfig(File config) throws ConfigurationException {
    return new GameConfig(config);
  }

  protected GameConfig(File config) throws ConfigurationException {
    super(Objects.requireNonNull(config));
  }

  /** Returns the name of the server's main world. */
  public String getMainWorldName() {
    return config.getString(WORLD_MAIN_WORLD_NAME);
  }

  /** Returns the size that the world border should start at. */
  public double getBorderStartSize() {
    return config.getDouble(WORLD_BORDER_START_SIZE);
  }

  /** Returns the rate (in blocks/seconds) at which the border should shrink. */
  public double getBorderShrinkRate() {
    return config.getDouble(WORLD_BORDER_SHRINK_RATE);
  }

  /** Returns the size that the border should shrink to. */
  public double getBorderFinalSize() {
    return config.getDouble(WORLD_BORDER_FINAL_SIZE);
  }

  @Override
  protected Set<ConfigOption> getDefaults() {
    Set<ConfigOption> options = new HashSet<>();
    options.add(
        ConfigOption.getNewConfigOption(
            WORLD_MAIN_WORLD_NAME,
            "world",
            value -> value instanceof String
        )
    );
    options.add(
        ConfigOption.getNewConfigOption(
            WORLD_BORDER_START_SIZE,
            1000D,
            value -> {
              try {
                return Double.parseDouble(value.toString()) > 0;
              } catch (NumberFormatException e) {
                return false;
              }
            }
        )
    );
    options.add(
        ConfigOption.getNewConfigOption(
            WORLD_BORDER_SHRINK_RATE,
            .3D,
            value -> {
              try {
                return Double.parseDouble(value.toString()) > 0;
              } catch (NumberFormatException e) {
                return false;
              }
            }
        )
    );
    options.add(
        ConfigOption.getNewConfigOption(
            WORLD_BORDER_FINAL_SIZE,
            500D,
            value -> {
              try {
                return Double.parseDouble(value.toString()) > 0;
              } catch (NumberFormatException e) {
                return false;
              }
            }
        )
    );

    return options;
  }
}
