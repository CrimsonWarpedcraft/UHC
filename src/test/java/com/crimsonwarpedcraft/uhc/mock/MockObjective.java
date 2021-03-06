package com.crimsonwarpedcraft.uhc.mock;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock class for Objective.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings({
    "NP_NONNULL_RETURN_VIOLATION",
    "EI_EXPOSE_REP",
    "EI_EXPOSE_REP2"
})
public class MockObjective implements Objective {
  private final String name;
  private final String criteria;
  private final Component displayName;
  private final RenderType renderType;
  private final Map<String, Objective> objectives;
  private final Map<String, Score> scores = new HashMap<>();

  /** Creates a new MockObjective instance with all required data. */
  public MockObjective(String name,
                       String criteria,
                       Component displayName,
                       RenderType renderType,
                       Map<String, Objective> objectives) {
    this.name = name;
    this.criteria = criteria;
    this.displayName = displayName;
    this.renderType = renderType;
    this.objectives = objectives;
  }

  @Override
  public @NotNull String getName() throws IllegalStateException {
    return name;
  }

  @Override
  public @NotNull Component displayName() throws IllegalStateException {
    return displayName;
  }

  @Override
  public void displayName(@Nullable Component displayName)
      throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public @NotNull String getDisplayName() throws IllegalStateException {
    return null;
  }

  @Override
  public void setDisplayName(@NotNull String displayName)
      throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public @NotNull String getCriteria() throws IllegalStateException {
    return criteria;
  }

  @Override
  public boolean isModifiable() throws IllegalStateException {
    return false;
  }

  @Override
  public @Nullable Scoreboard getScoreboard() {
    return null;
  }

  @Override
  public void unregister() throws IllegalStateException {
    objectives.remove(name);
  }

  @Override
  public void setDisplaySlot(@Nullable DisplaySlot slot) throws IllegalStateException {

  }

  @Override
  public @Nullable DisplaySlot getDisplaySlot() throws IllegalStateException {
    return null;
  }

  @Override
  public void setRenderType(@NotNull RenderType renderType) throws IllegalStateException {

  }

  @Override
  public @NotNull RenderType getRenderType() throws IllegalStateException {
    return renderType;
  }

  @Override
  public @NotNull Score getScore(@NotNull OfflinePlayer player)
      throws IllegalArgumentException, IllegalStateException {
    return null;
  }

  @Override
  public @NotNull Score getScore(@NotNull String entry)
      throws IllegalArgumentException, IllegalStateException {
    scores.putIfAbsent(entry, new MockScore());

    return scores.get(entry);
  }
}
