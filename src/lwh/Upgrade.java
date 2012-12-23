package lwh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import kbt.Config;
import kbt.Filesystem;
import kbt.Zip;

public class Upgrade
{
  private URL fileUrl;
  private URL versionUrl;

  private final String loaderWin = "start.bat";
  private final String loaderLin = "start.sh";

  public boolean setUrl(String url)
  {
    try {
      versionUrl = new URL(url);
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
      fileUrl = new URL(in.readLine());
    }
    catch (IOException ex) {
      return 0;
    }

    return ver;
  }

  /**
   * wymagane wczesniejsze wykonanie getVersion() aby okreslic link do zip
   * @param workdir
   * @return true/false
   */
  public boolean download(String workdir)
  {
    File tmpfile, target;
    InputStream in;
    Zip zip = new Zip();
    String tmpdir = Paths.get(workdir, "upgrade.tmp").toString();

    try {
      in = fileUrl.openStream();
      tmpfile = File.createTempFile("lwh-", "");

      Files.copy(in, tmpfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException ex) {
      System.err.println(ex);
      return false;
    }

    target = new File(tmpdir);

    if (target.exists()) {
      Filesystem.deleteDir(target);
    }

    if (zip.unzip(tmpfile.toString(), tmpdir)) {
      tmpfile.delete();

      copyStarter(tmpdir);
    }
    else {
      return false;
    }

    return true;
  }

  public void restart()
  {
    String loader;

    if ("Linux".equals(System.getProperty("os.name"))) {
      loader = "start.sh";
    }
    else {
      loader = "start.bat";
    }

    loader = Paths.get(Config.getWorkDir(), loader).toString();

    try {
      Runtime.getRuntime().exec(loader + " delay");
    }
    catch (IOException ex) {
      System.err.println(ex);
    }

    System.exit(0);
  }

  private void copyStarter(String targetdir)
  {
    Path sLin = Paths.get(Config.getWorkDir(), loaderLin);
    Path sWin = Paths.get(Config.getWorkDir(), loaderWin);

    try {
      Files.move(Paths.get(targetdir, loaderLin), sLin, StandardCopyOption.REPLACE_EXISTING);
      Files.move(Paths.get(targetdir, loaderWin), sWin, StandardCopyOption.REPLACE_EXISTING);

      sLin.toFile().setExecutable(true);
      sWin.toFile().setExecutable(true);
    }
    catch (IOException ex) {
      System.err.println(ex);
    }
  }

  public void updateDb()
  {
    int dbver = Db.getDbVer();
    int appver = Integer.parseInt(Config.get("version"));

    if (appver > dbver) {
      System.err.println("Wymagana aktualizacja bazy danych");
    }
  }
}
