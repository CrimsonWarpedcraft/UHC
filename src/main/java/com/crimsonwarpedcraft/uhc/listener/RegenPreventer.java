package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

/**
 * Prevents player regeneration.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class RegenPreventer implements Listener {
  /** Returns a new instance of a RegenPreventer. */
  public static RegenPreventer getRegenPreventer() {
    return new RegenPreventer();
  }

  private RegenPreventer() {
  }

  /** Prevent any players from regaining health. */
  @EventHandler
  public void onPlayerRegainHealth(EntityRegainHealthEvent event) {
    // TODO: If the event's entity is a player, and the event.getRegainReason() is REGEN, cancel it
  }
}
