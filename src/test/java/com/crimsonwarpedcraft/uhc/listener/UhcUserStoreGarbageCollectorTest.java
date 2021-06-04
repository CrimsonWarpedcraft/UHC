package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertNotSame;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.user.UhcPlayer;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcUserStoreGarbageCollector.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcUserStoreGarbageCollectorTest {

  @Test
  void onPlayerQuit() {
    UhcUserStoreGarbageCollector gc =
        UhcUserStoreGarbageCollector.getUhcUserStoreGarbageCollector();
    MockPlayer player = new MockPlayer();

    // Store the player in the user store
    UhcPlayer uhcPlayer = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    // Run the event listener
    gc.onPlayerQuit(
        new PlayerQuitEvent(
            player,
            Component.empty(),
            PlayerQuitEvent.QuitReason.DISCONNECTED
        )
    );

    // Check to see that the player is no longer in the player store
    assertNotSame(
        uhcPlayer,
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
    );
  }
}