package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.crimsonwarpedcraft.cwcommons.config.ConfigurationException;
import com.crimsonwarpedcraft.uhc.event.UhcPlayerDeathEvent;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import com.crimsonwarpedcraft.uhc.mock.MockWorldBorder;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/**
 * Tests for BorderShrinker.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class BorderShrinkerTest {

  @Test
  void getBorderShrinker() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> BorderShrinker
            .getBorderShrinker(
                null,
                GameConfig.getNewGameConfig(
                    Files
                        .createTempFile(
                            String.valueOf(System.currentTimeMillis()),
                            "config.json"
                        )
                        .toFile()
                )
            )
    );
    assertThrows(
        NullPointerException.class,
        () -> BorderShrinker
            .getBorderShrinker(
                GameState.newGameState(new MockServer()),
                null
            )
    );
  }

  @Test
  void onUhcPlayerDeath() throws ConfigurationException {
    MockPlayer player1 = new MockPlayer();
    MockPlayer player2 = new MockPlayer();
    MockWorld world = new MockWorld();
    world.setName("world1");
    MockServer server = new MockServer();
    server.addPlayer(player1);
    server.addPlayer(player2);
    server.loadWorld(world);
    GameState game = GameState
        .newGameState(server)
        .setRunning(true);

    GameConfig config = GameConfig.getNewGameConfig(
        Paths.get("src", "test", "resources", "config.yml").toFile()
    );
    BorderShrinker shrinker = BorderShrinker.getBorderShrinker(game, config);

    UhcPlayerDeathEvent event = UhcPlayerDeathEvent
        .getNewUhcPlayerDeathEvent(
            UhcUserStore
                .getInstance()
                .getUhcUser(new MockPlayer())
        );

    // Check that the border isn't changed when there are only two remaining players
    game.storeGamePlayers();
    shrinker.onUhcPlayerDeath(event);
    assertEquals(0, world.getWorldBorder().getSize());

    // Check that the border isn't changed when the game isn't running
    MockPlayer player3 = new MockPlayer();
    server.addPlayer(player3);
    game.storeGamePlayers();
    game.setRunning(false);
    shrinker.onUhcPlayerDeath(event);
    assertEquals(0, world.getWorldBorder().getSize());

    // Check that the border is changed properly
    game.setRunning(true);
    shrinker.onUhcPlayerDeath(event);
    assertEquals(50, world.getWorldBorder().getSize());
    assertEquals(50, ((MockWorldBorder) world.getWorldBorder()).getResizeDuration());

    // Check that players are sent a message about the new border size
    assertNotNull(player1.getLastMessage());
    assertNotNull(player2.getLastMessage());
    assertNotNull(player3.getLastMessage());
  }
}