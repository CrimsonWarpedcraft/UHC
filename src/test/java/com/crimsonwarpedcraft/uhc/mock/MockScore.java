package com.crimsonwarpedcraft.uhc.mock;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock object for Score.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings("NP_NONNULL_RETURN_VIOLATION")
public class MockScore implements Score {
  private int score = 0;

  @Override
  public @NotNull OfflinePlayer getPlayer() {
    return null;
  }

  @Override
  public @NotNull String getEntry() {
    return null;
  }

  @Override
  public @NotNull Objective getObjective() {
    return null;
  }

  @Override
  public int getScore() throws IllegalStateException {
    return score;
  }

  @Override
  public void setScore(int score) throws IllegalStateException {
    this.score = score;
  }

  @Override
  public boolean isScoreSet() throws IllegalStateException {
    return false;
  }

  @Override
  public @Nullable Scoreboard getScoreboard() {
    return null;
  }
}
