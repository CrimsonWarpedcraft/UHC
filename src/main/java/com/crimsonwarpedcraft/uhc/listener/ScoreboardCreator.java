package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.game.ScoreboardManager;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Creates scoreboards for players.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ScoreboardCreator implements Listener {
  /** Returns a new instance of a ScoreboardCreator. */
  public static ScoreboardCreator getNewScoreboardCreator() {
    return new ScoreboardCreator();
  }

  private ScoreboardCreator() {
  }

  /** Creates scoreboards for players as they join the game. */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    // Create a health scoreboard for the player
    ScoreboardManager
        .getNewScoreboardManager()
        .createPlayerHealthScoreboard(
            UhcUserStore
                .getInstance()
                .getUhcUser(event.getPlayer())
        );
  }
}
