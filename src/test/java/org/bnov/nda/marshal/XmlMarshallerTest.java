package org.bnov.nda.marshal;

import org.bnov.nda.model.Expressions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class XmlMarshallerTest {

  @Test
  void notExistingPathToFile_shouldThrowException() {
    XmlMarshaller<Expressions> sut = new XmlMarshaller<>(Expressions.class);
      File file = new File("not/existing/sile/path");
      Assertions.assertThrows(
        MarshallerException.class,
        () -> sut.marshall(new Expressions(), file));
  }
}
