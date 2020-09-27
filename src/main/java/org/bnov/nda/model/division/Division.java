package org.bnov.nda.model.division;

import lombok.Getter;
import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Division extends AbstractExpression {
  private Dividend dividend;
  private Divisor divisor;

  @Override
  public Long calculate(Calculator calculator) {
    return calculator.calculate(this);
  }
}
