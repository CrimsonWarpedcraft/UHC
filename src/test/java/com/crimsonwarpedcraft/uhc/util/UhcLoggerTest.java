package com.crimsonwarpedcraft.uhc.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crimsonwarpedcraft.uhc.mock.MockLogger;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcLogger.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcLoggerTest {

  @Test
  void getNewUhcLogger() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> UhcLogger.getNewUhcLogger(null)
    );
  }

  @Test
  void log() {
    // Make sure messages are properly logged
    MockLogger logger = new MockLogger(getClass().getName());
    UhcLogger uhcLogger = UhcLogger.getNewUhcLogger(logger);
    uhcLogger.log(UhcLogger.Level.WARN, "test");
    assertEquals("test", logger.getLastLog());
  }
}