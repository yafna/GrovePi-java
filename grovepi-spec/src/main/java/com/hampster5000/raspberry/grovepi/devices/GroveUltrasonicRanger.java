package com.hampster5000.raspberry.grovepi.devices;

import java.io.IOException;

import com.hampster5000.raspberry.grovepi.GroveDigitalPin;
import com.hampster5000.raspberry.grovepi.GrovePi;
import com.hampster5000.raspberry.grovepi.GrovePiCommands;
import com.hampster5000.raspberry.grovepi.GroveUtil;

@GroveDigitalPin
public class GroveUltrasonicRanger {

  private final GrovePi grovePi;
  private final int pin;

  public GroveUltrasonicRanger(GrovePi grovePi, int pin) {
    this.grovePi = grovePi;
    this.pin = pin;
  }

  public double get() throws IOException {
    return grovePi.exec((io) -> {
      io.write(GrovePiCommands.uRead_cmd, pin, GrovePiCommands.unused, GrovePiCommands.unused);
      io.sleep(200);
      int[] v = GroveUtil.unsign(io.read(new byte[4]));
      return (v[1] * 256) + v[2];
    });
  }

}
