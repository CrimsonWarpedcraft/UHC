package com.crimsonwarpedcraft.uhc.mock;

import com.crimsonwarpedcraft.uhc.event.UhcPlayerEvent;
import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Mock object for UhcPlayerEvent.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockUhcPlayerEvent extends UhcPlayerEvent {
  private static final HandlerList handlers = new HandlerList();

  public MockUhcPlayerEvent(UhcPlayer player) {
    super(player);
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
