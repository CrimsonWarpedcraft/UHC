package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.Uhc;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.util.UhcLogger;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Prevents players outside UHC whitelist from joining.
 *
 * @author Copyright (c) zupmine. All Rights Reserved.
 */
public class JoinPreventer implements Listener {
  private final GameState game;
  private static final UhcLogger LOGGER = Uhc.getUhcLogger();

  /** Returns a new instance of a JoinPreventer. */
  public static JoinPreventer getJoinPreventer(GameState game) {
    return new JoinPreventer(Objects.requireNonNull(game));
  }

  private JoinPreventer(GameState game) {
    this.game = game;
  }

  /** Prevents players outside UHC whitelist from logging in. */
  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent event) {
    if (game.isRunning()
        && !game.getGamePlayers().contains(event.getPlayer().getUniqueId())) {
      event.disallow(
          PlayerLoginEvent.Result.KICK_WHITELIST,
          Component.text("UHC game in progress, no new players allowed")
      );

      LOGGER.log(
          UhcLogger.Level.INFO,
          "Blocked " + event.getPlayer().getName() + " from joining the game due to whitelist"
      );
    }
  }
}
