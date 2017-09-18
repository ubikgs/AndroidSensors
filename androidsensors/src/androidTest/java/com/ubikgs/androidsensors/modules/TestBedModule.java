package com.ubikgs.androidsensors.modules;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 11/9/17.
 */

@Module
public class TestBedModule {

    @Provides
    Context provideContext() {
        return InstrumentationRegistry.getTargetContext();
    }
}
