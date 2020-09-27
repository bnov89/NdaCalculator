package org.bnov.nda.calculator.model.addition;

import lombok.Data;
import org.bnov.nda.calculator.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Addition extends AbstractExpression {
  @XmlElement(name = "item")
  private List<Item> items;
}
