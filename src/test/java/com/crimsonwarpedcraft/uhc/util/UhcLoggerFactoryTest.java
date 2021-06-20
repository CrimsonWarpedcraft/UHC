package com.crimsonwarpedcraft.uhc.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcLoggerFactory.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcLoggerFactoryTest {

  @Test
  void getNewUhcLoggerFactory() {
    // Check no NPE
    assertDoesNotThrow(() -> UhcLoggerFactory.getNewUhcLoggerFactory(null));
  }

  @Test
  void getNewUhcLogger() {
    // Check getting logger with non-null argument
    Logger logger = Logger.getLogger(getClass().getName());
    assertNotNull(UhcLoggerFactory.getNewUhcLoggerFactory(logger).getNewUhcLogger());

    // Check getting logger with null argument
    assertNotNull(UhcLoggerFactory.getNewUhcLoggerFactory(null).getNewUhcLogger());
  }
}