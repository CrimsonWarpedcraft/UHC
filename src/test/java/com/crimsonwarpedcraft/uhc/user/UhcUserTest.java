package com.crimsonwarpedcraft.uhc.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcUser.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcUserTest {

  @Test
  void hasAdminPermission() {
    MockPlayer player = new MockPlayer();
    UhcUser user = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    // Make sure that the user does not have admin permission
    player.setPermission("uhc.admin", false);
    assertFalse(user.hasAdminPermission());

    // Make sure that the uses does have admin permission
    player.setPermission("uhc.admin", true);
    assertTrue(user.hasAdminPermission());
  }

  @Test
  void sendMessage() {
    MockPlayer player = new MockPlayer();
    UhcUser user = UhcUserStore
        .getInstance()
        .getUhcUser(player);

    user.sendMessage(Component.text("test"));

    assertEquals(Component.text("test"), player.getLastMessage());
  }
}