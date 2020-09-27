package org.bnov.nda.model;

import lombok.Getter;
import org.bnov.nda.calculator.Calculator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractExpression {
  @XmlAttribute private long id;
  @XmlAttribute private boolean complex;

  public abstract Long calculate(Calculator calculator);
}
