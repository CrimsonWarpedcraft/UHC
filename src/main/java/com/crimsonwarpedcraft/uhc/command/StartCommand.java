package com.crimsonwarpedcraft.uhc.command;

import static net.kyori.adventure.text.Component.text;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.crimsonwarpedcraft.uhc.Uhc;
import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.game.WorldConfig;
import com.crimsonwarpedcraft.uhc.user.GameMode;
import com.crimsonwarpedcraft.uhc.user.UhcUserStore;
import com.crimsonwarpedcraft.uhc.util.UhcLogger;
import java.util.Collection;
import java.util.Objects;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command to start the game.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
@CommandAlias("uhc")
public class StartCommand extends BaseCommand {
  private static final UhcLogger LOGGER = Uhc.getUhcLogger();
  private final GameState game;
  private final GameConfig config;

  /**
   * Returns an instance of a StartCommand.
   *
   * @param game the state of the game
   */
  public static StartCommand getStartCommand(GameState game, GameConfig config) {
    return new StartCommand(Objects.requireNonNull(game), Objects.requireNonNull(config));
  }

  private StartCommand(GameState game, GameConfig config) {
    this.game = game;
    this.config = config;
  }

  /** Command to start the game. */
  @Subcommand("start")
  @Description("Starts the game")
  @CommandPermission("uhc.admin")
  public void onStart(CommandSender sender) {

    // If game is not already running, starts the game
    if (!game.isRunning()) {
      game.setRunning(true);

      // Sets Config data
      WorldConfig
          .getWorldConfig(game.getWorld(config.getMainWorldName()))
          .setDifficulty(Difficulty.HARD) //Sets difficulty to HARD
          .setTime(0)
          .setBorderSize(config.getBorderStartSize()) //Creates World Border
          //Shrink world border
          .setBorderSize(
              config.getBorderShrinkSize(),
              config.getBorderShrinkSeconds()
          );

      //Lists all players, resets their stats & sends them a BEGIN message
      game.storeGamePlayers();
      Collection<? extends Player> gamePlayers = game.getOnlinePlayers();
      for (Player player : gamePlayers) {
        UhcUserStore
            .getInstance()
            .getUhcUser(player)
            .resetHealth()
            .resetHunger()
            .resetSaturation()
            .resetExhaustion()
            .resetExp()
            .resetInventory()
            .setGameMode(GameMode.SURVIVAL)
            .sendMessage(
                text("Game Has BEGUN!!", NamedTextColor.GREEN)
            );
      }

      LOGGER.log(UhcLogger.Level.INFO, "Game has started");
    // If game is already running, sends an error message
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
