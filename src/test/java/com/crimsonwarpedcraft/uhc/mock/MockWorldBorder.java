package com.crimsonwarpedcraft.uhc.mock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.jetbrains.annotations.NotNull;

/**
 * Mock object for WorldBorders.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockWorldBorder implements WorldBorder {
  private double size = 0;
  private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  @Override
  public void reset() {

  }

  @Override
  public double getSize() {
    return size;
  }

  @Override
  public void setSize(double newSize) {
    size = newSize;
  }

  @Override
  public void setSize(double newSize, long seconds) {
    Runnable task = () -> size = seconds;

    executor.schedule(task, seconds, TimeUnit.SECONDS);
  }

  @Override
  public @NotNull Location getCenter() {
    return null;
  }

  @Override
  public void setCenter(double x, double z) {

  }

  @Override
  public void setCenter(@NotNull Location location) {

  }

  @Override
  public double getDamageBuffer() {
    return 0;
  }

  @Override
  public void setDamageBuffer(double blocks) {

  }

  @Override
  public double getDamageAmount() {
    return 0;
  }

  @Override
  public void setDamageAmount(double damage) {

  }

  @Override
  public int getWarningTime() {
    return 0;
  }

  @Override
  public void setWarningTime(int seconds) {

  }

  @Override
  public int getWarningDistance() {
    return 0;
  }

  @Override
  public void setWarningDistance(int distance) {

  }

  @Override
  public boolean isInside(@NotNull Location location) {
    return false;
  }

  public void shutdownTasks() {
    executor.shutdown();
  }
}
