package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crimsonwarpedcraft.uhc.game.WorldConfig;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import com.crimsonwarpedcraft.uhc.mock.MockWorldBorder;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.junit.jupiter.api.Test;

/**
 * Test for WorldConfig.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class WorldConfigTest {

  @Test
  void setDifficulty() {
    World world = new MockWorld();

    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> WorldConfig
            .getWorldConfig(world)
            .setDifficulty(null)
    );

    // Set difficulty of the world to hard
    WorldConfig
        .getWorldConfig(world)
        .setDifficulty(Difficulty.HARD);

    // Check that the world got set to hard
    assertEquals(Difficulty.HARD, world.getDifficulty());

    // Set difficulty of the world to normal
    WorldConfig
        .getWorldConfig(world)
        .setDifficulty(Difficulty.NORMAL);


    // Check that the world got set to normal
    assertEquals(Difficulty.NORMAL, world.getDifficulty());
  }

  @Test
  void setBorderSize() throws InterruptedException {
    World world = new MockWorld();
    MockWorldBorder border = (MockWorldBorder) world.getWorldBorder();

    WorldConfig
        .getWorldConfig(world)
        .setBorderSize(100);

    assertEquals(100, border.getSize());

    WorldConfig
        .getWorldConfig(world)
        .setBorderSize(200, 1);

    assertEquals(100, border.getSize());

    Thread.sleep(3000);

    assertEquals(200, border.getSize());

    border.shutdownTasks();
  }

  @Test
  void getWorldConfig() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> WorldConfig
            .getWorldConfig(null)
    );
  }
}