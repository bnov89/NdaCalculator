package org.bnov.nda.model.subtraction;

import lombok.Getter;
import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Subtraction extends AbstractExpression {
  @XmlElement private Minuend minuend;
  @XmlElement private Subtrahend subtrahend;

  @Override
  public Long calculate(Calculator calculator) {
    return calculator.calculate(this);
  }
}
