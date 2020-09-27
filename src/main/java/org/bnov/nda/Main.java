package org.bnov.nda;

import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.marshal.Format;
import org.bnov.nda.marshal.MarshallerFactory;
import org.bnov.nda.model.Expressions;
import org.bnov.nda.unmarshal.UnmarshallerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class Main {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public static void main(String[] args) {
    if (args.length != 2) {
      LOGGER.error(
          "Calculator requires exactly two arguments: \n1. The folder where input files are located\n2. The folder where input files are located");
      System.exit(-1);
    }
    LOGGER.info("Start processing... data");
    new NdaCalculator(
            new MarshallerFactory().create(Format.XML, org.bnov.nda.model.result.Expressions.class),
            new UnmarshallerFactory().create(org.bnov.nda.unmarshal.Format.XML, Expressions.class),
            new Calculator())
        .process(args[0], args[1]);
    LOGGER.info("Application finished work.");
  }
}
