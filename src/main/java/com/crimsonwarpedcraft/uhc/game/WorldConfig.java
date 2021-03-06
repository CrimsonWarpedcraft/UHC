package com.crimsonwarpedcraft.uhc.game;

import java.util.Objects;
import org.bukkit.Difficulty;
import org.bukkit.World;

/**
 * Modify world the properties of the world.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class WorldConfig {
  private final World world;

  private WorldConfig(World world) {
    this.world = world;
  }

  /**
   * Set the difficulty of the world.
   *
   * @param difficulty the difficulty to set the world to
   * @return this instance that can be used for method chaining
   */
  public WorldConfig setDifficulty(Difficulty difficulty) {
    world.setDifficulty(Objects.requireNonNull(difficulty));

    return this;
  }

  /**
   * Set time of world.
   *
   * @param time the new absolute time in ticks
   * @return this instance that can be used for method chaining
   */
  public WorldConfig setTime(long time) {
    world.setFullTime(time);

    return this;
  }

  /**
   * Set the size of the world border.
   *
   * @param blocks the size of the border in blocks
   * @return this instance that can be used for method chaining
   */
  public WorldConfig setBorderSize(double blocks) {
    world.getWorldBorder().setSize(blocks);

    return this;
  }

  /**
   * Changes the size of the world border over a duration of seconds.
   *
   * @param blocks the size of the border in blocks
   * @param secs the amount of time in seconds to shrink or grow the border for
   * @return this instance that can be used for method chaining
   */
  public WorldConfig setBorderSize(double blocks, long secs) {
    world.getWorldBorder().setSize(blocks, secs);

    return this;
  }

  /**
   * Returns current border size.
   *
   * @return Size of the border in blocks
   */
  public double getBorderSize() {
    return world.getWorldBorder().getSize();
  }

  /**
   * Get a new WorldConfig instance to modify the properties of a world.
   *
   * @param world the world to modify the properties of
   * @return a WorldConfig instance
   */
  public static WorldConfig getWorldConfig(World world) {
    return new WorldConfig(Objects.requireNonNull(world));
  }
}
