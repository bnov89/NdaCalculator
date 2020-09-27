package org.bnov.nda.calculator;

import org.bnov.nda.calculator.file.reader.FileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Calculator {

  private final FileReader fileReader;

  public Calculator(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public void process(final String inputPath, final String outputPath) {
    try {
      List<Path> paths = fileReader.readFilesFromPath(inputPath);
      //            paths.stream()
      //                    .map(Path::toFile)
      //                    .map(file -> new FileInputStream(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
