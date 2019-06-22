/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nflowcoffee.android.utilities

import org.junit.Assert.assertEquals
import org.junit.Test

class GrowZoneUtilTest {

    @Test fun getZoneForLatitude() {
        assertEquals(13, com.nflowcoffee.android.utilities.getZoneForLatitude(0.0))
        assertEquals(13, com.nflowcoffee.android.utilities.getZoneForLatitude(7.0))
        assertEquals(12, com.nflowcoffee.android.utilities.getZoneForLatitude(7.1))
        assertEquals(1, com.nflowcoffee.android.utilities.getZoneForLatitude(84.1))
        assertEquals(1, com.nflowcoffee.android.utilities.getZoneForLatitude(90.0))
    }

    @Test fun getZoneForLatitude_negativeLatitudes() {
        assertEquals(13, com.nflowcoffee.android.utilities.getZoneForLatitude(-7.0))
        assertEquals(12, com.nflowcoffee.android.utilities.getZoneForLatitude(-7.1))
        assertEquals(1, com.nflowcoffee.android.utilities.getZoneForLatitude(-84.1))
        assertEquals(1, com.nflowcoffee.android.utilities.getZoneForLatitude(-90.0))
    }

    // Bugfix test for https://github.com/googlesamples/android-sunflower/issues/8
    @Test fun getZoneForLatitude_GitHub_issue8() {
        assertEquals(9, com.nflowcoffee.android.utilities.getZoneForLatitude(35.0))
        assertEquals(8, com.nflowcoffee.android.utilities.getZoneForLatitude(42.0))
        assertEquals(7, com.nflowcoffee.android.utilities.getZoneForLatitude(49.0))
        assertEquals(6, com.nflowcoffee.android.utilities.getZoneForLatitude(56.0))
        assertEquals(5, com.nflowcoffee.android.utilities.getZoneForLatitude(63.0))
        assertEquals(4, com.nflowcoffee.android.utilities.getZoneForLatitude(70.0))
    }
}