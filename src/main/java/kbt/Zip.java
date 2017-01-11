package kbt;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Zip {
  private static void copyInputStream(InputStream in, OutputStream out) throws IOException
  {
    byte[] buffer = new byte[4096];
    int len;

    while ((len = in.read(buffer)) >= 0) {
      out.write(buffer, 0, len);
    }

    in.close();
    out.close();
  }

  public boolean unzip(String infile, String outdir)
  {
    Enumeration entries;
    ZipFile zipFile;
    File file;

    try {
      zipFile = new ZipFile(infile);

      entries = (Enumeration)zipFile.entries();

      while (entries.hasMoreElements()) {
        ZipEntry entry = (ZipEntry)entries.nextElement();
        Path entrypath = Paths.get(outdir, entry.getName());
        Path dir;
        boolean cpfile = false;

        if (entry.isDirectory()) {
          dir = entrypath;
        }
        else {
          dir = entrypath.getParent();
          cpfile = true;
        }

        file = new File(dir.toString());
        if (!file.exists()) {
          file.mkdirs();
        }

        if (cpfile) {
          copyInputStream(
            zipFile.getInputStream(entry),
            new BufferedOutputStream(new FileOutputStream(entrypath.toString())));
        }
      }

      zipFile.close();
    }
    catch (IOException ioe) {
      System.err.println(ioe);
      return false;
    }

    return true;
  }
}
