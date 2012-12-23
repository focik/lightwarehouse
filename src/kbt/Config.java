/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kbt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kabot
 */
public class Config
{
  private static Properties props = null;
  private static String workDir;

  public static String getWorkDir()
  {
    return workDir;
  }

  public static void setWorkDir(String workDir)
  {
    Config.workDir = workDir;
  }

  public static String get(String key)
  {
    String value;

    if (props == null) {
      props = new Properties();

      loadFile(workDir + "/" +"common.properties");

      // plik uzytkownika nie musi istniec
      if ((new File(workDir + "/" +"user.properties")).exists()) {
        loadFile(workDir + "/" +"user.properties");
      }
    }

    value = props.getProperty(key);

    return putVars(value);
  }

  private static void loadFile(String filename)
  {
    try {
      props.load(new FileInputStream(filename));
    }
    catch (IOException e) {
      System.err.println(e);
    }
  }

  private static String putVars(String val)
  {
    val = val.replace("{workDir}", workDir);

    return val;
  }
}