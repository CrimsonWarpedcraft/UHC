package com.crimsonwarpedcraft.uhc.user;

/**
 * Represents possible game modes for players.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public enum GameMode {
  SURVIVAL(org.bukkit.GameMode.SURVIVAL),
  CREATIVE(org.bukkit.GameMode.CREATIVE),
  ADVENTURE(org.bukkit.GameMode.ADVENTURE),
  SPECTATOR(org.bukkit.GameMode.SPECTATOR);

  private final org.bukkit.GameMode gameMode;

  GameMode(org.bukkit.GameMode gameMode) {
    this.gameMode = gameMode;
  }

  org.bukkit.GameMode getGameMode() {
    return gameMode;
  }
}
