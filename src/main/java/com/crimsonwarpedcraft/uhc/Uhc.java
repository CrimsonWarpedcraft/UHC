package com.crimsonwarpedcraft.uhc;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
  @SuppressFBWarnings("DLS_DEAD_LOCAL_STORE") // TODO remove once we add something here
  private void registerListeners() {
    // Plugin manager used for registering event listeners with
    PluginManager manager =  this.getServer().getPluginManager();

  }
}
