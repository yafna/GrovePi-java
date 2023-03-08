Java part of the https://github.com/DexterInd/GrovePi project.
Has been forked and changed to get latest changes of the pi4j library. 

# IoTDevices
Grove Pi library

The grovepi for Java8 library is provided as maven projects.
Since you need native access to the raspberry pi device, use the Pi4J library (third party: http://pi4j.com/)

## BUILD
run:
mvn install

This creates the .jar files inside the target folder of each of the following projects:
- GrovePi-Spec
- GrovePi-pi4j

## INCLUDING THE LIBRARIES IN YOUR PROJECTS

Include the GrovePi-Spec jar. This is the core of the library.
Include the implementation you want to use:
- GrovePi-pi4j and the pi4j jar 

Alternatively: Use maven dependencies:

Mandatory:

    <dependency>
      <groupId>com.github.yafna.raspberry</groupId>
      <artifactId>GrovePi-spec</artifactId>
    </dependency>

Device Implementation:

    <dependency>
      <groupId>com.github.yafna.raspberry</groupId>
      <artifactId>GrovePi-pi4j</artifactId>
    </dependency>


## USAGE

Simply create a new instance of the GrovePi class with the implementation you want:

GrovePi grovepi = new GrovePi4J();

Then create the connected devices and provide the grovepi you created as parameter in constructors:

GroveTemperatureAndHumiditySensor dht = new GroveTemperatureAndHumiditySensor(grovePi, 4, GroveTemperatureAndHumiditySensor.Type.DHT11)

Or for simple digital in or out use
GroveDigitalOut led = grovePi.getDigitalOut(4);

The number in the parameters is usually the port number you are using.

*NOTE*: YOU MUST NOT CREATE MULTIPLE GrovePi objects! use the same for all the devices connected to your board. Using multiple may cause collisions in device access.
