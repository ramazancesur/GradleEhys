package tr.com.prolms.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesLoader class.
 */
public class PropertiesLoader {

  static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

  /**
   * load method.
   * @param fileName String
   * @return Properties
   */
  public Properties load(String fileName) {
    Properties prop = new Properties();
    InputStream im = null;
    try {
      im = findFile(fileName);
      prop.load(im);
    } catch (IOException ignore) {
      logger.error(ignore.getMessage());
    } finally {
      if (im != null) {
        try {
          im.close();
        } catch (IOException ignore) {
          logger.error(ignore.getMessage());
        }
      }
    }
    return prop;
  }

  private InputStream findFile(String fileName) throws FileNotFoundException {
    InputStream im = findInWorkingDirectory(fileName);
    if (im == null) {
      im = findInClasspath(fileName);
    }
    if (im == null) {
      im = findInSourceDirectory(fileName);
    }
    if (im == null) {
      throw new FileNotFoundException(String.format("File %s not found", fileName));
    }
    return im;
  }

  private InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
    return new FileInputStream("src/main/resources/" + fileName);
  }

  private InputStream findInClasspath(String fileName) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
  }

  private InputStream findInWorkingDirectory(String fileName) {
    try {
      return new FileInputStream(System.getProperty("user.dir") + fileName);
    } catch (FileNotFoundException e) {
      return null;
    }
  }
}
