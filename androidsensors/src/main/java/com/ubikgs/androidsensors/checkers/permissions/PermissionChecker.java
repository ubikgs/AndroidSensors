package com.ubikgs.androidsensors.checkers.permissions;

/**
 * Created by alberto on 11/9/17.
 */

public interface PermissionChecker {
    String getNeededPermission();
    boolean isPermissionGranted();
}
