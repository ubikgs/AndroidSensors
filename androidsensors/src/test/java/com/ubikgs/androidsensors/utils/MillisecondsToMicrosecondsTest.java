package com.ubikgs.androidsensors.utils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by alberto on 15/3/17.
 */
public class MillisecondsToMicrosecondsTest {

    private MillisecondsToMicroseconds millisecondsToMicroseconds;

    @Before
    public void setUp() throws Exception {
        millisecondsToMicroseconds = new MillisecondsToMicroseconds();
    }

    @Test
    public void convert_withEverythingOk_returnsConversionInUs() throws Exception {
        assertThat(millisecondsToMicroseconds.convert(1), equalTo(1000L));
    }

    @Test
    public void convert_longOver_returnsLongMax() throws Exception {
        assertThat(millisecondsToMicroseconds.convert(Long.MAX_VALUE), equalTo(Long.MAX_VALUE));
    }

    @Test
    public void convert_longCornerCase_returnsOk() throws Exception {
        long maxFittableLong1KMultiple = Long.MAX_VALUE / 1000L;
        assertThat(millisecondsToMicroseconds.convert(maxFittableLong1KMultiple), equalTo(maxFittableLong1KMultiple * 1000L));
    }

    @Test
    public void convert_longCornerCasePlus1_returnsLongMax() throws Exception {
        long maxFittableLong1KMultiplePlus1 = (Long.MAX_VALUE / 1000L) + 1;
        assertThat(millisecondsToMicroseconds.convert(maxFittableLong1KMultiplePlus1), equalTo(Long.MAX_VALUE));
    }

}