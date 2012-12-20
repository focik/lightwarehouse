package lwh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Upgrade
{
  private URL fileUrl;
  private URL versionUrl;

  public boolean setUrl(String vUrl, String fUrl)
  {
    try {
      fileUrl = new URL(fUrl);
      versionUrl = new URL(vUrl);
    }
    catch (MalformedURLException ex) {
      return false;
    }

    return true;
  }

  public int getVersion()
  {
    int ver;

    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(versionUrl.openStream()));

      ver = Integer.parseInt(in.readLine());
    }
    catch (IOException ex) {
      return 0;
    }

    return ver;
  }

  public boolean download()
  {
    File tmpfile;
    InputStream in;

    try {
      in = fileUrl.openStream();
      tmpfile = File.createTempFile("lwh-", "");

      Files.copy(in, tmpfile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }
    catch (IOException ex) {
      System.out.println(ex);
      return false;
    }

    return true;
  }

  public void start()
  {
    try {

      //run loader
      Process proc = Runtime.getRuntime().exec("java -jar dist/magazyn.jar");

      System.out.println("waiting...");
      try {
        proc.waitFor();
        System.out.println("jest");
      }
      catch (InterruptedException ex) {
        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    catch (IOException ex) {
      Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
}
