package com.crimsonwarpedcraft.uhc.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcPlayerDeathEvent.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcPlayerDeathEventTest {

  @Test
  void getNewUhcPlayerDeathEvent() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> UhcPlayerDeathEvent.getNewUhcPlayerDeathEvent(null)
    );
  }

  @Test
  void isCancelled() {
    UhcPlayerDeathEvent event = UhcPlayerDeathEvent
        .getNewUhcPlayerDeathEvent(
            UhcUserStore
                .getInstance()
                .getUhcUser(new MockPlayer())
        );

    // Check that disable is not cancelled by default
    assertFalse(event.isCancelled());

    // Check that event can be cancelled
    event.setCancelled(true);
    assertTrue(event.isCancelled());
  }

  @Test
  void setCancelled() {
    UhcPlayerDeathEvent event = UhcPlayerDeathEvent
        .getNewUhcPlayerDeathEvent(
            UhcUserStore
                .getInstance()
                .getUhcUser(new MockPlayer())
        );

    // Check that event can be cancelled
    event.setCancelled(true);
    assertTrue(event.isCancelled());
    event.setCancelled(false);
    assertFalse(event.isCancelled());
  }

  @Test
  void getHandlers() {
    // Check not null
    assertNotNull(
        UhcPlayerDeathEvent
            .getNewUhcPlayerDeathEvent(
                UhcUserStore
                    .getInstance()
                    .getUhcUser(new MockPlayer())
            )
            .getHandlers()
    );
  }

  @Test
  void getHandlerList() {
    // Check not null
    assertNotNull(UhcPlayerDeathEvent.getHandlerList());
  }
}