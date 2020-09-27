package org.bnov.nda.calculator.mapper;

import org.bnov.nda.calculator.model.AbstractExpressionElement;
import org.bnov.nda.calculator.model.Expressions;
import org.bnov.nda.calculator.model.addition.Addition;
import org.bnov.nda.calculator.model.addition.Item;
import org.bnov.nda.calculator.model.division.Division;
import org.bnov.nda.calculator.model.multiplication.Multiplication;
import org.bnov.nda.calculator.model.subtraction.Subtraction;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class UnmarshallerTest {

  @Test
  void givenComplexAdditionExpressions_shouldMapProperly()
      throws URISyntaxException, JAXBException, XMLStreamException {
    // GIVEN
    File additionExpressionFile =
        loadFile("expressions/simple/single_operation/complex_multi_additions.xml");
    // WHEN
    Expressions result =
        (Expressions) new Unmarshaller().unmarshall(additionExpressionFile, Expressions.class);
    // THEN
    assertThat(result).isNotNull();
    Addition firstAddition = result.getAdditions().get(0);
    assertThat(firstAddition.getId()).isEqualTo(1L);
    assertThat(firstAddition.isComplex()).isFalse();
    List<Long> firstItemsValue =
        firstAddition.getItems().stream().map(Item::getValue).collect(Collectors.toList());
    assertThat(firstItemsValue).containsExactlyInAnyOrder(2L, 3L, 4L);

    Addition secondAddition = result.getAdditions().get(1);
    assertThat(secondAddition.getId()).isEqualTo(3L);
    assertThat(secondAddition.isComplex()).isTrue();
    List<Long> secondItemsValue =
        secondAddition.getItems().stream().map(Item::getValue).collect(Collectors.toList());
    assertThat(secondItemsValue).containsExactlyInAnyOrder(7L, null, 8L);

    Item complexItem = secondAddition.getItems().get(1);
    Addition complexNodeAddition = complexItem.getAdditions().get(0);
    List<Long> complexNodeAdditionItemsValue =
        complexNodeAddition.getItems().stream().map(Item::getValue).collect(Collectors.toList());
    assertThat(complexNodeAdditionItemsValue).containsExactlyInAnyOrder(11L, 22L, 33L);

    Subtraction complexNodeSubtraction = complexItem.getSubtractions().get(0);
    assertThat(complexNodeSubtraction.getSubtrahend().getValue()).isEqualTo(234);
    assertThat(complexNodeSubtraction.getMinuend().getValue()).isEqualTo(556);

    Multiplication complexNodeMultiplication = complexItem.getMultiplications().get(0);
    List<Long> complexNodeMultiplicationItemsValue =
        complexNodeMultiplication.getFactors().stream()
            .map(AbstractExpressionElement::getValue)
            .collect(Collectors.toList());
    assertThat(complexNodeMultiplicationItemsValue).containsExactlyInAnyOrder(12L, 52L, 96L);

    Division complexNodeDivision = complexItem.getDivisions().get(0);
    assertThat(complexNodeDivision.getDivisor().getValue()).isEqualTo(450);
    assertThat(complexNodeDivision.getDividend().getValue()).isEqualTo(900);
  }

  @Test
  void givenSimpleSubtractionExpressions_shouldMapProperly()
      throws URISyntaxException, JAXBException, XMLStreamException {
    // GIVEN
    File subtractionExpressionFile =
        loadFile("expressions/simple/single_operation/simple_multi_subtractions.xml");
    // WHEN
    Expressions result =
        (Expressions) new Unmarshaller().unmarshall(subtractionExpressionFile, Expressions.class);
    // THEN
    assertThat(result.getSubtractions()).isNotNull().hasSize(2);
    Subtraction firstSubtraction = result.getSubtractions().get(0);
    Subtraction secondSubtraction = result.getSubtractions().get(1);
    assertThat(firstSubtraction.getId()).isEqualTo(11L);
    assertThat(firstSubtraction.getMinuend().getValue()).isEqualTo(3L);
    assertThat(firstSubtraction.getSubtrahend().getValue()).isEqualTo(2L);
    assertThat(secondSubtraction.getId()).isEqualTo(12L);
    assertThat(secondSubtraction.getMinuend().getValue()).isEqualTo(4L);
    assertThat(secondSubtraction.getSubtrahend().getValue()).isEqualTo(7L);
  }

  @Test
  void givenSimpleMultiplicationExpressions_shouldMapProperly()
      throws URISyntaxException, JAXBException, XMLStreamException {
    // GIVEN
    File multiplicationExpressionFile =
        loadFile("expressions/simple/single_operation/simple_multi_multiplications.xml");
    // WHEN
    Expressions result =
        (Expressions)
            new Unmarshaller().unmarshall(multiplicationExpressionFile, Expressions.class);
    // THEN
    assertThat(result.getMultiplications()).isNotNull().hasSize(2);
    Multiplication firstMultiplication = result.getMultiplications().get(0);
    Multiplication secondMultiplication = result.getMultiplications().get(1);
    assertThat(firstMultiplication.getId()).isEqualTo(3L);
    List<Long> firstMultiplicationValues =
        firstMultiplication.getFactors().stream()
            .map(AbstractExpressionElement::getValue)
            .collect(Collectors.toList());
    assertThat(firstMultiplicationValues).hasSize(3).containsExactlyInAnyOrder(5L, 6L, 8L);

    assertThat(secondMultiplication.getId()).isEqualTo(8L);
    List<Long> secondMultiplicationValues =
        secondMultiplication.getFactors().stream()
            .map(AbstractExpressionElement::getValue)
            .collect(Collectors.toList());
    assertThat(secondMultiplicationValues).hasSize(3).containsExactlyInAnyOrder(9L, 1L, 2L);
  }

  @Test
  void givenSimpleDivisionExpressions_shouldMapProperly()
      throws URISyntaxException, JAXBException, XMLStreamException {
    // GIVEN
    File divisionExpressionFile =
        loadFile("expressions/simple/single_operation/simple_multi_divisions.xml");
    // WHEN
    Expressions result =
        (Expressions) new Unmarshaller().unmarshall(divisionExpressionFile, Expressions.class);
    // THEN
    assertThat(result.getDivisions()).isNotNull().hasSize(2);
    Division firstDivision = result.getDivisions().get(0);
    Division secondSubtraction = result.getDivisions().get(1);
    assertThat(firstDivision.getId()).isEqualTo(4L);
    assertThat(firstDivision.getDividend().getValue()).isEqualTo(-54L);
    assertThat(firstDivision.getDivisor().getValue()).isEqualTo(9L);
    assertThat(secondSubtraction.getId()).isEqualTo(6L);
    assertThat(secondSubtraction.getDividend().getValue()).isEqualTo(8L);
    assertThat(secondSubtraction.getDivisor().getValue()).isEqualTo(-2L);
  }

  private File loadFile(final String path) throws URISyntaxException {
    return Paths.get(getClass().getClassLoader().getResource(path).toURI()).toFile();
  }
}
