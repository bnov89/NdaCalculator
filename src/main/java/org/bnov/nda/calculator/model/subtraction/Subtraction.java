package org.bnov.nda.calculator.model.subtraction;

import lombok.Data;
import org.bnov.nda.calculator.model.AbstractExpression;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Subtraction extends AbstractExpression {
  @XmlElement private Minuend minuend;
  @XmlElement private Subtrahend subtrahend;
}
