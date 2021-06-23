package com.crimsonwarpedcraft.uhc.game;

import com.crimsonwarpedcraft.uhc.event.UhcEvent;
import com.crimsonwarpedcraft.uhc.user.UhcPlayerData;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
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
  private final HashMap<UUID, UhcPlayerData> gamePlayers;

  /** Returns a new GameState instance. */
  public static GameState newGameState(Server server) {
    return new GameState(Objects.requireNonNull(server));
  }

  private GameState(Server server) {
    running = false;
    this.server = server;
    gamePlayers = new HashMap<>();
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

  /** Stores current online players to game players Set. */
  public GameState storeGamePlayers() {
    getOnlinePlayers().forEach(
        player -> gamePlayers.put(player.getUniqueId(), UhcPlayerData.getNewUhcPlayerData())
    );

    return this;
  }

  /** Returns copy of Set of game players' UUIDs. */
  public Map<UUID, UhcPlayerData> getGamePlayers() {
    return new HashMap<>(gamePlayers);
  }

  /** Calls the current event. */
  public GameState callEvent(UhcEvent event) {
    server.getPluginManager().callEvent(event);

    return this;
  }
}
