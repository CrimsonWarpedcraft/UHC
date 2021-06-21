package com.crimsonwarpedcraft.uhc.mock;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock object for Scoreboard.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings("NP_NONNULL_RETURN_VIOLATION")
public class MockScoreboard implements Scoreboard {
  HashMap<String, Objective> objectives = new HashMap<>();

  @Override
  public @NotNull Objective registerNewObjective(@NotNull String name, @NotNull String criteria)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Objective registerNewObjective(@NotNull String name,
                                                 @NotNull String criteria,
                                                 @Nullable Component displayName)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Objective registerNewObjective(@NotNull String name,
                                                 @NotNull String criteria,
                                                 @Nullable Component displayName,
                                                 @NotNull RenderType renderType)
      throws IllegalArgumentException {
    Objective objective = new MockObjective(name, criteria, displayName, renderType);
    objectives.put(name, objective);
    return objective;
  }

  @Override
  public @NotNull Objective registerNewObjective(@NotNull String name,
                                                 @NotNull String criteria,
                                                 @NotNull String displayName)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Objective registerNewObjective(@NotNull String name,
                                                 @NotNull String criteria,
                                                 @NotNull String displayName,
                                                 @NotNull RenderType renderType)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @Nullable Objective getObjective(@NotNull String name) throws IllegalArgumentException {
    return objectives.get(name);
  }

  @Override
  public @Nullable Objective getObjective(@NotNull DisplaySlot slot)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Set<Objective> getObjectivesByCriteria(@NotNull String criteria)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Set<Objective> getObjectives() {
    return new HashSet<>(objectives.values());
  }

  @Override
  public @NotNull Set<Score> getScores(@NotNull OfflinePlayer player)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Set<Score> getScores(@NotNull String entry) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void resetScores(@NotNull OfflinePlayer player) throws IllegalArgumentException {

  }

  @Override
  public void resetScores(@NotNull String entry) throws IllegalArgumentException {

  }

  @Override
  public @Nullable Team getPlayerTeam(@NotNull OfflinePlayer player)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public @Nullable Team getEntryTeam(@NotNull String entry) throws IllegalArgumentException {
    return null;
  }

  @Override
  public @Nullable Team getTeam(@NotNull String teamName) throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Set<Team> getTeams() {
    return null;
  }

  @Override
  public @NotNull Team registerNewTeam(@NotNull String name) throws IllegalArgumentException {
    return null;
  }

  @Override
  public @NotNull Set<OfflinePlayer> getPlayers() {
    return null;
  }

  @Override
  public @NotNull Set<String> getEntries() {
    return null;
  }

  @Override
  public void clearSlot(@NotNull DisplaySlot slot) throws IllegalArgumentException {

  }
}
