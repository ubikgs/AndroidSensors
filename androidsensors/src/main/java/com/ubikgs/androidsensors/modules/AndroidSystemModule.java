package com.ubikgs.androidsensors.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 18/9/17.
 */

@Module
public class AndroidSystemModule {

    Context context;

    public AndroidSystemModule(Context applicationContext) {
        this.context = applicationContext;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}