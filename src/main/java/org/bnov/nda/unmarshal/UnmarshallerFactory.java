package org.bnov.nda.unmarshal;

public class UnmarshallerFactory {
  public <T> Unmarshaller<T> create(Format format, Class<T> rootClass) {
    switch (format) {
      case XML:
        return new XmlUnmarshaller<>(rootClass);
      default:
        return null;
    }
  }
}
