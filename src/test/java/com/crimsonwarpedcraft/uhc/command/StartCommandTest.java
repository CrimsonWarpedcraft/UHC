package com.crimsonwarpedcraft.uhc.command;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

/**
 * Tests for StartCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class StartCommandTest {

  @Test
  void onStart() {
    MockPlayer player = new MockPlayer();
    MockServer server = new MockServer();
    server.addPlayer(player);
    server.loadWorld(new MockWorld());
    GameState game = GameState.newGameState(server);
    StartCommand start = StartCommand.getStartCommand(game);

    // Run the start command
    start.onStart(player);

    // Check that the game is running
    assertTrue(game.isRunning());

    // Make sure the player was sent a message
    Component message = player.getLastMessage();
    assertNotNull(message);

    // Run the start command again
    start.onStart(player);

    // Check that the player was sent a different message
    assertNotEquals(message, player.getLastMessage());
  }
}