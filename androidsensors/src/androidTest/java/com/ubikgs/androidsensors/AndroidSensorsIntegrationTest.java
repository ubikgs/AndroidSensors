package com.ubikgs.androidsensors;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.gatherers.SensorGatherer;
import com.ubikgs.androidsensors.gatherers.gps.LocationGatherer;
import com.ubikgs.androidsensors.gatherers.imu.AccelerometerGatherer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 18/9/17.
 */
@RunWith(AndroidJUnit4.class)
public class AndroidSensorsIntegrationTest {

    @Inject Context context;
    private Helper helper;

    private AndroidSensors defaultAndroidSensors;
    private AndroidSensors customAndroidSensors;


    @Before
    public void setUp() throws Exception {
        DaggerTestBedComponent.create().inject(this);

        helper = new Helper();

        defaultAndroidSensors = AndroidSensors
                .builder()
                .build(context);

        customAndroidSensors = AndroidSensors
                .builder()
                .customDefaultEnableRequester(helper.defaultSensorEnableRequester)
                .customGPSEnableRequester(helper.gpsSensorEnableRequester)
                .customSensorRequirementChecker(helper.sensorRequirementChecker)
                .customSensorConfig(helper.sensorConfig)
                .build(context);
    }

    @Test
    public void sensorGatherers_withDefaultConfigIsEmpty_isFalse() throws Exception {
        boolean empty = defaultAndroidSensors.allSensorGatherers().isEmpty();
        assertThat(empty, is(false));
    }



    @Test
    public void sensorGathererFor_withDefaultConfig_returnsAccelerometerGatherer() throws Exception {
        SensorGatherer sensorGatherer =
                defaultAndroidSensors.sensorGathererBy(SensorType.ACCELEROMETER);

        assertThat(sensorGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withDefaultConfig_returnsAccelerometerGatherer() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                defaultAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        assertThat(accelerometerGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withDefaultConfig_returnsLocationGatherer() throws Exception {
        LocationGatherer locationGatherer =
                defaultAndroidSensors.sensorGatherer(LocationGatherer.class);

        assertThat(locationGatherer, not(equalTo(null)));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomDefaultEnableRequester() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.askForEnabling();
        assertThat(helper.defaultSensorEnableRequesterCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomGPSEnableRequester() throws Exception {
        LocationGatherer locationGatherer =
                customAndroidSensors.sensorGatherer(LocationGatherer.class);

        locationGatherer.askForEnabling();
        assertThat(helper.gpsEnableRequesterCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomSensorRequirementChecker() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.isRequired();
        assertThat(helper.sensorRequirementCheckerCalled, is(true));
    }

    @Test
    public void sensorGatherer_withCustomConfig_returnsWithCustomSensorConfig() throws Exception {
        AccelerometerGatherer accelerometerGatherer =
                customAndroidSensors.sensorGatherer(AccelerometerGatherer.class);

        accelerometerGatherer.recordStream().subscribe().dispose();
        assertThat(helper.sensorConfigCalled, is(true));
    }

    /*
    * Helper objects
    * */

    private class Helper {
        boolean defaultSensorEnableRequesterCalled = false;
        boolean gpsEnableRequesterCalled = false;
        boolean sensorRequirementCheckerCalled = false;
        boolean sensorConfigCalled = false;

        SensorEnableRequester defaultSensorEnableRequester = sensorType -> defaultSensorEnableRequesterCalled = true;

        SensorEnableRequester gpsSensorEnableRequester = sensorType -> gpsEnableRequesterCalled = true;

        SensorRequirementChecker sensorRequirementChecker = sensorType -> {
            sensorRequirementCheckerCalled = true;
            return true;
        };

        SensorConfig sensorConfig = new SensorConfig() {
            @Override
            public long getMinSensorDelay(SensorType sensorType) {
                sensorConfigCalled = true;
                return 0;
            }

            @Override
            public BackpressureStrategy getBackpressureStrategy(SensorType sensorType) {
                sensorConfigCalled = true;
                return BackpressureStrategy.BUFFER;
            }
        };
    }
}