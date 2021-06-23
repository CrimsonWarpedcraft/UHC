package com.crimsonwarpedcraft.uhc.event;

import com.crimsonwarpedcraft.uhc.user.UhcPlayer;

/**
 * Represents classes that are about UhcPlayers.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public abstract class UhcPlayerEvent extends UhcEvent {
  private final UhcPlayer player;

  public UhcPlayerEvent(UhcPlayer player) {
    this.player = player;
  }

  /** Returns the UhcPlayer for this event. */
  public UhcPlayer getPlayer() {
    return player;
  }
}
