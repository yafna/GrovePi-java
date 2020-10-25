package com.github.yafna.raspberry.grovepi;

import java.io.IOException;

public interface GrovePiSequence<T> {

  T execute(GroveIO io) throws IOException;

}
