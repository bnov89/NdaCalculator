package org.bnov.nda.unmarshal;

import java.io.File;

public interface Unmarshaller<T> {
  T unmarshall(File file);
}
