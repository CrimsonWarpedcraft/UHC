package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import java.net.InetAddress;
import org.bukkit.event.player.PlayerLoginEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for JoinPreventer.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class JoinPreventerTest {

  @Test
  void getJoinPreventer() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> JoinPreventer.getJoinPreventer(null)
    );
  }

  @Test
  void onPlayerLogin() {
    MockPlayer player = new MockPlayer();
    MockServer server = new MockServer();
    server.addPlayer(player);
    GameState game = GameState
        .newGameState(server);

    PlayerLoginEvent event = new PlayerLoginEvent(player, "", InetAddress.getLoopbackAddress());

    JoinPreventer joinPreventer = JoinPreventer.getJoinPreventer(game);
    joinPreventer.onPlayerLogin(event);

    // Check that players are allowed to log in before the game starts
    assertEquals(PlayerLoginEvent.Result.ALLOWED, event.getResult());

    // Check that players are prevented from logging in after the game starts
    game.setRunning(true);
    joinPreventer.onPlayerLogin(event);
    assertEquals(PlayerLoginEvent.Result.KICK_WHITELIST, event.getResult());

    // Check that players that are game players are allowed to log in after the game starts
    event.allow();
    game.storeGamePlayers();
    joinPreventer.onPlayerLogin(event);
    assertEquals(PlayerLoginEvent.Result.ALLOWED, event.getResult());
  }
}