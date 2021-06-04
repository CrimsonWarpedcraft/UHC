package com.crimsonwarpedcraft.uhc.listener;

import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Removes players from the UchUserStore when they are no longer needed.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcUserStoreGarbageCollector implements Listener {

  public static UhcUserStoreGarbageCollector getUhcUserStoreGarbageCollector() {
    return new UhcUserStoreGarbageCollector();
  }

  private UhcUserStoreGarbageCollector() {
  }

  /** Removes players from the user store when players leave the game. */
  @EventHandler(priority = EventPriority.MONITOR)
  public void onPlayerQuit(PlayerQuitEvent event) {
    // Remove player from user store
    UhcUserStore
        .getInstance()
        .removePlayer(event.getPlayer());
  }
}
