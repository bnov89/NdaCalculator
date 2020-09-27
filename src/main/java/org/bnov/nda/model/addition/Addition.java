package org.bnov.nda.model.addition;

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
public class Addition extends AbstractExpression {
  @XmlElement(name = "item")
  private List<Item> items;

  @Override
  public Long calculate(Calculator calculator) {
    return calculator.calculate(this);
  }
}
