package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerJoinEvent;
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
    player.setScoreboard(scoreboard);
    PlayerJoinEvent event = new PlayerJoinEvent(player, Component.text(""));

    // Check that the objective was properly set on the player's scoreboard
    ScoreboardCreator
        .getNewScoreboardCreator()
        .onPlayerJoin(event);
    assertEquals(1, player.getScoreboard().getObjectives().size());
  }
}