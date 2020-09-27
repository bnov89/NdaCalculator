package org.bnov.nda.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.assertj.core.api.Assertions.assertThat;

class FileCollectorTest {
  private final FileCollector sut = new FileCollector();

  @Test
  void givenPathNotExists_shouldThrowException() {
    NoSuchFileException exception =
        Assertions.assertThrows(NoSuchFileException.class, () -> sut.getFiles("not/existing/path"));
    assertThat(exception).hasMessage("not\\existing\\path");
  }

  @Test
  void givenPathIsNull_shouldThrowException() {
    IllegalArgumentException exception =
        Assertions.assertThrows(IllegalArgumentException.class, () -> sut.getFiles(null));
    assertThat(exception).hasMessage("Path cannot be null");
  }
}
