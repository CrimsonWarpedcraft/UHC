package com.crimsonwarpedcraft.uhc.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import net.kyori.adventure.text.Component;
import org.bukkit.Difficulty;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.junit.jupiter.api.Test;

/**
 * Tests for StartCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class StartCommandTest {

  @Test
  void onStart() {
    MockPlayer player1 = new MockPlayer();
    player1.setHealth(10);
    AttributeInstance attribute = player1.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    assertNotNull(attribute);
    attribute.setBaseValue(2);
    player1.setExp(10);
    player1.setExhaustion(5);
    player1.setSaturation(0);
    player1.setFoodLevel(5);
    MockPlayer player2 = new MockPlayer();

    MockServer server = new MockServer();
    server.addPlayer(player1);
    server.addPlayer(player2);

    MockWorld world = new MockWorld();
    world.getWorldBorder().setSize(5);
    world.setDifficulty(Difficulty.PEACEFUL);
    server.loadWorld(world);

    GameState game = GameState.newGameState(server);
    StartCommand start = StartCommand.getStartCommand(game);

    // Run the start command
    start.onStart(player2);

    // Check that the game is running
    assertTrue(game.isRunning());

    // Make sure the player was sent a message
    Component message = player2.getLastMessage();
    assertNotNull(message);

    // Make sure that all players were sent a message
    assertNotNull(player1.getLastMessage());

    // Make sure players' health were reset
    assertNotEquals(10, player1.getHealth());

    // Make sure players' max health were reset
    assertNotEquals(2, attribute.getBaseValue());

    // Make sure players' food level were reset
    assertNotEquals(5, player1.getFoodLevel());

    // Make sure players' experience were reset
    assertNotEquals(10, player1.getExp());

    // Make sure players' saturation were reset
    assertNotEquals(10, player1.getSaturation());

    // Make sure that the world border was set
    assertNotEquals(5, world.getWorldBorder().getSize());

    // Make sure that the difficulty was set to hardcore
    assertEquals(Difficulty.HARD, world.getDifficulty());

    // Run the start command again
    start.onStart(player2);

    // Check that the player was sent a different message
    assertNotEquals(message, player2.getLastMessage());
  }
}