package com.github.yafna.raspberry.grovepi.pi4j;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.yafna.raspberry.grovepi.GrovePiSequenceVoid;
import com.github.yafna.raspberry.grovepi.devices.GroveRgbLcd;

public class GroveRgbLcdPi4J extends GroveRgbLcd {

  private final I2CBus bus;
  private final I2CDevice rgb;
  private final I2CDevice text;

  public GroveRgbLcdPi4J() throws IOException {
    try {
      this.bus = I2CFactory.getInstance(I2CBus.BUS_1);
    } catch (I2CFactory.UnsupportedBusNumberException e) {
      throw new GroveUnsupportedBusNumberException(e);
    }
    this.rgb = bus.getDevice(DISPLAY_RGB_ADDR);
    this.text = bus.getDevice(DISPLAY_TEXT_ADDR);
    init();
  }

  @Override
  public void close() {
    try {
      bus.close();
    } catch (IOException ex) {
      Logger.getLogger("GrovePi").log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void execRGB(GrovePiSequenceVoid sequence) throws IOException {
    synchronized (this) {
      sequence.execute(new IO(rgb));
    }
  }

  @Override
  public void execTEXT(GrovePiSequenceVoid sequence) throws IOException {
    synchronized (this) {
      sequence.execute(new IO(text));
    }
  }

}
