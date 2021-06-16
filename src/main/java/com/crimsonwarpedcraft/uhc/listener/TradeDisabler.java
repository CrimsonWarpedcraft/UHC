package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * Disables villager's trades.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class TradeDisabler implements Listener {
  /** Returns a new instance of a TradeDisabler. */
  public static TradeDisabler getTradeDisabler() {
    return new TradeDisabler();
  }

  private TradeDisabler() {
  }

  /** Prevents players from interacting (Right Clicking) with AbstractVillagers
   * (Villagers/Wandering Villagers).
   */
  @EventHandler
  public void onVillagerInteract(PlayerInteractEntityEvent event) {
    if (event.getRightClicked() instanceof AbstractVillager) {
      event.setCancelled(true);
    }
  }
}
