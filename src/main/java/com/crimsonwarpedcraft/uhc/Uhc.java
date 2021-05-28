package com.crimsonwarpedcraft.uhc;

import com.crimsonwarpedcraft.uhc.listener.VillagerGuardian;
import io.papermc.lib.PaperLib;
import org.bukkit.plugin.PluginManager;
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

    // Register event listeners
    registerListeners();

    saveDefaultConfig();
  }

  /** Helper function for registering event listeners. */
  private void registerListeners() {
    // Plugin manager used for registering event listeners with
    PluginManager manager =  this.getServer().getPluginManager();

    // Register VillagerGuardian event listener ("this" means that *this* plugin provided it)
    manager.registerEvents(new VillagerGuardian(), this);
  }
}
