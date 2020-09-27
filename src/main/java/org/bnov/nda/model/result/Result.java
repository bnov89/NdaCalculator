package org.bnov.nda.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Getter
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
  @XmlAttribute private final Long id;
  @XmlValue private final Long calculationResult;
}
