package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.event.UhcPlayerDeathEvent;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.game.WorldConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.lang.Math;

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
    return new BorderShrinker(game, config);
  }

  private BorderShrinker(GameState game, GameConfig config) {
    this.game = game;
    this.config = config;
  }

  // Returns expected border size given the # of players alive
  private double borderPos(long aliveCount) {
    double deathShrink = (
        config.getBorderStartSize() - config.getBorderFinalSize()
    )
        / (
            game.getGamePlayers().size() - 2
    );
    return config.getBorderFinalSize() + ((aliveCount - 2) * deathShrink);
  }

  // Returns the necessary time for the border to shrink at a constant rate
  private long moveTime(double position, double shrinkRate) {
    return Math.round(
        Math.abs(
            WorldConfig
                .getWorldConfig(game.getWorld(config.getMainWorldName()))
                .getBorderSize()
                - position
        )
            / shrinkRate
    );
  }

  /** Shrinks world border at a constant rate when a Uhc player dies. */
  @EventHandler
  public void onUhcPlayerDeath(UhcPlayerDeathEvent event) {

    int aliveCount = game.getAlivePlayers().size();
    if (aliveCount > 2) {

      WorldConfig
          .getWorldConfig(game.getWorld(config.getMainWorldName()))
          .setBorderSize(
              borderPos(aliveCount),
              moveTime(
                  borderPos(aliveCount),
                  config.getBorderShrinkRate()
              )
          );
    }
  }
}
