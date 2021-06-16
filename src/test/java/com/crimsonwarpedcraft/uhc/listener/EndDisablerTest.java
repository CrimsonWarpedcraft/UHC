package com.crimsonwarpedcraft.uhc.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crimsonwarpedcraft.uhc.mock.MockBlock;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

/**
 * Tests for EndDisabler.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class EndDisablerTest {

  @Test
  void onPortalInteract() {
    EndDisabler disabler = EndDisabler.getEndDisabler();

    // Make sure end portal interactions are cancelled
    PlayerInteractEvent endBlockEvent = new PlayerInteractEvent(
        new MockPlayer(),
        Action.LEFT_CLICK_BLOCK,
        new ItemStack(Material.AIR),
        new MockBlock(Material.END_PORTAL_FRAME),
        BlockFace.UP
    );
    disabler.onPortalInteract(endBlockEvent);
    assertEquals(Event.Result.DENY, endBlockEvent.useInteractedBlock());

    // Make sure other interactions are not cancelled
    PlayerInteractEvent otherBlockEvent = new PlayerInteractEvent(
        new MockPlayer(),
        Action.LEFT_CLICK_BLOCK,
        new ItemStack(Material.AIR),
        new MockBlock(Material.STONE),
        BlockFace.UP
    );
    disabler.onPortalInteract(otherBlockEvent);
    assertEquals(Event.Result.ALLOW, otherBlockEvent.useInteractedBlock());
  }
}