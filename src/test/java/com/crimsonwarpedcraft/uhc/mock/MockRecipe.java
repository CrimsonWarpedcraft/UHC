package com.crimsonwarpedcraft.uhc.mock;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

/**
 * Mock object for Recipe.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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
