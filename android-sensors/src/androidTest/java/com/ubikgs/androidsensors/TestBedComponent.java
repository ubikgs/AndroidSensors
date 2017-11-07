package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.gatherers.GPSSensorGatheringTest;
import com.ubikgs.androidsensors.gatherers.IMUSensorFrequencyTest;
import com.ubikgs.androidsensors.gatherers.gps.LocationGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSMeasurementsGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSNavigationGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.gps.RawGPSStatusGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.AccelerometerGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.GravityGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.GyroscopeGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.LinearAccelerationGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.MagneticFieldGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.imu.RotationVectorGathererIntegrationTest;
import com.ubikgs.androidsensors.gatherers.wifi.WifiMeasurementsGathererIntegrationTest;
import com.ubikgs.androidsensors.modules.AndroidSensorsCoreModule;
import com.ubikgs.androidsensors.modules.AndroidSensorsEdgeModule;
import com.ubikgs.androidsensors.modules.SystemServicesModule;
import com.ubikgs.androidsensors.modules.TestBedModule;

import dagger.Component;

/**
 *  Copyright 2017 Alberto González Pérez
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     <p>http://www.apache.org/licenses/LICENSE-2.0</p>
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@Component(modules = {
        TestBedModule.class,
        SystemServicesModule.class,
        AndroidSensorsCoreModule.class,
        AndroidSensorsEdgeModule.class
})
public interface TestBedComponent {
    void inject(AccelerometerGathererIntegrationTest test);

    void inject(IMUSensorFrequencyTest test);

    void inject(GravityGathererIntegrationTest test);

    void inject(GyroscopeGathererIntegrationTest test);

    void inject(LinearAccelerationGathererIntegrationTest test);

    void inject(MagneticFieldGathererIntegrationTest test);

    void inject(RotationVectorGathererIntegrationTest test);

    void inject(LocationGathererIntegrationTest test);

    void inject(RawGPSMeasurementsGathererIntegrationTest test);

    void inject(RawGPSNavigationGathererIntegrationTest test);

    void inject(RawGPSStatusGathererIntegrationTest test);

    void inject(GPSSensorGatheringTest test);

    void inject(AndroidSensorsIntegrationTest test);

    void inject(WifiMeasurementsGathererIntegrationTest test);
}
