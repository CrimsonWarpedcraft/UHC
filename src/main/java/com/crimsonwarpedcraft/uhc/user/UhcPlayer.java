package com.crimsonwarpedcraft.uhc.user;

import org.bukkit.entity.Player;

/**
 * Represents a UhcUser based on an online Player.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcPlayer extends UhcUser {
  UhcPlayer(Player player) {
    super(player);
  }
}
