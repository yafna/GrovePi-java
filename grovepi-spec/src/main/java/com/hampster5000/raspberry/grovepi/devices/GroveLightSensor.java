package com.hampster5000.raspberry.grovepi.devices;

import java.io.IOException;

import com.hampster5000.raspberry.grovepi.GroveAnalogPin;
import com.hampster5000.raspberry.grovepi.GrovePi;
import com.hampster5000.raspberry.grovepi.GroveUtil;

@GroveAnalogPin
public class GroveLightSensor extends GroveAnalogInputDevice<Double> {

  public GroveLightSensor(GrovePi grovePi, int pin) throws IOException {
    super(grovePi.getAnalogIn(pin, 4));
  }

  @Override
  public Double get(byte[] data) {
    int[] v = GroveUtil.unsign(data);
    return (double) (v[1] * 256) + v[2];
  }
}
