package com.crimsonwarpedcraft.uhc.event;

import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
import java.util.Objects;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * An event thrown when a player dies during the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcPlayerDeathEvent extends UhcPlayerEvent implements Cancellable {
  private static final HandlerList handlers = new HandlerList();
  private boolean cancelled;

  public static UhcPlayerDeathEvent getNewUhcPlayerDeathEvent(UhcPlayer player) {
    return new UhcPlayerDeathEvent(Objects.requireNonNull(player));
  }

  private UhcPlayerDeathEvent(UhcPlayer player) {
    super(player);
    cancelled = false;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public void setCancelled(boolean cancel) {
    this.cancelled = cancel;
  }

  @Override
  public @NotNull HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }
}
