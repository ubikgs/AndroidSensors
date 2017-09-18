package com.ubikgs.androidsensors.checkers.permissions;

import javax.inject.Inject;

/**
 * Created by alberto on 11/9/17.
 */

public class NoPermissionChecker implements PermissionChecker {

    @Inject
    public NoPermissionChecker() {
    }

    @Override
    public String getNeededPermission() {
        return "";
    }

    @Override
    public boolean isPermissionGranted() {
        return true;
    }
}
