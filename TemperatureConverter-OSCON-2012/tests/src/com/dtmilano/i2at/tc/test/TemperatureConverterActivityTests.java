/**
 * 
 */
package com.dtmilano.i2at.tc.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.dtmilano.i2at.tc.TemperatureConverterActivity;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityTests extends
        ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity mActivity;
    private EditText mCelsius;
    private EditText mFahrenheit;

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

        mActivity = getActivity();
        assertNotNull(mActivity);

        mCelsius = (EditText)mActivity.findViewById(com.dtmilano.i2at.tc.R.id.celsius);
        assertNotNull(mCelsius);
        mFahrenheit = (EditText)mActivity.findViewById(com.dtmilano.i2at.tc.R.id.fahrenheit);
        assertNotNull(mFahrenheit);
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
        assertNotNull(mActivity);
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.TemperatureConverterActivity#onCreateOptionsMenu(android.view.Menu)}.
     */
    public final void testOnCreateOptionsMenuMenu() {
        fail("Not yet implemented");
    }

    @SmallTest
    public void testFieldsOnScreen() {
        final View origin = 
                mActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(origin, mCelsius);
        ViewAsserts.assertOnScreen(origin, mFahrenheit);
    }
    
    @SmallTest
    public void testAlignment() {
            ViewAsserts.assertRightAligned(mCelsius,
                 mFahrenheit);
            ViewAsserts.assertLeftAligned(mCelsius,
                 mFahrenheit);
    }
    
    @SmallTest
    public void testFieldsShouldStartEmpty() {
            assertTrue("".equals(mCelsius.getText()
                 .toString()));
            assertTrue("".equals(mFahrenheit.getText()
                 .toString()));
    }
    
    @SmallTest
    public void testJustification() {
            final int expected = 
            Gravity.RIGHT|Gravity.CENTER_VERTICAL;
            assertEquals(expected,
                 mCelsius.getGravity());
            assertEquals(expected,
                 mFahrenheit.getGravity());
    }
}
