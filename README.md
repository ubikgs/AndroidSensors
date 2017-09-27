# AndroidSensors [![Build Status](https://travis-ci.org/ubikgs/AndroidSensors.svg?branch=master)](https://travis-ci.org/ubikgs/AndroidSensors) [ ![Download](https://api.bintray.com/packages/ubikgs/AndroidSensors/android-sensors/images/download.svg) ](https://bintray.com/ubikgs/AndroidSensors/android-sensors/_latestVersion) [![Javadocs](https://www.javadoc.io/badge/com.ubikgs/android-sensors.svg)](https://www.javadoc.io/doc/com.ubikgs/android-sensors)

A library that facilitates the access to Android devices' sensors

Uses RxJava to improve sensor records flows management experience.

## Usage

Add the dependency

```groovy
dependencies {
    implementation 'com.ubikgs:android-sensors:1.0.0-alpha4'
    
    // Needed to work with AndroidSensors output Flowables
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.3'
}
```

To start using the library, you must initialize it first:

```java
Context applicationContext;

AndroidSensors androidSensors = AndroidSensors
                         .builder()
                         .build(applicationContext);
```
Library should be initialized only once. To avoid performance issues, keep the instance as a singleton.

Then you can request some sensor gatherers in different ways:

```java
AccelerometerGatherer accelerometerGatherer = androidSensors.sensorGatherer(AccelerometerGatherer.class);

SensorGatherer sensorGatherer = androidSensors.sensorGathererBy(SensorType.ACCELEROMETER);

Set<SensorGatherer> sensorGatherers = androidSensors.allSensorGatherers();
```

To start consuming from a sensor:

```java
sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SensorRecord>() {
                        @Override
                        public void accept(SensorRecord sensorRecord) throws Exception {
                                // Update the view with each SensorRecord update
                        }
               });
```

If you re using Java 8 as a compilation and source target you can also do the following which is more succinct:

```java
sensorGatherer.recordStream()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sensorRecord -> {
                                // Update the view with each SensorRecord update
                        }
               });
```

If you prefer to work in this way you'll need [Retrolambda](https://github.com/orfjackal/retrolambda) or [Android Studio 3.0.0 Preview](https://developer.android.com/studio/preview/index.html).

When using Android Studio 3.0.0, you'll have to specify the following in your app `build.gradle`:

```groovy
android {

    compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
    }
    
}
```

You can also configure some library inner behaviors via the builder interface:

```java
Context applicationContext;
SensorEnableRequester defaultSensorEnableRequester;
SensorEnableRequester gpsSensorEnableRequester;
SensorRequirementChecker sensorRequirementChecker;
SensorConfig sensorConfig;

AndroidSensors customAndroidSensors = AndroidSensors
                               .builder()
                               .customDefaultEnableRequester(defaultSensorEnableRequester)
                               .customGPSEnableRequester(gpsSensorEnableRequester)
                               .customSensorRequirementChecker(sensorRequirementChecker)
                               .customSensorConfig(sensorConfig)
                               .build(applicationContext);
```
Check the the default implementations of each interface to get an idea about how to customize the library.


## License
    Copyright 2017 Ubik Geospatial Solutions
    Copyright 2017 Alberto González Pérez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.