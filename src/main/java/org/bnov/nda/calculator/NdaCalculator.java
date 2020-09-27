package org.bnov.nda.calculator;

import org.bnov.nda.calculator.file.reader.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class NdaCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        if (args.length != 2) {
            LOGGER.error("Calculator requires exactly two arguments: \n1. The folder where input files are located\n2. The folder where input files are located");
            System.exit(0);
        }
        new Calculator(new FileReader()).process(args[0], args[1]);
    }
}
