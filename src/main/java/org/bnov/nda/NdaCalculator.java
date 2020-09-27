package org.bnov.nda;

import org.apache.commons.io.FilenameUtils;
import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.file.FileCollector;
import org.bnov.nda.file.FileCreator;
import org.bnov.nda.marshal.Marshaller;
import org.bnov.nda.model.AbstractExpression;
import org.bnov.nda.model.Expressions;
import org.bnov.nda.model.Tuple;
import org.bnov.nda.model.result.Result;
import org.bnov.nda.unmarshal.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NdaCalculator {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final Marshaller<org.bnov.nda.model.result.Expressions> marshaller;
  private final Unmarshaller<Expressions> unmarshaller;
  private final Calculator calculator;

  public NdaCalculator(
      Marshaller<org.bnov.nda.model.result.Expressions> marshaller,
      Unmarshaller<Expressions> unmarshaller,
      Calculator calculator) {
    this.marshaller = marshaller;
    this.unmarshaller = unmarshaller;
    this.calculator = calculator;
  }

  public void process(final String inputPath, final String outputPath) {
    validatePaths(inputPath, outputPath);
    final FileCollector fileCollector = new FileCollector();
    try {
      List<File> inputFiles = fileCollector.getFiles(inputPath);
      inputFiles.stream()
          .map(file -> this.unmarshall(file, unmarshaller))
          .map(
              expressionsFileTuple ->
                  this.calculate(
                      expressionsFileTuple.getFirstValue(), expressionsFileTuple.getSecondValue()))
          .forEach(
              resultsFileTuple ->
                  marshaller.marshall(
                      new org.bnov.nda.model.result.Expressions(resultsFileTuple.getFirstValue()),
                      FileCreator.createFile(
                          outputPath,
                          FilenameUtils.removeExtension(
                              resultsFileTuple.getSecondValue().getName()),
                          Configuration.RESULT_FILE_SUFFIX,
                          Configuration.RESULT_FILE_EXTENSION)));
    } catch (IOException e) {
      LOGGER.error("Error occured during processing... {}", e.getMessage());
    }
  }

  private void validatePaths(String inputPath, String outputPath) {
    if (new File(inputPath).exists() && new File(outputPath).exists()) {
      return;
    }
    throw new IllegalArgumentException("Input and output path must exist");
  }

  private Tuple<Expressions, File> unmarshall(File file, Unmarshaller<Expressions> unmarshaller) {
    return new Tuple<>(unmarshaller.unmarshall(file), file);
  }

  private Tuple<List<Result>, File> calculate(Expressions expressions, File file) {
    List<Result> results = new LinkedList<>();

    results.addAll(
        expressions.getExpressionList().stream().map(this::calculate).collect(Collectors.toList()));
    return new Tuple<>(results, file);
  }

  private Result calculate(AbstractExpression expression) {
    return new Result(expression.getId(), expression.calculate(calculator));
  }
}
