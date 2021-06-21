package com.crimsonwarpedcraft.uhc.game;

import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Manages player's scoreboards.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ScoreboardManager {

  /** Returns a new instance of a ScoreboardManager. */
  public static ScoreboardManager getNewScoreboardManager() {
    return new ScoreboardManager();
  }

  private ScoreboardManager() {
  }

  /** Creates a player health objective on the player's scoreboard. */
  public ScoreboardManager createPlayerHealthScoreboard(UhcPlayer player) {
    // The text to be shown on the scoreboard
    TextComponent text = Component.empty()
        .color(NamedTextColor.RED)
        .append(Component.text("\u2665")); // Unicode black heart

    // Creates an objective on the player's scoreboard
    Scoreboard board = player.getScoreboard();
    Objective objective = board.getObjective("showhealth");

    if (objective != null) {
      objective.unregister();
    }

    objective = board.registerNewObjective("showhealth", "health", text,
        RenderType.HEARTS);
    objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);

    // Updates the scoreboard
    player.sendPlayerListHeaderAndFooter(Component.empty(), Component.empty());

    return this;
  }
}
