package com.crimsonwarpedcraft.uhc.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.crimsonwarpedcraft.uhc.game.GameConfig;
import com.crimsonwarpedcraft.uhc.game.GameState;
import com.crimsonwarpedcraft.uhc.mock.MockItemStack;
import com.crimsonwarpedcraft.uhc.mock.MockPlayer;
import com.crimsonwarpedcraft.uhc.mock.MockServer;
import com.crimsonwarpedcraft.uhc.mock.MockWorld;
import java.nio.file.Paths;
import net.kyori.adventure.text.Component;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

/**
 * Tests for StartCommand.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
class StartCommandTest {

  @Test
  void onStart() throws InterruptedException {
    MockPlayer player1 = new MockPlayer();
    player1.setHealth(10);
    AttributeInstance attribute = player1.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    assertNotNull(attribute);
    attribute.setBaseValue(2);
    player1.setExp(.8f);
    player1.setExhaustion(5);
    player1.setSaturation(0);
    player1.setFoodLevel(5);
    player1.setGameMode(GameMode.ADVENTURE);
    player1.getInventory().addItem(new MockItemStack(Material.ACACIA_BOAT));
    MockPlayer player2 = new MockPlayer();

    MockServer server = new MockServer();
    server.addPlayer(player1);
    server.addPlayer(player2);

    MockWorld world = new MockWorld();
    world.setName("world1");
    world.getWorldBorder().setSize(5);
    world.setDifficulty(Difficulty.PEACEFUL);
    world.setFullTime(1000);
    server.loadWorld(world);

    FileConfiguration configFile = new YamlConfiguration();
    assertDoesNotThrow(
        () -> configFile.load(
            Paths.get("src", "test", "resources", "config.yml").toFile()
        )
    );

    GameState game = GameState.newGameState(server);
    GameConfig config = GameConfig.getNewGameConfig(configFile);
    StartCommand start = StartCommand.getStartCommand(
        game,
        config
    );

    // Run the start command
    start.onStart(player2);

    // Check that the game is running
    assertTrue(game.isRunning());

    // Make sure that players are added to game players
    assertTrue(game.getGamePlayers().containsKey(player1.getUniqueId()));
    assertTrue(game.getGamePlayers().containsKey(player2.getUniqueId()));

    // Make sure the player was sent a message
    Component message = player2.getLastMessage();
    assertNotNull(message);

    // Make sure that all players were sent a message
    assertNotNull(player1.getLastMessage());

    // Make sure players' health were reset
    assertEquals(
        attribute.getDefaultValue(),
        player1.getHealth()
    );

    // Make sure players' max health were reset
    assertEquals(
        attribute.getDefaultValue(),
        attribute.getBaseValue()
    );

    // Make sure players' food level were reset
    assertEquals(20, player1.getFoodLevel());

    // Make sure players' experience were reset
    assertEquals(0, player1.getExp());
    assertEquals(0, player1.getLevel());

    // Make sure players' saturation were reset
    assertEquals(5, player1.getSaturation());

    // Make sure player's game modes were set
    assertEquals(GameMode.SURVIVAL, player1.getGameMode());

    // Make sure players' inventories were reset
    assertEquals(0, player1.getInventory().getSize());

    // Make sure that the world border was set
    assertEquals(config.getBorderStartSize(), world.getWorldBorder().getSize());

    // Make sure that the difficulty was set to hardcore
    assertEquals(Difficulty.HARD, world.getDifficulty());

    // Make sure that the time was set to day
    assertEquals(0, world.getFullTime());

    // Make sure border shrink after the configured seconds
    Thread.sleep((config.getBorderShrinkSeconds() + 1) * 1000);

    assertEquals(config.getBorderShrinkSize(), world.getWorldBorder().getSize());

    // Run the start command again
    start.onStart(player2);

    // Check that the player was sent a different message
    assertNotEquals(message, player2.getLastMessage());
  }
}