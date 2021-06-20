package com.crimsonwarpedcraft.uhc.mock;

import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock object for Logger.
 *
 * @author Copyright (c) Levi Muniz. All Rights Reserved.
 */
public class MockLogger extends Logger {
  private String lastLog;

  /**
   * Construct a logger for a named subsystem.
   *
   * <p>The logger will be initially configured with a null Level
   * and with useParentHandlers set to true.
   *
   * @param name               A name for the logger.  This should
   *                           be a dot-separated name and should normally
   *                           be based on the package name or class name
   *                           of the subsystem, such as java.net
   *                           or javax.swing.  It may be null for anonymous Loggers.
   *                           of the messages require localization.
   * @throws MissingResourceException if the resourceBundleName is non-null and
   *                                  no corresponding resource can be found.
   */
  public MockLogger(String name) {
    super(name, null);
  }

  @Override
  public void log(Level level, String msg) {
    super.log(level, msg);
    lastLog = msg;
  }

  public String getLastLog() {
    return lastLog;
  }
}
