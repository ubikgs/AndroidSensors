package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.modules.AndroidSensorsCoreModule;
import com.ubikgs.androidsensors.modules.AndroidSensorsEdgeModule;
import com.ubikgs.androidsensors.modules.AndroidSystemModule;
import com.ubikgs.androidsensors.modules.SystemServicesModule;

import dagger.Component;

/**
 * Created by alberto on 18/9/17.
 */

@Component(modules = {
        AndroidSystemModule.class,
        SystemServicesModule.class,
        AndroidSensorsCoreModule.class,
        AndroidSensorsEdgeModule.class
})
public interface AndroidSensorsComponent {

    @Component.Builder
    interface Builder {
        Builder androidSystemModule(AndroidSystemModule androidSystemModule);
        Builder androidSensorsEdgeModule(AndroidSensorsEdgeModule androidSensorsEdgeModule);
        AndroidSensorsComponent build();
    }

    void inject(AndroidSensors androidSensors);
}
