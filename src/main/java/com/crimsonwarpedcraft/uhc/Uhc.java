package com.crimsonwarpedcraft.uhc;

import com.crimsonwarpedcraft.uhc.command.CommandRegister;
import com.crimsonwarpedcraft.uhc.command.StartCommand;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.game.RecipeManager;
import com.crimsonwarpedcraft.uhc.listener.EndDisabler;
import com.crimsonwarpedcraft.uhc.listener.JoinPreventer;
import com.crimsonwarpedcraft.uhc.listener.ListenerRegister;
import com.crimsonwarpedcraft.uhc.listener.RegenPreventer;
import com.crimsonwarpedcraft.uhc.listener.ResurrectPreventer;
import com.crimsonwarpedcraft.uhc.listener.ScoreboardCreator;
import com.crimsonwarpedcraft.uhc.listener.TradeDisabler;
import com.crimsonwarpedcraft.uhc.listener.UhcUserStoreGarbageCollector;
import com.crimsonwarpedcraft.uhc.listener.VillagerGuardian;
import com.crimsonwarpedcraft.uhc.util.UhcLogger;
import com.crimsonwarpedcraft.uhc.util.UhcLoggerFactory;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for UHC plugin.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Uhc extends JavaPlugin {
  // We only assign these here to prevent null issues in testing, we reassign them later
  private static UhcLoggerFactory factory = UhcLoggerFactory.getNewUhcLoggerFactory(null);
  private static UhcLogger LOGGER = factory.getNewUhcLogger();

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    // Reset factory now that we can use our logger
    resetFactory(getLogger());

    GameConfig gameConfig = GameConfig.getNewGameConfig(getConfig());
    GameState gameState = GameState.newGameState(getServer());

    // Try to overwrite config file
    // This lets us update the config file with default
    // values if we ever add more config options
    try {
      gameConfig.save(
          new File(getDataFolder(), "config.yml")
      );
    } catch (IOException e) {
      LOGGER.log(
          UhcLogger.Level.WARN,
          "Error writing config file, your config may be out of date!"
      );
    }

    RecipeManager
        .getNewRecipeManager(getServer())
        // Remove suspicious stew recipes
        .filter(
            recipe -> !(recipe instanceof ComplexRecipe)
                || !((ComplexRecipe) recipe)
                .getKey()
                .equals(NamespacedKey.minecraft("suspicious_stew"))
        )
        .apply();

    CommandRegister
        .newCommandRegister(this)
        .register(
            StartCommand.getStartCommand(gameState, gameConfig)
        );

    // Used for registering event listeners with
    ListenerRegister
        .getListenerRegister(this)
        // Register event listeners
        .registerListener(EndDisabler.getEndDisabler())
        .registerListener(JoinPreventer.getJoinPreventer(gameState))
        .registerListener(RegenPreventer.getRegenPreventer())
        .registerListener(ResurrectPreventer.getResurrectPreventer())
        .registerListener(ScoreboardCreator.getNewScoreboardCreator())
        .registerListener(TradeDisabler.getTradeDisabler())
        .registerListener(UhcUserStoreGarbageCollector.getUhcUserStoreGarbageCollector())
        .registerListener(VillagerGuardian.getVillagerGuardian());

    LOGGER.log(UhcLogger.Level.INFO, "Successfully enabled UHC!");
  }

  private static void resetFactory(Logger logger) {
    factory = UhcLoggerFactory.getNewUhcLoggerFactory(logger);
    LOGGER = factory.getNewUhcLogger();
  }

  /** Returns a UhcLogger instance that uses this plugin's logging system. */
  public static UhcLogger getUhcLogger() {
    return factory.getNewUhcLogger();
  }
}
