package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockEntity;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for RegenPreventer.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class RegenPreventerTest {

  @Test
  void onPlayerRegainHealth() {
    RegenPreventer preventer = RegenPreventer.getRegenPreventer();

    // Check that only players are cancelled
    EntityRegainHealthEvent entityEvent = new EntityRegainHealthEvent(
        new MockEntity(),
        0,
        EntityRegainHealthEvent.RegainReason.REGEN
    );
    preventer.onPlayerRegainHealth(entityEvent);
    assertFalse(entityEvent.isCancelled());

    // Check that health potions are not cancelled
    EntityRegainHealthEvent playerMagicEvent = new EntityRegainHealthEvent(
        new MockEntity(),
        0,
        EntityRegainHealthEvent.RegainReason.MAGIC_REGEN
    );
    preventer.onPlayerRegainHealth(playerMagicEvent);
    assertFalse(playerMagicEvent.isCancelled());

    // Check that regen is cancelled
    EntityRegainHealthEvent playerRegenEvent = new EntityRegainHealthEvent(
        new MockEntity(),
        0,
        EntityRegainHealthEvent.RegainReason.REGEN
    );
    preventer.onPlayerRegainHealth(playerRegenEvent);
    assertTrue(playerRegenEvent.isCancelled());
  }
}