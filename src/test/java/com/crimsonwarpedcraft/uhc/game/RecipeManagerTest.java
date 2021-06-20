package com.crimsonwarpedcraft.uhc.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.game.RecipeManager;
import com.crimsonwarpedcraft.uhc.mock.MockItemStack;
import com.crimsonwarpedcraft.uhc.mock.MockRecipe;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.junit.jupiter.api.Test;

/**
 * Tests for RecipeManager.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class RecipeManagerTest {

  @Test
  void getNewRecipeManager() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> RecipeManager.getNewRecipeManager(null)
    );
  }

  @Test
  void filter() {
    // Check NPE
    assertThrows(
        NullPointerException.class,
        () -> RecipeManager.getNewRecipeManager(new MockServer()).filter(null)
    );
  }

  @Test
  void apply() {
    MockServer server = new MockServer();
    Recipe recipe1 = new MockRecipe(
        new MockItemStack(Material.ACACIA_BOAT)
    );
    server.addRecipe(recipe1);
    Recipe recipe2 = new MockRecipe(
        new MockItemStack(Material.ACACIA_BUTTON)
    );
    server.addRecipe(recipe2);

    RecipeManager manager = RecipeManager
        .getNewRecipeManager(server)
        .apply();

    // Make sure the recipe hasn't been removed yet
    assertTrue(server.hasRecipe(recipe1));

    // Filter recipe1 out
    manager
        .filter(
            serverRecipe -> !serverRecipe.getResult().isSimilar(
                new MockItemStack(Material.ACACIA_BOAT)
            )
        )
        .apply();

    // Make sure recipe1 was removed
    assertFalse(server.hasRecipe(recipe1));

    // Make sure recipe2 is still present
    assertTrue(server.hasRecipe(recipe2));

    // Add new filter to manager
    manager
        .filter(
            serverRecipe -> !serverRecipe.getResult().isSimilar(
                new MockItemStack(Material.ACACIA_BUTTON)
            )
        )
        .apply();

    // Make sure recipe2 was removed
    assertFalse(server.hasRecipe(recipe2));
  }
}