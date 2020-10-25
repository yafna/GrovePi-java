package com.hampster5000.raspberry.grovepi.devices;

import java.io.IOException;

import com.hampster5000.raspberry.grovepi.GroveAnalogPin;
import com.hampster5000.raspberry.grovepi.GrovePi;
import com.hampster5000.raspberry.grovepi.GroveUtil;

@GroveAnalogPin
public class GroveSoundSensor extends GroveAnalogInputDevice<Double> {

  public GroveSoundSensor(GrovePi grovePi, int pin) throws IOException {
    super(grovePi.getAnalogIn(pin, 4));
  }

  @Override
  public Double get(byte[] b) {
    int[] v = GroveUtil.unsign(b);
    return (double) (v[1] * 256) + v[2];
  }
}
