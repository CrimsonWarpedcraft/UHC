package com.crimsonwarpedcraft.uhc.listener;

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
    MockPluginManager manager = new MockPluginManager();
    ListenerRegister register = new ListenerRegister(new MockPlugin(), manager);

    // Register a mock listener with the register
    Listener listener = new MockListener();
    register.registerListener(listener);

    // Check that the listener was registered successfully
    assertTrue(manager.getListeners().contains(listener));
  }
}