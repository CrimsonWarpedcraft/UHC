package com.crimsonwarpedcraft.uhc.user;

import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

/**
 * Represents a user of the plugin.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcUser {
  private final CommandSender sender;

  UhcUser(CommandSender sender) {
    this.sender = sender;
  }

  /** Returns true if the user had admin permission, false otherwise. */
  public boolean hasAdminPermission() {
    return sender.hasPermission("uhc.admin");
  }

  /** Sends the user a message. */
  public void sendMessage(Component message) {
    sender.sendMessage(message);
  }
}
