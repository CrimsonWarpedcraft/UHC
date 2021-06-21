package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockScoreboard;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import org.bukkit.scoreboard.Objective;
import org.junit.jupiter.api.Test;

/**
 * Tests for ScoreboardManager.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ScoreboardManagerTest {

  @Test
  void createPlayerHealthScoreboard() {
    MockScoreboard scoreboard = new MockScoreboard();
    MockPlayer player = new MockPlayer();
    player.setScoreboard(scoreboard);

    // Check if the scoreboard was properly configured
    ScoreboardManager
        .getNewScoreboardManager()
        .createPlayerHealthScoreboard(
            UhcUserStore
                .getInstance()
                .getUhcUser(player)
        );
    Objective objective = scoreboard.getObjective("showhealth");
    assertNotNull(objective);
    assertEquals("health", objective.getCriteria());
  }
}