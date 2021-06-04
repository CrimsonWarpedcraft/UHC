package com.crimsonwarpedcraft.uhc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for GameState.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class GameStateTest {

  @Test
  void setRunning() {
    GameState game = GameState.newGameState();

    // Check that setting to true works
    game.setRunning(true);
    assertTrue(game.isRunning());

    // Check that setting to false works
    game.setRunning(false);
    assertFalse(game.isRunning());
  }

  @Test
  void isRunning() {
    GameState game = GameState.newGameState();

    // Check that the default value is false
    assertFalse(game.isRunning());

    // Check that we can see the updated value
    game.setRunning(true);
    assertTrue(game.isRunning());
  }
}