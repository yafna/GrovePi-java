package com.hampster5000.raspberry.grovepi;

import java.io.IOException;

public class GroveAnalogOut {

  private final GrovePi grovePi;
  private final int pin;

  public GroveAnalogOut(GrovePi grovePi, int pin) throws IOException {
    this.grovePi = grovePi;
    this.pin = pin;
    grovePi.execVoid((GroveIO io) -> io.write(GrovePiCommands.pMode_cmd, pin, GrovePiCommands.pMode_out_arg, GrovePiCommands.unused));
  }

  public void set(double value) throws IOException {
    int[] command = new int[8];
    command[0] = GrovePiCommands.aWrite_cmd;
    command[1] = pin;
    command[2] = 1;
    command[3] = 3;
    command[4] = GrovePiCommands.unused;
    grovePi.execVoid((GroveIO io) -> io.write(command));
  }

}
