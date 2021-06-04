package com.crimsonwarpedcraft.uhc;

/**
 * Stores information about the state of the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class GameState {
  private boolean running;

  /** Returns a new GameState instance. */
  public static GameState newGameState() {
    return new GameState();
  }

  private GameState() {
    running = false;
  }

  /**
   * Set whether the game is running or not.
   *
   * @param running boolean representing whether the game is running or not
   * @return an instance of this object for method chaining
   */
  public GameState setRunning(boolean running) {
    this.running = running;

    return this;
  }

  /** Returns whether the game is running or not. */
  public boolean isRunning() {
    return running;
  }
}
