package org.bnov.nda.marshal;

public class MarshallerFactory {
  public <T> Marshaller<T> create(Format format, Class<T> rootClass) {
    switch (format) {
      case XML:
        return new XmlMarshaller<>(rootClass);
      default:
        return null;
    }
  }
}
