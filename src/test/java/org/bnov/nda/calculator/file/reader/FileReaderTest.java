package org.bnov.nda.calculator.file.reader;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FileReaderTest {

  private FileReader sut = new FileReader();

  @Test
  void givenPathIsNull_shouldThrowException() {
    IllegalArgumentException exception =
        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.readFilesFromPath(null));
    assertThat(exception).hasMessage("Path cannot be null");
  }

  @Test
  void givenPathNotExists_shouldThrowException() {
    NoSuchFileException exception =
        Assertions.assertThrows(
            NoSuchFileException.class, () -> sut.readFilesFromPath("not/existing/path"));
    assertThat(exception).hasMessage("not\\existing\\path");
  }

  @Test
  void givenValidPath_shouldReadFilesInGivenDirectory() throws IOException, URISyntaxException {
    Path path = Paths.get(getClass().getClassLoader().getResource("expressions").toURI());
    List<Path> expressions =
        sut.readFilesFromPath(FilenameUtils.separatorsToSystem(path.toString()));
    List<String> fileNames =
        expressions.stream().map(p -> p.getFileName().toString()).collect(Collectors.toList());
    //        assertThat(expressions).hasSize(2);
    assertThat(fileNames).contains("complex_operations.xml", "simple_operations.xml");
  }
}
