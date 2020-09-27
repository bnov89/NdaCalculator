package org.bnov.nda.marshal;

import java.io.File;

public interface Marshaller<T> {
  void marshall(T data, File file);
}
