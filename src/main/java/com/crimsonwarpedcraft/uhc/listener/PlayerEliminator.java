package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import java.util.Objects;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Listener for when players are eliminated from the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class PlayerEliminator implements Listener {
  private final GameState game;

  /** Returns a new PlayerEliminator instance. */
  public static PlayerEliminator getNewPlayerEliminator(GameState game) {
    return new PlayerEliminator(Objects.requireNonNull(game));
  }

  private PlayerEliminator(GameState game) {
    this.game = game;
  }

  /** Listens for when players die. */
  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerDeath(PlayerDeathEvent event) {
    if (game.isRunning()) {
      game
          .getGamePlayers()
          .get(
              UhcUserStore
                  .getInstance()
                  .getUhcUser(event.getEntity())
                  .getUuid()
          )
          .setAlive(false);
    }
  }
}
