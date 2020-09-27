package org.bnov.nda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tuple<R, T> {
  private final R firstValue;
  private final T secondValue;
}
