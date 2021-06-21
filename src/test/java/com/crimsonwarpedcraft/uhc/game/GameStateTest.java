package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import java.util.Set;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

/**
 * Tests for GameState.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class GameStateTest {

  @Test
  void newGameState() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> GameState.newGameState(null)
    );
  }

  @Test
  void setRunning() {
    GameState game = GameState.newGameState(new MockServer());

    // Check that setting to true works
    game.setRunning(true);
    assertTrue(game.isRunning());

    // Check that setting to false works
    game.setRunning(false);
    assertFalse(game.isRunning());
  }

  @Test
  void isRunning() {
    GameState game = GameState.newGameState(new MockServer());

    // Check that the default value is false
    assertFalse(game.isRunning());

    // Check that we can see the updated value
    game.setRunning(true);
    assertTrue(game.isRunning());
  }

  @Test
  void getOnlinePlayers() {
    MockServer server = new MockServer();
    GameState game = GameState.newGameState(server);

    // Add a fake player to the server
    Player player = new MockPlayer();
    server.addPlayer(player);

    // Check that we can find the player in the collection
    assertTrue(game.getOnlinePlayers().contains(player));
  }

  @Test
  void getWorld() {
    MockServer server = new MockServer();
    GameState game = GameState.newGameState(server);

    // Add a fake world to the server
    World world = new MockWorld();
    server.loadWorld(world);

    // Check that we can get the world based on its name
    assertSame(
        game.getWorld(
            world.getName()
        ),
        world
    );
  }

  @Test
  void storeGamePlayers() {
    MockServer server = new MockServer();
    server.addPlayer(new MockPlayer());
    GameState game = GameState.newGameState(server);

    // Check that the player was stored correctly
    assertEquals(
        1,
        game
            .storeGamePlayers()
            .getGamePlayers()
            .size()
    );
  }

  @Test
  void getGamePlayers() {
    MockPlayer player = new MockPlayer();
    MockServer server = new MockServer();
    server.addPlayer(player);

    GameState game = GameState.newGameState(server);

    Set<Player> players = game
        .storeGamePlayers()
        .getGamePlayers();

    // Check that the player was stored correctly
    assertTrue(players.contains(player));

    // Check that the lists returned are not the same
    assertNotSame(players, game.getGamePlayers());
  }
}