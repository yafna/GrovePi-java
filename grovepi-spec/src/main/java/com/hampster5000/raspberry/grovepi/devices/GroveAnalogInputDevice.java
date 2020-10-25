package com.hampster5000.raspberry.grovepi.devices;

import java.io.IOException;

import com.hampster5000.raspberry.grovepi.GroveAnalogInListener;
import com.hampster5000.raspberry.grovepi.GroveAnalogIn;

public abstract class GroveAnalogInputDevice<T> implements Runnable, GroveAnalogInListener {

  private final GroveAnalogIn in;
  private GroveInputDeviceListener<T> listener;

  public GroveAnalogInputDevice(GroveAnalogIn in) {
    this.in = in;
    in.setListener(this);
  }

  @Override
  public void run() {
    in.run();
  }

  @Override
  public void onChange(byte[] newValue) {
    if (listener != null) {
      listener.onChange(get(newValue));
    }
  }

  public void setListener(GroveInputDeviceListener listener) {
    this.listener = listener;
  }

  public T get() throws IOException {
    return get(in.get());
  }

  public abstract T get(byte[] data);
}
