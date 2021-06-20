package com.crimsonwarpedcraft.uhc.util;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class for logging messages to the console.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcLogger {
  private final Logger logger;

  /** Levels in which a message can be logged at. */
  public enum Level {
    INFO(java.util.logging.Level.INFO),
    WARN(java.util.logging.Level.WARNING),
    SEVERE(java.util.logging.Level.SEVERE);

    private final java.util.logging.Level level;

    Level(java.util.logging.Level level) {
      this.level = level;
    }

    private java.util.logging.Level getLevel() {
      return level;
    }
  }

  static UhcLogger getNewUhcLogger(Logger logger) {
    return new UhcLogger(Objects.requireNonNull(logger));
  }

  private UhcLogger(Logger logger) {
    this.logger = logger;
  }

  /** Logs a message to the console at the specified level. */
  public UhcLogger log(Level level, String message) {
    logger.log(level.getLevel(), message);

    return this;
  }
}
