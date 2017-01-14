package kbt;

import java.io.*;
import java.util.Properties;

public class Config
{
  private static Properties props;
  private static String workDir;

  public static String getWorkDir()
  {
    return workDir;
  }

  public static void setWorkDir(String workDir)
  {
    Config.workDir = workDir;
  }

  public static void setProperties(String propertiesFile)
  {
      InputStream inputStream;

      if (propertiesFile == null) {
          inputStream = Config.class.getClassLoader().getResourceAsStream("application.properties");
      }
      else {
          try {
              inputStream = new FileInputStream(new File(propertiesFile));
          } catch (FileNotFoundException e) {
              System.err.println("Invalid propertiesFile: " + propertiesFile);
              return;
          }
      }

      props = new Properties();
      try {
          props.load(inputStream);
      }
      catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static String get(String key)
  {
    if (props == null) {
    }

    return props.getProperty(key);
  }
}
