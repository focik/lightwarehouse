package kbt;

import java.io.File;

public class Filesystem {

  /**
   * http://www.albeesonline.com/blog/2008/11/02/recursively-delete-a-directory/
   *
   * @param dir
   */
  public static void deleteDir(File dir)
  {
    // If it is a directory get the child
    if (dir.isDirectory()) {
      // List all the contents of the directory
      File fileList[] = dir.listFiles();

      // Loop through the list of files/directories
      for (int index = 0; index < fileList.length; index++) {
        // Get the current file object.
        File file = fileList[index];

        // Call deleteDir function once again for deleting all the directory contents or
        // sub directories if any present.
        deleteDir(file);
      }
    }

    // Delete the current directory or file.
    dir.delete();
  }
}
