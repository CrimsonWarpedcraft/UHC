package com.crimsonwarpedcraft.uhc.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.crimsonwarpedcraft.uhc.GameState;
import java.util.Objects;
import org.bukkit.command.CommandSender;

/**
 * Command to start the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@CommandAlias("uhc")
public class StartCommand extends BaseCommand {
  private final GameState game;

  /**
   * Returns an instance of a StartCommand.
   *
   * @param game the state of the game
   */
  public static StartCommand getStartCommand(GameState game) {
    return new StartCommand(Objects.requireNonNull(game));
  }

  private StartCommand(GameState game) {
    this.game = game;
  }

  /** Command to start the game. */
  @Subcommand("start")
  @Description("Starts the game")
  @CommandPermission("uhc.admin")
  public void onStart(CommandSender sender) {
    // TODO If game is running, start game, start world border shrinking and send message to all
    //  players, otherwise, send error message to command sender. Consider setting all players'
    //  health to 20
  }
}
