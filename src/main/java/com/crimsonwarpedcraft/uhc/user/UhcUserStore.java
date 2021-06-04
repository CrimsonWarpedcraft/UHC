package com.crimsonwarpedcraft.uhc.user;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Used to get an instance of a UhcUser from a Player or CommandSender.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcUserStore {
  private static UhcUserStore factory;
  private final Map<Player, UhcPlayer> players;

  /** Returns the UhcUserStore instance. */
  public static UhcUserStore getInstance() {
    if (factory == null) {
      factory = new UhcUserStore();
    }

    return factory;
  }

  private UhcUserStore() {
    players = new HashMap<>();
  }

  /**
   * Gets the UhcPlayer for the Player.
   *
   * @param player the player to get the UhcPlayer from
   * @return the existing UhcPlayer for this Player if found, otherwise a new instance is returned
   */
  public UhcPlayer getUhcUser(Player player) {
    if (players.containsKey(player)) {
      return players.get(player);
    }

    UhcPlayer uhcPlayer = new UhcPlayer(player);

    players.put(player, uhcPlayer);

    return uhcPlayer;
  }

  /**
   * Gets the UchUser for the CommandSender.
   *
   * @param sender the command sender to get the UhcUser from
   * @return a new UhcUser
   */
  public UhcUser getUhcUser(CommandSender sender) {
    return new UhcUser(sender);
  }

  /** Removes an exising UhcPlayer for the player. */
  public UhcUserStore removePlayer(Player player) {
    players.remove(player);

    return this;
  }
}
