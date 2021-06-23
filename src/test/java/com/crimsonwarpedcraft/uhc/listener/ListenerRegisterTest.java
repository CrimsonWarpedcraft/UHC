package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.mock.MockListener;
import com.crimsonwarpedcraft.uhc.mock.MockPlugin;
import com.crimsonwarpedcraft.uhc.mock.MockPluginManager;
import org.bukkit.event.Listener;
import org.junit.jupiter.api.Test;

/**
 * Tests for ListenerRegister.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class ListenerRegisterTest {

  @Test
  void registerListener() {
    MockPlugin plugin = new MockPlugin();
    ListenerRegister register = ListenerRegister.getListenerRegister(plugin);

    // Check NPE
    assertThrows(NullPointerException.class, () -> register.registerListener(null));

    // Register a mock listener with the register
    Listener listener = new MockListener();
    register.registerListener(listener);

    // Check that the listener was registered successfully
    assertTrue(
        ((MockPluginManager) plugin
            .getServer()
            .getPluginManager())
            .getListeners()
            .contains(listener)
    );
  }

  @Test
  void getListenerRegister() {
    // Check NPE
    assertThrows(NullPointerException.class, () -> ListenerRegister.getListenerRegister(null));
  }
}