package org.bnov.nda;

import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.marshal.Marshaller;
import org.bnov.nda.marshal.MarshallerFactory;
import org.bnov.nda.model.result.Expressions;
import org.bnov.nda.unmarshal.Format;
import org.bnov.nda.unmarshal.Unmarshaller;
import org.bnov.nda.unmarshal.UnmarshallerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bnov.nda.marshal.Format.XML;

class NdaCalculatorTest {

  @Test
  void inputPathNotExists_shouldThrowException() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            new NdaCalculator(buildMarshaller(), buildUnmarshaller(), new Calculator())
                .process("e2e/expressions", "somePath"));
  }

  @Test
  void outputPathNotExists_shouldThrowException() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            new NdaCalculator(buildMarshaller(), buildUnmarshaller(), new Calculator())
                .process("somePath", "e2e/expressions"));
  }

  @Test
  void
      givenTwoFilesContainsSimpleAndComplexExpressionsOfAllTypes_shouldReturnFilesWithCorrectResults()
          throws URISyntaxException, IOException {
    List<File> files = new LinkedList<>();
    try {
      // GIVEN
      File file = new File(getClass().getClassLoader().getResource("e2e/expressions").toURI());
      NdaCalculator ndaCalculator =
          new NdaCalculator(buildMarshaller(), buildUnmarshaller(), new Calculator());
      // WHEN
      ndaCalculator.process(file.getAbsolutePath(), file.getAbsolutePath());
      // THEN
      files = getResultFiles(file.getAbsolutePath());
      assertThat(files).hasSize(2);
      File simpleOutputFile = files.get(1);
      File complexOutputFile = files.get(0);
      File simpleTypesResultFile =
          new File(
              getClass()
                  .getClassLoader()
                  .getResource("e2e/expected_result/simple_result.xml")
                  .toURI());
      File complexTypesResultFile =
          new File(
              getClass()
                  .getClassLoader()
                  .getResource("e2e/expected_result/complex_result.xml")
                  .toURI());
      assertThat(simpleOutputFile).hasSameTextualContentAs(simpleTypesResultFile);
      assertThat(complexOutputFile).hasSameTextualContentAs(complexTypesResultFile);
    } finally {
      for (File fileToDelete : files) {
        fileToDelete.delete();
      }
    }
  }

  private Marshaller<Expressions> buildMarshaller() {
    return new MarshallerFactory().create(XML, Expressions.class);
  }

  private Unmarshaller<org.bnov.nda.model.Expressions> buildUnmarshaller() {
    return new UnmarshallerFactory().create(Format.XML, org.bnov.nda.model.Expressions.class);
  }

  public List<File> getResultFiles(final String path) throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      return paths
          .map(Path::toFile)
          .filter(file -> file.getName().contains(Configuration.RESULT_FILE_SUFFIX))
          .collect(Collectors.toList());
    }
  }
}
