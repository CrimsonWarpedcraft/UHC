package com.crimsonwarpedcraft.uhc;

import java.util.Collection;
import java.util.Objects;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Stores information about the state of the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class GameState {
  private boolean running;
  private final Server server;

  /** Returns a new GameState instance. */
  public static GameState newGameState(Server server) {
    return new GameState(Objects.requireNonNull(server));
  }

  private GameState(Server server) {
    running = false;
    this.server = server;
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

  /** Returns a Collection of the currently online players. */
  public Collection<? extends Player> getOnlinePlayers() {
    return server.getOnlinePlayers();
  }

  /** Returns a world by its name. */
  public World getWorld(String name) {
    return server.getWorld(Objects.requireNonNull(name));
  }
}
