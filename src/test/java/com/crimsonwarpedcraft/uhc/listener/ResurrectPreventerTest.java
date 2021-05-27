package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockLivingEntity;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for ResurrectPreventer.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ResurrectPreventerTest {

  @Test
  void onEntityResurrectEvent() {
    ResurrectPreventer resurrectPreventer = ResurrectPreventer.getResurrectPreventer();

    // Create an event with mock living entity data
    EntityResurrectEvent entityEvent = new EntityResurrectEvent(new MockLivingEntity());

    resurrectPreventer.onEntityResurrectEvent(entityEvent);

    // Since we don't want illager resurrects to be cancelled, make sure we allow it
    assertFalse(entityEvent.isCancelled());

    // Create an event with mock player data
    EntityResurrectEvent playerEvent = new EntityResurrectEvent(new MockPlayer());

    resurrectPreventer.onEntityResurrectEvent(playerEvent);

    // Make sure we block player resurrects
    assertTrue(playerEvent.isCancelled());
  }
}