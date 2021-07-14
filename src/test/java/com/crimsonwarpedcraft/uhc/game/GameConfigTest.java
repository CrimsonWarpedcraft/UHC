package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crimsonwarpedcraft.cwcommons.config.ConfigurationException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

/**
 * Tests for GameConfig.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings("PATH_TRAVERSAL_IN")
class GameConfigTest {

  @Test
  void getNewGameConfig() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> GameConfig.getNewGameConfig(null)
    );
  }

  @Test
  void getMainWorldName() throws IOException, ConfigurationException {
    File configFile = Files.createTempFile(
        String.valueOf(System.currentTimeMillis()),
        "config.yml"
    )
        .toFile();
    GameConfig config = GameConfig.getNewGameConfig(configFile);

    // Make sure we get the expected default value
    assertEquals(
        "world",
        config.getMainWorldName()
    );

    // Check that saved values are loaded properly
    YamlConfiguration savedConfig = new YamlConfiguration();
    assertDoesNotThrow(
        () -> savedConfig.load(configFile)
    );
    savedConfig.set("world.main-world-name", "world1");
    savedConfig.save(configFile);
    config = GameConfig.getNewGameConfig(configFile);
    assertEquals(
        "world1",
        config.getMainWorldName()
    );

    // Check that invalid values throw an error
    savedConfig.set("world.main-world-name", 1);
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
  }

  @Test
  void getBorderStartSize() throws IOException, ConfigurationException {
    File configFile = Files.createTempFile(
        String.valueOf(System.currentTimeMillis()),
        "config.yml"
    )
        .toFile();
    GameConfig config = GameConfig.getNewGameConfig(configFile);

    // Make sure we get the expected default value
    assertEquals(
        1000D,
        config.getBorderStartSize()
    );

    // Check that saved values are loaded properly
    YamlConfiguration savedConfig = new YamlConfiguration();
    assertDoesNotThrow(
        () -> savedConfig.load(configFile)
    );
    savedConfig.set("world.border-start-size", 5D);
    savedConfig.save(configFile);
    config = GameConfig.getNewGameConfig(configFile);
    assertEquals(
        5D,
        config.getBorderStartSize()
    );

    // Check that invalid values throw an error
    savedConfig.set("world.border-start-size", -1D);
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
    savedConfig.set("world.border-start-size", "test");
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
  }

  @Test
  void getBorderShrinkRate() throws IOException, ConfigurationException {
    File configFile = Files.createTempFile(
        String.valueOf(System.currentTimeMillis()),
        "config.yml"
    )
        .toFile();
    GameConfig config = GameConfig.getNewGameConfig(configFile);

    // Make sure we get the expected default value
    assertEquals(
        .3,
        config.getBorderShrinkRate()
    );

    // Check that saved values are loaded properly
    YamlConfiguration savedConfig = new YamlConfiguration();
    assertDoesNotThrow(
        () -> savedConfig.load(configFile)
    );
    savedConfig.set("world.border-shrink-blocks-per-sec", 5D);
    savedConfig.save(configFile);
    config = GameConfig.getNewGameConfig(configFile);
    assertEquals(
        5D,
        config.getBorderShrinkRate()
    );

    // Check that invalid values throw an error
    savedConfig.set("world.border-shrink-blocks-per-sec", -1D);
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
    savedConfig.set("world.border-shrink-blocks-per-sec", "test");
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
  }

  @Test
  void getBorderFinalSize() throws IOException, ConfigurationException {
    File configFile = Files.createTempFile(
        String.valueOf(System.currentTimeMillis()),
        "config.yml"
    )
        .toFile();
    GameConfig config = GameConfig.getNewGameConfig(configFile);

    // Make sure we get the expected default value
    assertEquals(
        500D,
        config.getBorderFinalSize()
    );

    // Check that saved values are loaded properly
    YamlConfiguration savedConfig = new YamlConfiguration();
    assertDoesNotThrow(
        () -> savedConfig.load(configFile)
    );
    savedConfig.set("world.border-final-size", 1D);
    savedConfig.save(configFile);
    config = GameConfig.getNewGameConfig(configFile);
    assertEquals(
        1D,
        config.getBorderFinalSize()
    );

    // Check that invalid values throw an error
    savedConfig.set("world.border-final-size", -1D);
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
    savedConfig.set("world.border-final-size", "test");
    savedConfig.save(configFile);
    assertThrows(
        ConfigurationException.class,
        () -> GameConfig.getNewGameConfig(configFile)
    );
  }
}