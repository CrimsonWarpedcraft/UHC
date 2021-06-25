package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.event.UhcPlayerDeathEvent;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.game.WorldConfig;
import java.lang.Math;
import java.util.Objects;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Shrinks world border after a player dies.
 *
 * @author Copyright (c) Daniel Azancot. All Rights Reserved.
 */
public class BorderShrinker implements Listener {
  private final GameState game;
  private final GameConfig config;

  /** Returns a new instance of a BorderShrinker. */
  public static BorderShrinker getBorderShrinker(GameState game, GameConfig config) {
    return new BorderShrinker(Objects.requireNonNull(game), Objects.requireNonNull(config));
  }

  private BorderShrinker(GameState game, GameConfig config) {
    this.game = game;
    this.config = config;
  }

  // Returns expected border size given the # of players alive
  private double borderPos() {
    double borderSizeDelta = config.getBorderStartSize() - config.getBorderFinalSize();
    double deathShrink = borderSizeDelta / (game.getGamePlayers().size() - 2);
    int aliveCount = game.getAlivePlayers().size() - 3;
    return config.getBorderFinalSize() + aliveCount * deathShrink;
  }

  // Returns the necessary time for the border to shrink at a constant rate
  private long moveTime(double position) {
    double borderSize = WorldConfig
        .getWorldConfig(
            game.getWorld(
                config.getMainWorldName()
            )
        )
        .getBorderSize();

    return Math.round(
        Math.abs(borderSize - position) / config.getBorderShrinkRate()
    );
  }

  /** Shrinks world border at a constant rate when a Uhc player dies. */
  @EventHandler
  public void onUhcPlayerDeath(UhcPlayerDeathEvent event) {

    if (!game.isRunning() || game.getAlivePlayers().size() - 1 < 2) {
      return;
    }

    double position = borderPos();
    WorldConfig
        .getWorldConfig(game.getWorld(config.getMainWorldName()))
        .setBorderSize(
            position,
            moveTime(position)
        );
    //TODO send message to players

  }
}
