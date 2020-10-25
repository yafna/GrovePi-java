package com.github.yafna.raspberry.grovepi.devices;

import java.io.IOException;

import com.github.yafna.raspberry.grovepi.GroveDigitalPin;
import com.github.yafna.raspberry.grovepi.GroveIO;
import com.github.yafna.raspberry.grovepi.GrovePi;
import com.github.yafna.raspberry.grovepi.GrovePiCommands;

@GroveDigitalPin
public class GroveLed {

  public static int MAX_BRIGTHNESS = 255;

  private final GrovePi grovePi;
  private final int pin;

  public GroveLed(GrovePi grovePi, int pin) throws IOException {
    this.grovePi = grovePi;
    this.pin = pin;
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.pMode_cmd, pin, GrovePiCommands.pMode_out_arg, GrovePiCommands.unused));
    set(false);
  }

  public final void set(boolean value) throws IOException {
    int val = value ? 1 : 0;
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.dWrite_cmd, pin, val, GrovePiCommands.unused));
  }

  public final void set(int value) throws IOException {
    final int val = ((value > 255) ? 255 : (value < 0 ? 0 : value));
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.aWrite_cmd, pin, val, GrovePiCommands.unused));
  }

}
