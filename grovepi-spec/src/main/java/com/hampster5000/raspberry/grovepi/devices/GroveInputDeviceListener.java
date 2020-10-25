package com.hampster5000.raspberry.grovepi.devices;

public interface GroveInputDeviceListener<T> {

  void onChange(T t);
}
