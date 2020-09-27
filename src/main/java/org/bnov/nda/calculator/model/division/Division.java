package org.bnov.nda.calculator.model.division;

import lombok.Data;
import org.bnov.nda.calculator.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Division extends AbstractExpression {
  private Dividend dividend;
  private Divisor divisor;
}
