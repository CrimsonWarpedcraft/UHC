package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockAbstractVillager;
import com.crimsonwarpedcraft.uhc.mock.MockEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for VillagerGuardian event listener.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class VillagerGuardianTest {

  @Test
  void onEntityDamage() {
    VillagerGuardian villagerGuardian = VillagerGuardian.getVillagerGuardian();

    // Create an event with mock entity data
    EntityDamageEvent entityEvent = new EntityDamageEvent(
        new MockEntity(),
        EntityDamageEvent.DamageCause.CUSTOM,
        0
    );

    villagerGuardian.onEntityDamage(entityEvent);

    // Ensure that we don't cancel all entity damage events
    assertFalse(entityEvent.isCancelled());

    // Create an event with mock villager data
    EntityDamageEvent villagerEvent = new EntityDamageEvent(
        new MockAbstractVillager(),
        EntityDamageEvent.DamageCause.CUSTOM,
        0
    );

    villagerGuardian.onEntityDamage(villagerEvent);

    // Ensure that we cancel all AbstractVillager damage events
    assertTrue(villagerEvent.isCancelled());
  }
}