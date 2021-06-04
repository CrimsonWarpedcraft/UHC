package com.crimsonwarpedcraft.uhc.user;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

/**
 * Tests for UchUserStore.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcUserStoreTest {

  @Test
  void getUhcUser() {
    Player player = new MockPlayer();

    // Get a UhcPlayer instance
    UhcPlayer uhcPlayer = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    // Make sure that we get the name instance after each call
    assertSame(
        uhcPlayer,
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
    );

    // Make sure that we don't get the same instance if we convert player to a CommandSender
    assertNotSame(
        uhcPlayer,
        UhcUserStore
            .getInstance()
            .getUhcUser(
                (CommandSender) player
            )
    );
  }

  @Test
  void removePlayer() {
    Player player = new MockPlayer();

    // Get a UhcPlayer instance
    UhcPlayer uhcPlayer = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    // Delete the instance from the store
    UhcUserStore
        .getInstance()
        .removePlayer(player);

    // Make sure we don't get the same object anymore
    assertNotEquals(
        uhcPlayer,
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
    );
  }
}