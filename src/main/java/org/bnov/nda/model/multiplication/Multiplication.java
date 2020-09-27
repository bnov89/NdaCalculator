package org.bnov.nda.model.multiplication;

import lombok.Getter;
import org.bnov.nda.calculator.Calculator;
import org.bnov.nda.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Multiplication extends AbstractExpression {
  @XmlElement(name = "factor")
  List<Factor> factors;

  @Override
  public Long calculate(Calculator calculator) {
    return calculator.calculate(this);
  }
}
