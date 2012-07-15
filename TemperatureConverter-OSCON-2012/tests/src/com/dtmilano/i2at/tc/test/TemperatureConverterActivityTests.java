/**
 * 
 */
package com.dtmilano.i2at.tc.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Gravity;
import android.view.View;

import com.dtmilano.i2at.tc.EditNumber;
import com.dtmilano.i2at.tc.TemperatureConverter;
import com.dtmilano.i2at.tc.TemperatureConverterActivity;

/**
 * @author diego
 *
 */
public class TemperatureConverterActivityTests extends
        ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity mActivity;
    private EditNumber mCelsius;
    private EditNumber mFahrenheit;

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

        mCelsius = (EditNumber)mActivity.findViewById(com.dtmilano.i2at.tc.R.id.celsius);
        assertNotNull(mCelsius);
        mFahrenheit = (EditNumber)mActivity.findViewById(com.dtmilano.i2at.tc.R.id.fahrenheit);
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
        mActivity.openOptionsMenu();
        mActivity.closeOptionsMenu();
        assertTrue(true);
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
    
    @UiThreadTest
    public void testFahrenheitToCelsiusConversion() {
        mCelsius.clear();
        mFahrenheit.clear();
        final double f = 32.5;
        mFahrenheit.requestFocus();
        mFahrenheit.setNumber(f);
        mCelsius.requestFocus();
        final double expected =
              TemperatureConverter.fahrenheitToCelsius(f);
        final double actual = mCelsius.getNumber();
        final double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta + " larger than expected", delta < 0.005);
    }
    
    @SmallTest
    public void testFahrenheitToCelsiusConversion_input() throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() { mCelsius.clear(); mFahrenheit.clear(); mCelsius.requestFocus(); }
        });
           
        final String c = "-123.4";
        getInstrumentation().sendStringSync(c);
        assertEquals(c, mCelsius.getText().toString());
        final double expected =
            TemperatureConverter.celsiusToFahrenheit(Double.parseDouble(c));
        final double actual = mFahrenheit.getNumber();
        final double delta = Math.abs(expected - actual);
        assertTrue("delta=" + delta + " larger than expected", delta < 0.005);
    }
}
