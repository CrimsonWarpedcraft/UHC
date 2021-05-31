package com.crimsonwarpedcraft.uhc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import com.crimsonwarpedcraft.uhc.mock.MockWorldBorder;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldBorder;
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

    // Set difficulty of the world to hard
    WorldConfig
        .getInstance(world)
        .setDifficulty(Difficulty.HARD);

    // Check that the world got set to hard
    assertEquals(Difficulty.HARD, world.getDifficulty());

    // Set difficulty of the world to normal
    WorldConfig
        .getInstance(world)
        .setDifficulty(Difficulty.NORMAL);


    // Check that the world got set to normal
    assertEquals(Difficulty.NORMAL, world.getDifficulty());
  }

  @Test
  void setBorderSize() throws InterruptedException {
    World world = new MockWorld();
    MockWorldBorder border = (MockWorldBorder) world.getWorldBorder();

    WorldConfig
        .getInstance(world)
        .setBorderSize(100);

    assertEquals(100, border.getSize());

    WorldConfig
        .getInstance(world)
        .setBorderSize(200, 1);

    assertEquals(100, border.getSize());

    Thread.sleep(3000);

    assertEquals(200, border.getSize());

    border.shutdownTasks();
  }
}