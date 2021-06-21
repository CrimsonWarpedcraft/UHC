package com.crimsonwarpedcraft.uhc.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.crimsonwarpedcraft.uhc.mock.MockItemStack;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockScoreboard;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.scoreboard.Scoreboard;
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

  @Test
  void sendPlayerListHeaderAndFooter() {
    MockPlayer player = new MockPlayer();

    Component text = Component.text("test");
    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .sendPlayerListHeaderAndFooter(text, Component.empty());

    assertEquals(text.toString(), player.getPlayerListHeader());
  }

  @Test
  void getScoreboard() {
    MockPlayer player = new MockPlayer();
    Scoreboard scoreboard = new MockScoreboard();
    player.setScoreboard(scoreboard);

    assertEquals(
        scoreboard,
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
            .getScoreboard()
    );
  }

  @Test
  void resetInventory() {
    MockPlayer player = new MockPlayer();
    player.getInventory().addItem(new MockItemStack(Material.ACACIA_BOAT));

    // Check that the player's inventory was cleared
    UhcUserStore
        .getInstance()
        .getUhcUser(player)
        .resetInventory();
    assertEquals(0, player.getInventory().getSize());
  }
}