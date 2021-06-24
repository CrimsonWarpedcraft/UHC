package com.crimsonwarpedcraft.uhc.game;

import com.crimsonwarpedcraft.uhc.Uhc;
import com.crimsonwarpedcraft.uhc.util.UhcLogger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.bukkit.Server;
import org.bukkit.inventory.Recipe;

/**
 * Allows for the management of in-game recipes.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class RecipeManager {
  private static final UhcLogger LOGGER = Uhc.getUhcLogger();
  private final List<Recipe> recipes;
  private final Server server;
  private int added;
  private int removed;

  /** Returns a new instance of a RecipeManager. */
  public static RecipeManager getNewRecipeManager(Server server) {
    return new RecipeManager(Objects.requireNonNull(server));
  }

  private RecipeManager(Server server) {
    this.server = server;
    added = 0;
    removed = 0;
    recipes = new LinkedList<>();
    server.recipeIterator().forEachRemaining(recipes::add);
  }

  /**
   * Adds a filter to the RecipeManager.
   *
   * <p>The filter is used to remove recipes which do not match. A recipe must match all
   * filters to be included in the final result.</p>
   */
  public RecipeManager filter(Predicate<Recipe> predicate) {
    int recipeCount = recipes.size();
    recipes.removeIf(predicate.negate());
    removed += recipeCount - recipes.size();

    return this;
  }

  /**
   * Adds a Recipe to the RecipeManager.
   *
   * <p>The filter is used to remove recipes which do not match. A recipe must match all
   * filters to be included in the final result.</p>
   */
  public RecipeManager addRecipe(Recipe recipe) {
    recipes.add(Objects.requireNonNull(recipe));
    added++;

    return this;
  }

  /** Sets the available recipes to the filtered recipes. */
  public void apply() {
    // Get rid of old recipes and add retained ones
    server.clearRecipes();
    recipes.forEach(server::addRecipe);

    LOGGER.log(UhcLogger.Level.INFO, "Added  " + added + ", removed " + removed + " recipe(s)");
  }
}
