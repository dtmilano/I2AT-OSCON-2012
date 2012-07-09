/**
 * 
 */
package com.dtmilano.i2at.tc.test;

import android.test.ActivityInstrumentationTestCase2;

import com.dtmilano.i2at.tc.TemperatureConverterActivity;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityTests extends
        ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    /**
     * No-arg constructor.
     */
    public TemperatureConverterActivityTests() {
        this("TemperatureConverterActivityTests");
    }
    
    /**
     * @param name
     */
    public TemperatureConverterActivityTests(String name) {
        super(TemperatureConverterActivity.class);
        setName(name);
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.TemperatureConverterActivity#onCreate(android.os.Bundle)}.
     */
    public final void testOnCreateBundle() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.TemperatureConverterActivity#onCreateOptionsMenu(android.view.Menu)}.
     */
    public final void testOnCreateOptionsMenuMenu() {
        fail("Not yet implemented");
    }

}
