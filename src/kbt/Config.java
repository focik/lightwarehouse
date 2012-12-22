/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kbt;

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

      loadFile("common.properties");
      loadFile("user.properties");
    }

    value = props.getProperty(key);

    return putVars(value);
  }

  private static void loadFile(String filename)
  {
    FileInputStream fcommon;
    String confPath;

    confPath = workDir + "/" + filename;

    try {
      fcommon = new FileInputStream(confPath);
      props.load(fcommon);
    }
    catch (FileNotFoundException e) {
      System.out.println(e);
    }
    catch (IOException e) {
      System.out.println(e);
    }
  }

  private static String putVars(String val)
  {
    val = val.replace("{workDir}", workDir);

    return val;
  }
}