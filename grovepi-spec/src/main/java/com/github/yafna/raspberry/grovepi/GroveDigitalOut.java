package com.github.yafna.raspberry.grovepi;

import java.io.IOException;

public class GroveDigitalOut {

  private final GrovePi grovePi;
  private final int pin;

  public GroveDigitalOut(GrovePi grovePi, int pin) throws IOException {
    this.grovePi = grovePi;
    this.pin = pin;
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.pMode_cmd, pin, GrovePiCommands.pMode_out_arg, GrovePiCommands.unused));
    set(false);
  }

  public final void set(boolean value) throws IOException {
    int val = value ? 1 : 0;
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.dWrite_cmd, pin, val, GrovePiCommands.unused));
  }
}
