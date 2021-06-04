package com.crimsonwarpedcraft.uhc.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.Plugin;

/**
 * Used to register in-game commands.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class CommandRegister {
  private final PaperCommandManager manager;

  /**
   * Returns a new instance of a CommandRegister.
   *
   * @param plugin the plugin that is registering the command
   * @return a new CommandRegister instance
   */
  public static CommandRegister newCommandRegister(Plugin plugin) {
    return new CommandRegister(plugin);
  }

  private CommandRegister(Plugin plugin) {
    manager = new PaperCommandManager(plugin);
  }

  /** Registers an object that has command annotations. */
  public CommandRegister register(BaseCommand command) {
    manager.registerCommand(command);

    return this;
  }
}
