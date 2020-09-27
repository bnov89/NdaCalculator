package org.bnov.nda.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bnov.nda.model.addition.Addition;
import org.bnov.nda.model.division.Division;
import org.bnov.nda.model.multiplication.Multiplication;
import org.bnov.nda.model.subtraction.Subtraction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@NoArgsConstructor
public class Expressions {
  @XmlElementRefs({
    @XmlElementRef(type = Addition.class),
    @XmlElementRef(type = Subtraction.class),
    @XmlElementRef(type = Multiplication.class),
    @XmlElementRef(type = Division.class)
  })
  private List<AbstractExpression> expressionList;
}
