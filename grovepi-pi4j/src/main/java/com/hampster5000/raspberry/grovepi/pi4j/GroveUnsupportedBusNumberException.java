package com.hampster5000.raspberry.grovepi.pi4j;

import java.io.IOException;

public class GroveUnsupportedBusNumberException extends IOException {
    public GroveUnsupportedBusNumberException(Throwable throwable) {
        super(throwable);
    }
}
