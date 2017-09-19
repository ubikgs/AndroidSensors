package com.ubikgs.androidsensors;

import com.ubikgs.androidsensors.modules.AndroidSensorsCoreModule;
import com.ubikgs.androidsensors.modules.AndroidSensorsEdgeModule;
import com.ubikgs.androidsensors.modules.AndroidSystemModule;
import com.ubikgs.androidsensors.modules.SystemServicesModule;

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
