package com.crimsonwarpedcraft.uhc.event;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockUhcPlayerEvent;
import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcPlayerEvent.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcPlayerEventTest {

  @Test
  void getPlayer() {
    UhcPlayer player = UhcUserStore
        .getInstance()
        .getUhcUser(new MockPlayer());

    // Check that the event player is stored properly
    MockUhcPlayerEvent event = new MockUhcPlayerEvent(player);
    assertEquals(player, event.getPlayer());
  }
}