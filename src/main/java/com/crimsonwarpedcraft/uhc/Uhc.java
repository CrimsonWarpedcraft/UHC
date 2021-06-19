package com.crimsonwarpedcraft.uhc;

import com.crimsonwarpedcraft.uhc.command.CommandRegister;
import com.crimsonwarpedcraft.uhc.command.StartCommand;
import com.crimsonwarpedcraft.uhc.listener.EndDisabler;
import com.crimsonwarpedcraft.uhc.listener.ListenerRegister;
import com.crimsonwarpedcraft.uhc.listener.RegenPreventer;
import com.crimsonwarpedcraft.uhc.listener.ResurrectPreventer;
import com.crimsonwarpedcraft.uhc.listener.TradeDisabler;
import com.crimsonwarpedcraft.uhc.listener.UhcUserStoreGarbageCollector;
import com.crimsonwarpedcraft.uhc.listener.VillagerGuardian;
import io.papermc.lib.PaperLib;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for UHC plugin.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class Uhc extends JavaPlugin {

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    GameConfig gameConfig = GameConfig.getNewGameConfig(getConfig());

    RecipeManager
        .getNewRecipeManager(getServer())
        // Remove any recipe that results in suspicious stew
        .filter(
            recipe -> !recipe.getResult().isSimilar(
                new ItemStack(Material.SUSPICIOUS_STEW)
            )
        )
        .apply();

    // Try to overwrite config file
    // This lets us update the config file with default
    // values if we ever add more config options
    try {
      gameConfig.save(
          new File(getDataFolder(), "config.yml")
      );
    } catch (IOException e) {
      getLogger()
          .log(
              Level.WARNING,
              "Error writing config file, your config may be out of date!"
          );
    }

    CommandRegister
        .newCommandRegister(this)
        .register(
            StartCommand.getStartCommand(
                GameState.newGameState(getServer()),
                gameConfig
            )
        );

    // Used for registering event listeners with
    ListenerRegister
        .getListenerRegister(this)
        // Register event listeners
        .registerListener(VillagerGuardian.getVillagerGuardian())
        .registerListener(TradeDisabler.getTradeDisabler())
        .registerListener(RegenPreventer.getRegenPreventer())
        .registerListener(ResurrectPreventer.getResurrectPreventer())
        .registerListener(EndDisabler.getEndDisabler())
        .registerListener(UhcUserStoreGarbageCollector.getUhcUserStoreGarbageCollector());
  }
}
