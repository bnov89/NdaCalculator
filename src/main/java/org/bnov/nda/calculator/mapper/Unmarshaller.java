package org.bnov.nda.calculator.mapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class Unmarshaller<T> {

  public T unmarshall(File file, Class<T> rootClass) throws JAXBException, XMLStreamException {
    JAXBContext context = JAXBContext.newInstance(rootClass);
    javax.xml.bind.Unmarshaller unmarshaller = context.createUnmarshaller();
    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StreamSource(file));
    xmlStreamReader =
        xmlInputFactory.createFilteredReader(
            xmlStreamReader,
            reader -> {
              if (reader.getEventType() == XMLStreamConstants.CHARACTERS) {
                return reader.getText().trim().length() > 0;
              }
              return true;
            });

    return (T) unmarshaller.unmarshal(xmlStreamReader);
  }
}
