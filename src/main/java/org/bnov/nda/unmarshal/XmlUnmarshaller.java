package org.bnov.nda.unmarshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

class XmlUnmarshaller<T> implements Unmarshaller<T> {

  private final Class<T> rootClass;

  XmlUnmarshaller(Class<T> rootClass) {
    this.rootClass = rootClass;
  }

  public T unmarshall(File file) {
    try {
      JAXBContext context =
          JAXBContext.newInstance(rootClass);
      javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLStreamReader xmlStreamReader =
          xmlInputFactory.createXMLStreamReader(new StreamSource(file));
      xmlStreamReader =
          xmlInputFactory.createFilteredReader(
              xmlStreamReader,
              reader -> {
                if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
                  return reader.getText().trim().length() > 0;
                }
                return true;
              });
      return unmarshaller.unmarshal(xmlStreamReader, rootClass).getValue();
    } catch (JAXBException | XMLStreamException e) {
      throw new UnmarshallerException(
          String.format("Couldn't unmarshall file: %s and root class: %s", file, rootClass), e);
    }
  }
}
