package com.crimsonwarpedcraft.uhc.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for UhcPlayerData.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcPlayerDataTest {

  @Test
  void isAlive() {
    UhcPlayerData data = UhcPlayerData.getNewUhcPlayerData();

    // Check that the default is true
    assertTrue(data.isAlive());

    // Check that the data reflects changes
    data.setAlive(false);
    assertFalse(data.isAlive());
  }

  @Test
  void setAlive() {
    UhcPlayerData data = UhcPlayerData.getNewUhcPlayerData();

    // Check that the data reflects changes
    data.setAlive(false);
    assertFalse(data.isAlive());
    data.setAlive(true);
    assertTrue(data.isAlive());
  }
}