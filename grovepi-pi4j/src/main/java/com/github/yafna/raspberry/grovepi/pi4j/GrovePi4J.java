package com.github.yafna.raspberry.grovepi.pi4j;

import com.github.yafna.raspberry.grovepi.GrovePi;
import com.github.yafna.raspberry.grovepi.GrovePiSequence;
import com.github.yafna.raspberry.grovepi.GrovePiSequenceVoid;
import com.github.yafna.raspberry.grovepi.devices.GroveRgbLcd;
import com.pi4j.context.Context;
import com.pi4j.io.exception.IOInvalidIDException;
import com.pi4j.io.exception.IONotFoundException;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CConfigBuilder;
import com.pi4j.io.i2c.impl.DefaultI2CConfig;

import java.io.IOException;

/**
 * Create a new GrovePi interface using the Pi4j library
 *
 * @author Eduardo Moranchel <emoranchel@asmatron.org>
 */
public class GrovePi4J implements GrovePi {
    public static final String UNIQUE_ID = "Grove-Pi-BUS-1";
    public static final int I2C_BUS = 1;
    public static final int GROVEPI_ADDRESS = 4;
    private I2C device;
    private Context pi4j;

    public GrovePi4J(Context pi4j) throws IOException {
        this.pi4j = pi4j;
        I2CConfig config = I2C.newConfigBuilder(pi4j)
                .id(UNIQUE_ID)
                .name("My I2C Bus")
                .bus(I2C_BUS)
                .device(GROVEPI_ADDRESS)
                .build();
        this.device = pi4j.create(config);
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
        device.close();
    }

    @Override
    public GroveRgbLcd getLCD() throws IOException {
        return new GroveRgbLcdPi4J(pi4j);
    }
}
