package com.crimsonwarpedcraft.uhc.mock;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Mock object for ItemStack.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockItemStack extends ItemStack {
  private final Material type;

  public MockItemStack(Material material) {
    super(material);
    this.type = material;
  }

  @Override
  public boolean isSimilar(@Nullable ItemStack stack) {
    if (stack == null) {
      return false;
    }
    if (stack == this) {
      return true;
    }

    return this.type == stack.getType();
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
