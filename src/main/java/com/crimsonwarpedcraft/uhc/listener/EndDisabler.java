package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Prevents player from spawning end portal.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class EndDisabler implements Listener {
  /** Returns a new instance of a EndDisabler. */
  public static EndDisabler getEndDisabler() {
    return new EndDisabler();
  }

  private EndDisabler() {
  }

  /** Prevents players from interacting with end portal frames. */
  @EventHandler
  public void onPortalInteract(PlayerInteractEvent event) {
    Block blockData = event.getClickedBlock();
    if (blockData != null && blockData.getType() == Material.END_PORTAL_FRAME) {
      event.setCancelled(true);
    }
  }
}