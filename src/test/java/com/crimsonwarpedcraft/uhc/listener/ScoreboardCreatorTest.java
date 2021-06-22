package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockScoreboard;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Objective;
import org.junit.jupiter.api.Test;

/**
 * Tests for ScoreboardCreator.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ScoreboardCreatorTest {

  @Test
  void onPlayerJoin() {
    MockScoreboard scoreboard = new MockScoreboard();
    MockPlayer player = new MockPlayer();
    player.setName("player1");
    player.setHealth(20);
    player.setScoreboard(scoreboard);
    MockServer server = new MockServer();
    server.addPlayer(player);

    PlayerJoinEvent event = new PlayerJoinEvent(player, Component.text(""));

    // Check that the objective was properly set on the player's scoreboard
    ScoreboardCreator
        .getNewScoreboardCreator()
        .onPlayerJoin(event);
    assertEquals(1, player.getScoreboard().getObjectives().size());

    // Check that the player's scoreboard was updated
    Objective objective = player.getScoreboard().getObjective("showhealth");
    assertEquals(Component.empty().toString(), player.getPlayerListHeader());
    assertNotNull(objective);
    assertEquals(20, objective.getScore(player.getName()).getScore());
  }
}