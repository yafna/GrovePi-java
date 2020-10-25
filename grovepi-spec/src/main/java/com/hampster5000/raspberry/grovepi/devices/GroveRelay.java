package com.hampster5000.raspberry.grovepi.devices;

import java.io.IOException;

import com.hampster5000.raspberry.grovepi.GroveDigitalPin;
import com.hampster5000.raspberry.grovepi.GroveIO;
import com.hampster5000.raspberry.grovepi.GrovePi;
import com.hampster5000.raspberry.grovepi.GrovePiCommands;

@GroveDigitalPin
public class GroveRelay {

  private final GrovePi grovePi;
  private final int pin;

  public GroveRelay(GrovePi grovePi, int pin) throws IOException {
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
