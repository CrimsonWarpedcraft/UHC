package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

/**
 * Prevents players from being resurrected.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ResurrectPreventer implements Listener {
  /** Returns a new instance of a ResurrectPreventer. */
  public static ResurrectPreventer getResurrectPreventer() {
    return new ResurrectPreventer();
  }

  private ResurrectPreventer() {
  }

  /** Cancels EntityResurrectEvents from players. */
  @EventHandler
  public void onEntityResurrectEvent(EntityResurrectEvent event) {
    if (event.getEntity() instanceof Player) {
      event.setCancelled(true);
    }
  }
}