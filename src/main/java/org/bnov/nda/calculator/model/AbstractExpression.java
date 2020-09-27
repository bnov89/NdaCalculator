package org.bnov.nda.calculator.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AbstractExpression {
  @XmlAttribute private long id;
  @XmlAttribute private boolean complex;
}
