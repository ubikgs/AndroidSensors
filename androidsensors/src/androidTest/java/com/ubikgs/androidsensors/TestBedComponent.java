package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.factories.SensorGathererFactoryIntegrationTest;
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
import com.ubikgs.androidsensors.modules.AndroidSensorsCoreModule;
import com.ubikgs.androidsensors.modules.AndroidSensorsEdgeModule;
import com.ubikgs.androidsensors.modules.SystemServicesModule;
import com.ubikgs.androidsensors.modules.TestBedModule;

import dagger.Component;

/**
 * Created by alberto on 11/9/17.
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

    void inject(SensorGathererFactoryIntegrationTest test);
}
