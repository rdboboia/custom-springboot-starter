package es.rdboboia.custom.starter.utils;

import java.io.InputStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@link LoggerUtils} is a utility class to easily log the entries and exists from methods. It also
 * provides a method to log calls to other classes.
 *
 * @author https://github.com/rdboboia
 */
public class LoggerUtils {
  private static final Logger log = LogManager.getLogger(LoggerUtils.class);

  private static LoggerUtils loggerUtilsInstance;
  private static Level level;

  private static final String CLASS_CALL = "Calling ";
  private static final String ENTERING_METHOD = "Entering into method ";
  private static final String EXITING_METHOD = "Exiting from method ";
  private static final int TRACE_LEVEL = 2;

  private static final String CONFIGURATION_FILE_NAME = "loggerUtils.properties";

  /**
   * Private constructor. No instantiation is required nor allowed. This class uses the Singleton
   * pattern.
   */
  private LoggerUtils() {
    // Nothing to do here.
  }

  /**
   * Provides the {@link LoggerUtils} singleton pattern instance. That is, it creates a new instance
   * if no instances are present or returns the existing instance if an instance already exists.
   *
   * @return the {@link LoggerUtils} instance.
   */
  public static LoggerUtils getLoggerUtilsInstance() {
    log.debug("Checking if an instance already exists.");
    if (loggerUtilsInstance == null) {
      log.debug("No instance exists. Creating an instance.");
      loggerUtilsInstance = new LoggerUtils();

      log.debug("Setting default log level to INFO.");
      LoggerUtils.level = Level.INFO;

      changeLogLevelWithConfigurationFileIfPresent();

      log.debug("The log level is set to: {}", LoggerUtils.level.toString());
    }

    return loggerUtilsInstance;
  }

  /**
   * Sets the log level of the {@link LoggerUtils} methods based on a file configuration. The
   * expected file name is {@value #CONFIGURATION_FILE_NAME}.<br>
   * <br>
   * The expected format is: level=_LEVEL_.<br>
   * Example: level=INFO.<br>
   * <br>
   * All possible values are defined by the {@link Level} class.
   */
  private static void changeLogLevelWithConfigurationFileIfPresent() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    log.debug("Checking if there is a log level configuration file.");
    if (classLoader.getResource(CONFIGURATION_FILE_NAME) != null) {
      log.debug("A configuration file was found.");

      InputStream inputStream =
          classLoader.getResourceAsStream(LoggerUtils.CONFIGURATION_FILE_NAME);
      try {
        log.debug("Attempting to read the file.");
        byte[] bytes = new byte[16];
        int readBytes = inputStream.read(bytes);

        log.debug("Storing data.");
        String data = new String(bytes, 0, readBytes);

        log.debug("Parsing data.");
        String[] split = data.split("=");
        if (split.length == 2 && split[0].equals("level")) {
          log.debug("Parsing level value.");
          Level parsedLevel = Level.getLevel(split[1]);

          if (parsedLevel != null) {
            log.debug("Level successfully parsed. Setting and storing the new log level.");
            LoggerUtils.level = parsedLevel;

            log.debug("The log level is now set to: {}", LoggerUtils.level.toString());
          } else {
            log.debug("Invalid log level value. The log level remains with the default value.");
          }
        }
      } catch (Exception e) {
        log.debug(
            "The configuration file could not be successfully read and parsed. "
                + "The log level remains with the default value.");
      } finally {
        try {
          log.debug("Closing input stream.");
          inputStream.close();
        } catch (Exception e) {
          log.error(
              "Error while trying to close the input stream. Exception that caused the issue: {}",
              e);
        }
      }
    } else {
      log.debug("No configuration file was found. The log level is left with the default value.");
    }
  }

  /**
   * Logs the method entry.
   *
   * @param log the {@link Logger} object that is used for all other logs.
   */
  public void logEnteringMethod(final Logger log) {
    log.atLevel(level)
        .log(ENTERING_METHOD + Thread.currentThread().getStackTrace()[TRACE_LEVEL].getMethodName());
  }

  /**
   * Logs the method exit.
   *
   * @param log the {@link Logger} object that is used for all other logs.
   */
  public void logExitingMethod(final Logger log) {
    log.atLevel(level)
        .log(EXITING_METHOD + Thread.currentThread().getStackTrace()[TRACE_LEVEL].getMethodName());
  }

  /**
   * Logs a call to another class.
   *
   * @param log the {@link Logger} object that is used for all other logs.
   * @param classValue the {@link Class} that will be called.
   */
  public void logClassCall(final Logger log, final Class<?> classValue) {
    log.atLevel(level).log(CLASS_CALL + classValue.getCanonicalName());
  }

  /**
   * Logs a call to another class.
   *
   * @param log the {@link Logger} object that is used for all other logs.
   * @param object an instance of the {@link Class} that will be called.
   */
  public void logClassCall(final Logger log, final Object object) {
    this.logClassCall(log, object.getClass());
  }
}
