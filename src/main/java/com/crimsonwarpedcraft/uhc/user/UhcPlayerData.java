package com.crimsonwarpedcraft.uhc.user;

/**
 * Data about UhcPlayers.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcPlayerData {
  private boolean isAlive;

  /** Returns a new instance of UchPlayerData. */
  public static UhcPlayerData getNewUhcPlayerData() {
    return new UhcPlayerData();
  }

  private UhcPlayerData() {
    isAlive = true;
  }

  /** Returns whether the player is alive or not. */
  public boolean isAlive() {
    return isAlive;
  }

  /** Sets whether the player is alive or not. */
  public void setAlive(boolean alive) {
    isAlive = alive;
  }
}
