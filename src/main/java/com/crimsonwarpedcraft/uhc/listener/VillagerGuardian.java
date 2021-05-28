package com.crimsonwarpedcraft.uhc.listener;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Prevents villagers from being damaged.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class VillagerGuardian implements Listener {
  /** Prevent any AbstractVillagers (Villagers/WanderingVillagers) from taking damage. */
  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    if (event.getEntity() instanceof AbstractVillager) {
      event.setCancelled(true);
    }
  }
}
