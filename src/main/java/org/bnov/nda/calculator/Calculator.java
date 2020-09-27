package org.bnov.nda.calculator;

import org.bnov.nda.model.addition.Addition;
import org.bnov.nda.model.addition.Item;
import org.bnov.nda.model.division.Dividend;
import org.bnov.nda.model.division.Division;
import org.bnov.nda.model.division.Divisor;
import org.bnov.nda.model.multiplication.Factor;
import org.bnov.nda.model.multiplication.Multiplication;
import org.bnov.nda.model.subtraction.Minuend;
import org.bnov.nda.model.subtraction.Subtraction;
import org.bnov.nda.model.subtraction.Subtrahend;

public class Calculator {

  public Long calculate(Addition addition) {
    return addition.getItems().stream().map(this::calculate).reduce(0L, Long::sum);
  }

  private Long calculate(Item item) {
    if (item.isSimpleType()) {
      return item.getValue();
    } else {
      return item.getExpression().calculate(this);
    }
  }

  public Long calculate(Subtraction subtraction) {
    Minuend minuend = subtraction.getMinuend();
    Subtrahend subtrahend = subtraction.getSubtrahend();
    long minuendLong;
    long subtrahendLong;
    if (minuend.isSimpleType()) {
      minuendLong = minuend.getValue();
    } else {
      minuendLong = minuend.getExpression().calculate(this);
    }
    if (subtrahend.isSimpleType()) {
      subtrahendLong = subtrahend.getValue();
    } else {
      subtrahendLong = subtrahend.getExpression().calculate(this);
    }
    return minuendLong - subtrahendLong;
  }

  public Long calculate(Multiplication multiplication) {
    return multiplication.getFactors().stream()
        .map(this::calculate)
        .reduce(1L, Math::multiplyExact);
  }

  private Long calculate(Factor factor) {
    if (factor.isSimpleType()) {
      return factor.getValue();
    }
    return factor.getExpression().calculate(this);
  }

  public Long calculate(Division division) {
    Dividend dividend = division.getDividend();
    Divisor divisor = division.getDivisor();

    Long dividendLong;
    Long divisiorLong;
    if (dividend.isSimpleType()) {
      dividendLong = dividend.getValue();
    } else {
      dividendLong = dividend.getExpression().calculate(this);
    }
    if (divisor.isSimpleType()) {
      divisiorLong = divisor.getValue();
    } else {
      divisiorLong = divisor.getExpression().calculate(this);
    }
    return Math.floorDiv(dividendLong, divisiorLong);
  }
}
