package com.hampster5000.raspberry.grovepi;

import java.io.IOException;

public interface GrovePiSequenceVoid<T> {

  void execute(GroveIO io) throws IOException;

}
