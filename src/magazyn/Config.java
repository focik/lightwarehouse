/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package magazyn;

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

  static public String get(String key)
  {
//    FileOutputStream propsfile;
    FileInputStream fcommon;
    String value;

    if (props == null) {
      props = new Properties();

      //polozenie pliku .jar
      String jarPath = Config.class.getProtectionDomain().getCodeSource().getLocation().getPath();
      String appPath;

      if (jarPath.endsWith(".jar")) {
        appPath = new File(jarPath).getParent();
      }
      else {
        appPath = System.getProperty("user.dir") + "/working";
      }

//System.out.println(appPath);

      try {
//      propsfile = new FileOutputStream("magazyn.conf");
//
//      def.setProperty("katalog=", "=pełna\tście\nżka");
//      def.store(propsfile, "komentarz przy\nzapisie");
//
//      propsfile.close();

        fcommon = new FileInputStream(appPath + "/common.properties");
        props.load(fcommon);
      }
      catch (FileNotFoundException e) {
        System.out.println(e);
      }
      catch (IOException e) {
        System.out.println(e);
      }
    }

    value = props.getProperty(key);

    return putVars(value);
  }
  
  private static String putVars(String val)
  {
    val = val.replace("{userdir}", System.getProperty("user.home"));
    val = val.replace("{appuserdir}", props.getProperty("appuserdir"));

    return val;
  }
}
