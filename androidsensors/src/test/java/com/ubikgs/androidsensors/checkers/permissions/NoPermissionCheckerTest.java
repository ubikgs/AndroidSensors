package com.ubikgs.androidsensors.checkers.permissions;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 11/9/17.
 */
public class NoPermissionCheckerTest {

    private NoPermissionChecker noPermissionChecker;

    @Before
    public void setUp() throws Exception {
        noPermissionChecker = new NoPermissionChecker();
    }

    @Test
    public void getNeededPermission() throws Exception {
        assertThat(noPermissionChecker.getNeededPermission(), equalTo(""));
    }

    @Test
    public void hasPermission() throws Exception {
        assertThat(noPermissionChecker.isPermissionGranted(), is(true));
    }

}