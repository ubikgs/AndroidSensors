package com.ubikgs.androidsensors.factories;

import com.ubikgs.androidsensors.gatherers.SensorGatherer;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by alberto on 15/9/17.
 */

public class SensorGathererFactory {

    private Set<SensorGatherer> sensorGatherers;

    @Inject
    public SensorGathererFactory(Set<SensorGatherer> sensorGatherers) {
        this.sensorGatherers = sensorGatherers;
    }

    public Set<SensorGatherer> getGatherers() {
        return new HashSet<>(Observable.fromIterable(sensorGatherers)
                .filter(gatherer -> gatherer.isCritical() ||
                        !gatherer.isCritical() && gatherer.isReady())
                .toList()
                .blockingGet());
    }
}
