package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockPluginManager;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.user.UhcPlayerData;
import java.util.ArrayList;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for PlayerEliminator.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class PlayerEliminatorTest {

  @Test
  void getNewPlayerEliminator() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> PlayerEliminator.getNewPlayerEliminator(null)
    );
  }

  @Test
  void onPlayerDeath() {
    MockServer server = new MockServer();
    MockPlayer player = new MockPlayer();
    server.addPlayer(player);
    MockPluginManager manager = ((MockPluginManager) server.getPluginManager());

    GameState gameState = GameState
        .newGameState(server)
        .setRunning(false)
        .storeGamePlayers();
    UhcPlayerData data = gameState
        .getGamePlayers()
        .get(player.getUniqueId());

    PlayerDeathEvent event = new PlayerDeathEvent(
        player,
        new ArrayList<>(),
        0,
        Component.empty()
    );

    PlayerEliminator eliminator = PlayerEliminator.getNewPlayerEliminator(gameState);

    // Check player is still considered alive when game is not running
    eliminator.onPlayerDeath(event);
    assertTrue(data.isAlive());
    assertNull(manager.getLastEvent());

    // Check player is still considered alive when game is running and triggered event is cancelled
    manager.setCancellableResult(MockPluginManager.Result.TRUE);
    eliminator.onPlayerDeath(event);
    assertTrue(data.isAlive());
    assertNull(manager.getLastEvent());

    // Check player is considered dead when the game is running
    manager.setCancellableResult(MockPluginManager.Result.DEFAULT);
    gameState.setRunning(true);
    eliminator.onPlayerDeath(event);
    assertFalse(data.isAlive());
    Event lastEvent = manager.getLastEvent();
    assertNotNull(lastEvent);

    // Check new event is not thrown when the game is running and the player is already dead
    manager.setCancellableResult(MockPluginManager.Result.DEFAULT);
    eliminator.onPlayerDeath(event);
    assertSame(lastEvent, manager.getLastEvent());
  }
}