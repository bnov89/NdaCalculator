package org.bnov.nda.calculator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bnov.nda.calculator.model.addition.Addition;
import org.bnov.nda.calculator.model.division.Division;
import org.bnov.nda.calculator.model.multiplication.Multiplication;
import org.bnov.nda.calculator.model.subtraction.Subtraction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
public class Expressions {
  @XmlElement(name = "addition")
  private List<Addition> additions;

  @XmlElement(name = "subtraction")
  private List<Subtraction> subtractions;

  @XmlElement(name = "multiplication")
  private List<Multiplication> multiplications;

  @XmlElement(name = "division")
  private List<Division> divisions;
}
