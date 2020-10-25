package com.hampster5000.raspberry.grovepi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroveAnalogIn implements Runnable {

  private final GrovePi grovePi;
  private final int pin;
  private GroveAnalogInListener listener;
  private final int bufferSize;

  public GroveAnalogIn(GrovePi grovePi, int pin, int bufferSize) throws IOException {
    this.grovePi = grovePi;
    this.pin = pin;
    grovePi.execVoid((io) -> io.write(GrovePiCommands.pMode_cmd, GrovePiCommands.pMode_in_arg, pin, GrovePiCommands.unused));
    this.bufferSize = bufferSize;
  }

  @Override
  public void run() {
    try {
      get();
    } catch (IOException ex) {
      Logger.getLogger("GrovePi").log(Level.SEVERE, null, ex);
    }
  }

  public byte[] get() throws IOException {
    byte[] value = grovePi.exec((io) -> {
      io.write(GrovePiCommands.aRead_cmd, pin, GrovePiCommands.unused, GrovePiCommands.unused);
      io.sleep(100);
      return io.read(new byte[bufferSize]);
    });
    if (listener != null) {
      listener.onChange(value);
    }
    return value;
  }

  public void setListener(GroveAnalogInListener listener) {
    this.listener = listener;
  }

}
