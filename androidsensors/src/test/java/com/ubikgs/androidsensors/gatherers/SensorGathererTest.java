package com.ubikgs.androidsensors.gatherers;

import com.ubikgs.androidsensors.SensorType;
import com.ubikgs.androidsensors.checkers.applevel.SensorRequirementChecker;
import com.ubikgs.androidsensors.checkers.internal.SensorChecker;
import com.ubikgs.androidsensors.checkers.permissions.PermissionChecker;
import com.ubikgs.androidsensors.config.SensorConfig;
import com.ubikgs.androidsensors.enablers.SensorEnableRequester;
import com.ubikgs.androidsensors.records.SensorRecord;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.BackpressureStrategy;
import io.reactivex.subscribers.TestSubscriber;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by alberto on 12/9/17.
 */

@RunWith(MockitoJUnitRunner.class)
public abstract class SensorGathererTest {

    @Mock protected SensorConfig sensorConfig;
    @Mock protected SensorEnableRequester sensorEnableRequester;
    @Mock protected PermissionChecker permissionChecker;
    @Mock protected SensorChecker sensorChecker;
    @Mock protected SensorRequirementChecker sensorRequirementChecker;

    protected SensorGatherer sensorGatherer;

    @Before
    public void setUp() throws Exception {
        if (sensorGatherer == null)
            throw new Error("sensorGatherer must be initialized before calling super.setUp() method");

        when(sensorConfig.getBackpressureStrategy(any())).thenReturn(BackpressureStrategy.BUFFER);
        when(permissionChecker.isPermissionGranted()).thenReturn(true);
        when(sensorChecker.isReady(any())).thenReturn(true);
    }


    @Test
    public void dataStream_withPermissionsAndSensorIsReady_returnsFlowable() throws Exception {
        TestSubscriber<SensorRecord> test = sensorGatherer.recordStream().test();

        test.assertSubscribed();
        test.dispose();
    }

    @Test
    public void dataStream_withNoPermission_returnsError() throws Exception {
        when(permissionChecker.isPermissionGranted()).thenReturn(false);

        assertFlowableReturnsErrorWithUndesiredState();
    }

    @Test
    public void dataStream_withNonReadySensor_returnsError() throws Exception {
        when(sensorChecker.isReady(any())).thenReturn(false);

        assertFlowableReturnsErrorWithUndesiredState();
    }

    private void assertFlowableReturnsErrorWithUndesiredState() {
        sensorGatherer.recordStream()
                .blockingSubscribe(sensorRecord -> fail(),
                        throwable -> assertThat(throwable.getClass(), equalTo(Error.class)));
    }

    @Test
    public void askForEnabling_callsSensorEnableRequester() throws Exception {
        sensorGatherer.askForEnabling();

        verify(sensorEnableRequester, times(1))
                .performEnableRequestFor(getSensorType());
    }

    @Test
    public void getNeededPermission_callsPermissionChecker() throws Exception {
        sensorGatherer.getNeededPermission();

        verify(permissionChecker, times(1)).getNeededPermission();
    }

    @Test
    public void hasPermissionGranted_callsPermissionChecker() throws Exception {
        sensorGatherer.hasPermissionGranted();

        verify(permissionChecker, times(1)).isPermissionGranted();
    }

    @Test
    public void isRequired_callsCriticalityChecker() throws Exception {
        sensorGatherer.isRequired();

        verify(sensorRequirementChecker, times(1)).isRequired(getSensorType());
    }

    protected abstract SensorType getSensorType();
}
