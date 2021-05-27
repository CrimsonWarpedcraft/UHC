package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Prevents villagers from being damaged.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class VillagerGuardian implements Listener {
  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    // TODO If the event.getEntity() is a Villager, then we cancel the event
  }
}
