package com.crimsonwarpedcraft.uhc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.nio.file.Paths;
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
  void getMainWorldName() {
    YamlConfiguration fileConfig = new YamlConfiguration();

    // Load config from file
    assertDoesNotThrow(
        () -> fileConfig.load(
            Paths.get("src", "test", "resources", "config.yml").toFile()
        )
    );

    // Make sure we get the expected value
    assertEquals(
        "world1",
        GameConfig
            .getNewGameConfig(fileConfig)
            .getMainWorldName()
    );
  }

  @Test
  void getBorderStartSize() {
    YamlConfiguration fileConfig = new YamlConfiguration();

    // Load config from file
    assertDoesNotThrow(
        () -> fileConfig.load(
            Paths.get("src", "test", "resources", "config.yml").toFile()
        )
    );

    // Make sure we get the expected value
    assertEquals(
        100,
        GameConfig
            .getNewGameConfig(fileConfig)
            .getBorderStartSize()
    );
  }

  @Test
  void getBorderShrinkSeconds() {
    YamlConfiguration fileConfig = new YamlConfiguration();

    // Load config from file
    assertDoesNotThrow(
        () -> fileConfig.load(
            Paths.get("src", "test", "resources", "config.yml").toFile()
        )
    );

    // Make sure we get the expected value
    assertEquals(
        100,
        GameConfig
            .getNewGameConfig(fileConfig)
            .getBorderShrinkSeconds()
    );
  }

  @Test
  void getBorderShrinkSize() {
    YamlConfiguration fileConfig = new YamlConfiguration();

    // Load config from file
    assertDoesNotThrow(
        () -> fileConfig.load(
            Paths.get("src", "test", "resources", "config.yml").toFile()
        )
    );

    // Make sure we get the expected value
    assertEquals(
        100,
        GameConfig
            .getNewGameConfig(fileConfig)
            .getBorderShrinkSize()
    );
  }

  @Test
  void save() {
    YamlConfiguration fileConfig = new YamlConfiguration();
    fileConfig.set("test.val1", 5);
    GameConfig config = GameConfig.getNewGameConfig(fileConfig);

    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> config.save(null)
    );

    File saveFile = Paths.get(
            System.getProperty("java.io.tmpdir"),
            System.currentTimeMillis() + this.getClass().getName()
    )
        .toFile();

    // Loop until we get a unique file name
    while (saveFile.exists()) {
      // Pause for 5ms at a time
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        fail();
      }

      saveFile = Paths.get(
          System.getProperty("java.io.tmpdir"),
          System.currentTimeMillis() + this.getClass().getName()
      )
          .toFile();
    }

    saveFile.deleteOnExit();

    // Check if writing file works
    File finalSaveFile = saveFile;
    assertDoesNotThrow(
        () -> config.save(finalSaveFile)
    );
    assertTrue(finalSaveFile.exists());

    // Check if we can load the config from the file
    YamlConfiguration newFileConfig = new YamlConfiguration();
    assertDoesNotThrow(() -> newFileConfig.load(finalSaveFile));
    assertEquals(5, newFileConfig.getInt("test.val1"));
  }
}