package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.user.UhcPlayerData;
import java.util.ArrayList;
import net.kyori.adventure.text.Component;
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

    // Check that the player is still considered alive when game is not running
    eliminator.onPlayerDeath(event);
    assertTrue(data.isAlive());

    // Check that the player is considered dead when the game is running
    gameState.setRunning(true);
    eliminator.onPlayerDeath(event);
    assertFalse(data.isAlive());
  }
}