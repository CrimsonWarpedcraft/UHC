package com.crimsonwarpedcraft.uhc.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.junit.jupiter.api.Test;

/**
 * Tests for UhcPlayer.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class UhcPlayerTest {

  @Test
  void resetHealth() {
    MockPlayer player = new MockPlayer();
    player.setHealth(10);

    AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    assertNotNull(attribute);

    // Set the player's max health to 30
    attribute.setBaseValue(30);

    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetHealth();

    // Make sure the player's health got reset to default
    assertEquals(attribute.getDefaultValue(), player.getHealth());

    // Make sure the player's max health got reset to default
    assertEquals(attribute.getDefaultValue(), attribute.getBaseValue());
  }

  @Test
  void resetHunger() {
    MockPlayer player = new MockPlayer();
    player.setFoodLevel(10);

    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetHunger();

    assertEquals(20, player.getFoodLevel());
  }

  @Test
  void resetSaturation() {
    MockPlayer player = new MockPlayer();
    player.setSaturation(0);

    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetSaturation();

    assertEquals(5, player.getSaturation());
  }

  @Test
  void resetExhaustion() {
    MockPlayer player = new MockPlayer();
    player.setExhaustion(10);

    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetExhaustion();

    assertEquals(0, player.getExhaustion());
  }

  @Test
  void resetExp() {
    MockPlayer player = new MockPlayer();
    player.setExp(10);

    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetExp();

    assertEquals(0, player.getExp());
  }
}