package com.github.yafna.raspberry.grovepi.pi4j;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.yafna.raspberry.grovepi.GrovePi;
import com.github.yafna.raspberry.grovepi.GrovePiSequence;
import com.github.yafna.raspberry.grovepi.GrovePiSequenceVoid;
import com.github.yafna.raspberry.grovepi.devices.GroveRgbLcd;

/**
 * Create a new GrovePi interface using the Pi4j library
 *
 * @author Eduardo Moranchel <emoranchel@asmatron.org>
 */
public class GrovePi4J implements GrovePi {

  private static final int GROVEPI_ADDRESS = 4;
  private final I2CBus bus;
  private final I2CDevice device;

  public GrovePi4J() throws IOException {
    try {
      this.bus = I2CFactory.getInstance(I2CBus.BUS_1);
    } catch (I2CFactory.UnsupportedBusNumberException e) {
      throw new GroveUnsupportedBusNumberException(e);
    }
    this.device = bus.getDevice(GROVEPI_ADDRESS);
  }

  @Override
  public <T> T exec(GrovePiSequence<T> sequence) throws IOException {
    synchronized (this) {
      return sequence.execute(new IO(device));
    }
  }

  @Override
  public void execVoid(GrovePiSequenceVoid sequence) throws IOException {
    synchronized (this) {
      sequence.execute(new IO(device));
    }
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
  public GroveRgbLcd getLCD() throws IOException {
    return new GroveRgbLcdPi4J();
  }
}
