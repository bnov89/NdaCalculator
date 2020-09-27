package org.bnov.nda.model;

import lombok.Getter;
import org.bnov.nda.model.addition.Addition;
import org.bnov.nda.model.division.Division;
import org.bnov.nda.model.multiplication.Multiplication;
import org.bnov.nda.model.subtraction.Subtraction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import java.util.List;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractExpressionElement {
  @XmlMixed private List<String> value;
  @XmlElementRefs({
          @XmlElementRef(type = Addition.class),
          @XmlElementRef(type = Subtraction.class),
          @XmlElementRef(type = Multiplication.class),
          @XmlElementRef(type = Division.class)
  })
  private AbstractExpression expression;
  public Long getValue() {
    return value != null && !value.isEmpty() ? Long.valueOf(value.get(0)) : null;
  }

  public boolean isSimpleType() {
    return value != null && !value.isEmpty();
  }
}
