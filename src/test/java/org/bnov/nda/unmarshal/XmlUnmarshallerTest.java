package org.bnov.nda.unmarshal;

import org.bnov.nda.model.Expressions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class XmlUnmarshallerTest {
  @Test
  void invalidXml_shouldThrowException() {
    final XmlUnmarshaller<Expressions> sut = new XmlUnmarshaller<>(Expressions.class);
    File file = new File("/e2e/invalid/invalid.xml");
    final UnmarshallerException exceptionThrown =
        Assertions.assertThrows(UnmarshallerException.class, () -> sut.unmarshall(file));
  }
}
