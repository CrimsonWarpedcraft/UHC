package com.crimsonwarpedcraft.uhc.mock;

import com.crimsonwarpedcraft.uhc.event.UhcEvent;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Mock object for UhcEvent.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockUhcEvent extends UhcEvent {
  private static final HandlerList handlers = new HandlerList();

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
