package org.bnov.nda.marshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

class XmlMarshaller<T> implements Marshaller<T> {

  private final Class<T> rootClass;

  public XmlMarshaller(Class<T> rootClass) {
    this.rootClass = rootClass;
  }

  public void marshall(T data, File file) {
    try {
      JAXBContext context = JAXBContext.newInstance(rootClass);
      javax.xml.bind.Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(data, file);
    } catch (JAXBException e) {
      throw new MarshallerException(String.format("Couldn't marshall to file: %s", file), e);
    }
  }
}
