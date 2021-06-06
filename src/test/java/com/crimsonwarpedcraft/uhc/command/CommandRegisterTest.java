package com.crimsonwarpedcraft.uhc.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for CommandRegister.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class CommandRegisterTest {

  @Test
  void newCommandRegister() {
    // Check NPE
    assertThrows(NullPointerException.class, () -> CommandRegister.newCommandRegister(null));
  }
}