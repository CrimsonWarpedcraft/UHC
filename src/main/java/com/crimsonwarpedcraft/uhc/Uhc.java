package com.crimsonwarpedcraft.uhc;

import com.crimsonwarpedcraft.cwcommons.command.PaperCommandRegister;
import com.crimsonwarpedcraft.cwcommons.config.ConfigurationException;
import com.crimsonwarpedcraft.cwcommons.listener.BukkitListenerRegister;
import com.crimsonwarpedcraft.uhc.command.StartCommand;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.game.RecipeManager;
import com.crimsonwarpedcraft.uhc.listener.BorderShrinker;
import com.crimsonwarpedcraft.uhc.listener.EndDisabler;
import com.crimsonwarpedcraft.uhc.listener.JoinPreventer;
import com.crimsonwarpedcraft.uhc.listener.RegenPreventer;
import com.crimsonwarpedcraft.uhc.listener.ResurrectPreventer;
import com.crimsonwarpedcraft.uhc.listener.ScoreboardCreator;
import com.crimsonwarpedcraft.uhc.listener.TradeDisabler;
import com.crimsonwarpedcraft.uhc.listener.UhcUserStoreGarbageCollector;
import com.crimsonwarpedcraft.uhc.listener.VillagerGuardian;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ComplexRecipe;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for UHC plugin.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Uhc extends JavaPlugin {
  private static final Logger LOGGER = Bukkit.getLogger();

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    // Try to load the config file
    GameConfig gameConfig;
    try {
      gameConfig = GameConfig.getNewGameConfig(
          new File(getDataFolder(), "config.yml")
      );
    } catch (ConfigurationException e) {
      LOGGER.log(
          Level.SEVERE,
          "Error loading config file, please correct it before continuing!"
      );
      setEnabled(false);
      return;
    }

    GameState gameState = GameState.newGameState(getServer());

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

    PaperCommandRegister
        .getNewPaperCommandRegister(this)
        .register(
            StartCommand.getStartCommand(gameState, gameConfig)
        );

    // Used for registering event listeners with
    BukkitListenerRegister
        .getNewBukkitListenerRegister(this)
        // Register event listeners
        .registerListener(BorderShrinker.getBorderShrinker(gameState, gameConfig))
        .registerListener(EndDisabler.getEndDisabler())
        .registerListener(JoinPreventer.getJoinPreventer(gameState))
        .registerListener(RegenPreventer.getRegenPreventer())
        .registerListener(ResurrectPreventer.getResurrectPreventer())
        .registerListener(ScoreboardCreator.getNewScoreboardCreator())
        .registerListener(TradeDisabler.getTradeDisabler())
        .registerListener(UhcUserStoreGarbageCollector.getUhcUserStoreGarbageCollector())
        .registerListener(VillagerGuardian.getVillagerGuardian());

    LOGGER.log(Level.INFO, "Successfully enabled UHC!");
  }
}
