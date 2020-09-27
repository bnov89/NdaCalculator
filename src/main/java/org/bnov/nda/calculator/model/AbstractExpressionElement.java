package org.bnov.nda.calculator.model;

import lombok.Data;
import org.bnov.nda.calculator.model.addition.Addition;
import org.bnov.nda.calculator.model.division.Division;
import org.bnov.nda.calculator.model.multiplication.Multiplication;
import org.bnov.nda.calculator.model.subtraction.Subtraction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractExpressionElement {
  @XmlMixed private List<String> value;
  @XmlElement(name = "addition")
  private List<Addition> additions;

  @XmlElement(name = "subtraction")
  private List<Subtraction> subtractions;

  @XmlElement(name = "multiplication")
  private List<Multiplication> multiplications;

  @XmlElement(name = "division")
  private List<Division> divisions;

  public Long getValue() {
    return value != null && !value.isEmpty() ? Long.valueOf(value.get(0)) : null;
  }
}
