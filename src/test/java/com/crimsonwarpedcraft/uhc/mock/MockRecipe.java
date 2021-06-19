package com.crimsonwarpedcraft.uhc.mock;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

/**
 * Mock object for Recipe.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockRecipe implements Recipe {
  private final ItemStack result;

  public MockRecipe(ItemStack result) {
    this.result = result;
  }

  @Override
  public @NotNull ItemStack getResult() {
    return result;
  }
}
