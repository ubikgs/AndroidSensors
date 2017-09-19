package com.ubikgs.androidsensors.utils;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

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