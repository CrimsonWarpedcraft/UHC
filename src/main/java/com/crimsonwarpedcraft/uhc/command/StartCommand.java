package com.crimsonwarpedcraft.uhc.command;

import static net.kyori.adventure.text.Component.text;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.crimsonwarpedcraft.uhc.GameState;
import com.crimsonwarpedcraft.uhc.WorldConfig;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import java.util.Collection;
import java.util.Objects;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

    if (!game.isRunning()) {
      game.setRunning(true);

      WorldConfig
          .getWorldConfig(game.getWorld("world"))
          // TODO set variables for border size and time to shrink
          // TODO prevent border from shrinking for an X amount of time
          .setBorderSize(1000)
          .setBorderSize(500, 300);

      Collection<? extends Player> onlinePlayers = game.getOnlinePlayers();
      for (Player player : onlinePlayers) {
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
            //TODO Reset Player health to 20
            .sendMessage(
                text("Game Has BEGUN!!", NamedTextColor.GREEN)
            );
      }

      // TODO save online players into whitelist, save old whitelist and replace it back on game end

    } else {
      UhcUserStore
          .getInstance()
          .getUhcUser(sender)
          .sendMessage(
              text("Error: Game Already Running", NamedTextColor.RED)
          );
    }
  }
}
