package com.crimsonwarpedcraft.uhc.game;

import com.crimsonwarpedcraft.uhc.Uhc;
import com.crimsonwarpedcraft.uhc.util.UhcLogger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
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
  private final List<Predicate<Recipe>> filters;
  private final Server server;

  /** Returns a new instance of a RecipeManager. */
  public static RecipeManager getNewRecipeManager(Server server) {
    return new RecipeManager(Objects.requireNonNull(server));
  }

  private RecipeManager(Server server) {
    this.server = server;
    filters = new LinkedList<>();
  }

  /**
   * Adds a filter to the RecipeManager.
   *
   * <p>The filter is used to remove recipes which do not match. A recipe must match all
   * filters to be included in the final result.</p>
   */
  public RecipeManager filter(Predicate<Recipe> predicate) {
    filters.add(Objects.requireNonNull(predicate));

    return this;
  }

  /** Sets the available recipes to the filtered recipes. */
  public void apply() {
    List<Recipe> newRecipes = new LinkedList<>();

    AtomicInteger originalRecipes = new AtomicInteger();
    server
        .recipeIterator()
        // For available each recipe
        .forEachRemaining(
            recipe -> {
              originalRecipes.addAndGet(1);
              if (
                  // Combine all the previous filters into one boolean for this recipe using reduce
                  filters
                      .stream()
                      .reduce(Predicate::and)
                      .orElse(x -> true)
                      .test(recipe)
              ) {
                newRecipes.add(recipe);
              }
            }
        );

    // Get rid of old recipes and add retained ones
    server.clearRecipes();
    newRecipes.forEach(server::addRecipe);

    int removed = originalRecipes.getAcquire() - newRecipes.size();
    LOGGER.log(UhcLogger.Level.INFO, "Removed " + removed + " recipe(s)");
  }
}
