package com.crimsonwarpedcraft.uhc.user;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Represents a UhcUser based on an online Player.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class UhcPlayer extends UhcUser {
  private final Player player;

  UhcPlayer(Player player) {
    super(player);
    this.player = player;
  }

  /** Resets the player's health to the default amount. */
  // For some reason, there's a false positive here even though we throw an NPE
  @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
  public UhcPlayer resetHealth() {
    // Get the health attributes for the player
    AttributeInstance attribute = Objects.requireNonNull(
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
    );

    // Set the max health to the default value, in case the player has absorption
    attribute.setBaseValue(attribute.getDefaultValue());

    // Set the player's health to the default value
    player.setHealth(attribute.getDefaultValue());

    return this;
  }

  /** Resets the player's hunger to the default amount. */
  public UhcPlayer resetHunger() {
    player.setFoodLevel(20);

    return this;
  }

  /** Resets the player's saturation to the default amount. */
  public UhcPlayer resetSaturation() {
    // 5 should be the default at respawn
    player.setSaturation(5);

    return this;
  }

  /** Resets the player's exhaustion to the default amount. */
  public UhcPlayer resetExhaustion() {
    player.setExhaustion(0);

    return this;
  }

  /** Resets the player's experience to the default amount. */
  public UhcPlayer resetExp() {
    player.setExp(0);
    player.setLevel(0);

    return this;
  }

  /** Resets the player's inventory. */
  public UhcPlayer resetInventory() {
    player.getInventory().clear();

    return this;
  }

  /** Sets the player's game mode. */
  public UhcPlayer setGameMode(GameMode gameMode) {
    player.setGameMode(gameMode.getGameMode());

    return this;
  }

  /** Sends the player the player list header and footer. */
  public UhcPlayer sendPlayerListHeaderAndFooter(Component header, Component footer) {
    player.sendPlayerListHeaderAndFooter(header, footer);

    return this;
  }

  /** Returns this player's scoreboard. */
  public Scoreboard getScoreboard() {
    return player.getScoreboard();
  }
}
