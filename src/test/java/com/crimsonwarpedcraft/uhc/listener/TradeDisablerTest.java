package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockAbstractVillager;
import com.crimsonwarpedcraft.uhc.mock.MockEntity;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for TradeDisabler.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class TradeDisablerTest {

  @Test
  void onVillagerInteract() {
    TradeDisabler disabler = TradeDisabler.getTradeDisabler();

    // Make sure that events with abstract villagers are cancelled. */
    PlayerInteractEntityEvent villagerEvent = new PlayerInteractEntityEvent(
        new MockPlayer(),
        new MockAbstractVillager()
    );
    disabler.onVillagerInteract(villagerEvent);
    assertTrue(villagerEvent.isCancelled());

    // Make sure that events with entities are not cancelled. */
    PlayerInteractEntityEvent entityEvent = new PlayerInteractEntityEvent(
        new MockPlayer(),
        new MockEntity()
    );
    disabler.onVillagerInteract(entityEvent);
    assertFalse(entityEvent.isCancelled());
  }
}