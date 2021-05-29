package com.crimsonwarpedcraft.uhc.listener;

import java.util.Objects;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Registers event listeners with the server.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class ListenerRegister {
  private final Plugin plugin;
  private final PluginManager manager;

  private ListenerRegister(Plugin plugin) {
    this.plugin = plugin;
    this.manager = plugin.getServer().getPluginManager();
  }

  protected ListenerRegister(Plugin plugin, PluginManager manager) {
    this.plugin = plugin;
    this.manager = manager;
  }

  /**
   * Registers an event listenter with the server.
   *
   * @param listener the event listener to register
   * @return this instance that can be used for method chaining
   */
  public ListenerRegister registerListener(Listener listener) {
    manager.registerEvents(listener, plugin);

    return this;
  }

  /**
   * Constructs and returns a ListenerRegister object.
   *
   * @param plugin the plugin that is registering the event listeners
   * @return a ListenerRegister object
   */
  public static ListenerRegister getInstance(Plugin plugin) {
    return new ListenerRegister(Objects.requireNonNull(plugin));
  }
}
