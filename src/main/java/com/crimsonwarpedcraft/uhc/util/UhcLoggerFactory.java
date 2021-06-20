package com.crimsonwarpedcraft.uhc.util;

import java.util.logging.Logger;

/**
 * Factory class for UhcLoggers.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcLoggerFactory {
  private final Logger logger;

  /** Returns a new UchLoggerFactory instance. */
  public static UhcLoggerFactory getNewUhcLoggerFactory(Logger logger) {
    if (logger == null) {
      return new UhcLoggerFactory(Logger.getLogger(UhcLoggerFactory.class.getName()));
    }

    return new UhcLoggerFactory(logger);
  }

  private UhcLoggerFactory(Logger logger) {
    this.logger = logger;
  }

  /** Returns a new instance of a UhcLogger. */
  public UhcLogger getNewUhcLogger() {
    return UhcLogger.getNewUhcLogger(logger);
  }
}
