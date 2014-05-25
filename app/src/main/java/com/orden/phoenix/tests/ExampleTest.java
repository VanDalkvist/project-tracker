package com.orden.phoenix.tests;

import android.test.InstrumentationTestCase;

/**
 * Created by I_van on 25.05.2014.
 */
public class ExampleTest extends InstrumentationTestCase {

    public void test() {
        final int expected = 5;
        final int reality = 5;

        assertEquals(expected, reality);
    }
}