package com.crimsonwarpedcraft.uhc;

import com.crimsonwarpedcraft.uhc.listener.ListenerRegister;
import com.crimsonwarpedcraft.uhc.listener.VillagerGuardian;
import io.papermc.lib.PaperLib;
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

    // Used for registering event listeners with
    ListenerRegister
        .getInstance(this)
        // Register event listeners
        .registerListener(new VillagerGuardian());

    saveDefaultConfig();
  }
}
