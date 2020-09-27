package org.bnov.nda.file;

import java.io.File;

public class FileCreator {
  private FileCreator() {}

  public static File createFile(
      final String path, final String fileName, final String suffix, final String extension) {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(path);
    stringBuilder.append("\\");
    stringBuilder.append(fileName);
    stringBuilder.append(suffix);
    stringBuilder.append(".");
    stringBuilder.append(extension);
    return new File(stringBuilder.toString());
  }
}
