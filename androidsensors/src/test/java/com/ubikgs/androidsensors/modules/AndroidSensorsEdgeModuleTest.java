package com.ubikgs.androidsensors.modules;

import android.content.Context;

import com.ubikgs.androidsensors.checkers.applevel.BasicSensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.BasicSensorConfig;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.BasicSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.GPSSensorEnableRequester;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.utils.MillisecondsToMicroseconds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 18/9/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AndroidSensorsEdgeModuleTest {

    @Mock Context context;
    @Mock MillisecondsToMicroseconds millisecondsToMicroseconds;

    @Mock SensorEnableRequester defaultSensorEnableRequester;
    @Mock SensorEnableRequester gpsSensorEnableRequester;
    @Mock
    SensorRequirementChecker sensorRequirementChecker;
    @Mock SensorConfig sensorConfig;

    private AndroidSensorsEdgeModule defaultModule;
    private AndroidSensorsEdgeModule customModule;

    @Before
    public void setUp() throws Exception {
        defaultModule = new AndroidSensorsEdgeModule();
        customModule = new AndroidSensorsEdgeModule(defaultSensorEnableRequester,
                gpsSensorEnableRequester, sensorRequirementChecker, sensorConfig);
    }

    @Test
    public void provideBasicSensorEnableRequester_withDefaultModule_returnsBasicSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideBasicSensorEnableRequester(context).getClass(),
                equalTo(BasicSensorEnableRequester.class));
    }

    @Test
    public void provideGPSSensorEnableRequester_withDefaultModule_returnsGPSSensorEnableRequester()
            throws Exception {
        assertThat(defaultModule.provideGPSSensorEnableRequester(context).getClass(),
                equalTo(GPSSensorEnableRequester.class));
    }

    @Test
    public void provideBasicCriticalityChecker_withDefaultModule_returnsBasicCriticalityChecker()
            throws Exception {
        assertThat(defaultModule.provideBasicCriticalityChecker().getClass(),
                equalTo(BasicSensorRequirementChecker.class));
    }

    @Test
    public void provideBasicSensorConfig_withDefaultModule_returnsBasicSensorConfig()
            throws Exception {
        assertThat(defaultModule.provideBasicSensorConfig(millisecondsToMicroseconds).getClass(),
                equalTo(BasicSensorConfig.class));
    }

    @Test
    public void provideBasicSensorEnableRequester_withCustomModule_returnsDefaultSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideBasicSensorEnableRequester(context),
                is(defaultSensorEnableRequester));
    }

    @Test
    public void provideGPSSensorEnableRequester_withCustomModule_returnsGPSSensorEnableRequester()
            throws Exception {
        assertThat(customModule.provideGPSSensorEnableRequester(context),
                is(gpsSensorEnableRequester));
    }

    @Test
    public void provideBasicCriticalityChecker_withCustomModule_returnsCriticalityChecker()
            throws Exception {
        assertThat(customModule.provideBasicCriticalityChecker(),
                is(sensorRequirementChecker));
    }

    @Test
    public void provideBasicSensorConfig_withCustomModule_returnsBasicSensorConfig()
            throws Exception {
        assertThat(customModule.provideBasicSensorConfig(millisecondsToMicroseconds),
                is(sensorConfig));
    }

}