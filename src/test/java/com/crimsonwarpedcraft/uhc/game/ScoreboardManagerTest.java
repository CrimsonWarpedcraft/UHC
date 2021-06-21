package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockScoreboard;
import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
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
    UhcPlayer uhcPlayer = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    // Check if the scoreboard was properly configured
    ScoreboardManager manager = ScoreboardManager.getNewScoreboardManager();

    manager.createPlayerHealthScoreboard(uhcPlayer);
    Objective objective = scoreboard.getObjective("showhealth");
    assertNotNull(objective);
    assertEquals("health", objective.getCriteria());

    // Make sure no errors occur when running a second time
    manager.createPlayerHealthScoreboard(uhcPlayer);
  }
}