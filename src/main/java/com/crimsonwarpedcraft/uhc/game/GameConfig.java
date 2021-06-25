package com.crimsonwarpedcraft.uhc.game;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Represents the configuration for a UHC game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class GameConfig {
  private static final String WORLD_MAIN_WORLD_NAME = "world.main-world-name";
  private static final String WORLD_BORDER_START_SIZE = "world.border-start-size";
  private static final String WORLD_BORDER_SHRINK_RATE = "world.border-shrink-blocks-per-sec";
  private static final String WORLD_BORDER_FINAL_SIZE = "world.border-final-size";
  private final FileConfiguration config;

  /**
   * Returns a new GameConfig instance.
   *
   * @param config the config loaded from a file
   * @return a new GameConfig instance
   */
  public static GameConfig getNewGameConfig(FileConfiguration config) {
    return new GameConfig(Objects.requireNonNull(config));
  }

  private GameConfig(FileConfiguration config) {
    this.config = config;

    config.options().copyDefaults(true);
    loadDefaults();
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

  /**
   * Saves the config to a file.
   *
   * @param file the file to save to
   * @return this instance for method chaining
   * @throws IOException if an error occurs while writing the file
   */
  public GameConfig save(File file) throws IOException {
    config.save(Objects.requireNonNull(file));

    return this;
  }

  private void loadDefaults() {
    Configuration defaults = new YamlConfiguration();

    // World settings
    defaults.set(WORLD_MAIN_WORLD_NAME, "world");
    defaults.set(WORLD_BORDER_START_SIZE, 1000D);
    defaults.set(WORLD_BORDER_SHRINK_RATE, .3);
    defaults.set(WORLD_BORDER_FINAL_SIZE, 500D);

    config.setDefaults(defaults);
  }
}
