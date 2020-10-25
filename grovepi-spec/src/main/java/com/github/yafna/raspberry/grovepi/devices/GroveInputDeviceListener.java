package com.github.yafna.raspberry.grovepi.devices;

public interface GroveInputDeviceListener<T> {

  void onChange(T t);
}
