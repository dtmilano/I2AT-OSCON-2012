/**
 * 
 */

package com.dtmilano.i2at.tc.test;

import com.dtmilano.i2at.tc.TemperatureConverter;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * @author diego
 */
public class TemperatureConverterTests extends TestCase {

    private static final HashMap<Double, Double> sConversionTableDouble = new HashMap<Double, Double>();

    static {
        sConversionTableDouble.put(0.0, 32.0);
        sConversionTableDouble.put(100.0, 212.0);
        sConversionTableDouble.put(-1.0, 30.20);
        sConversionTableDouble.put(-100.0, -148.0);
        sConversionTableDouble.put(32.0, 89.60);
        sConversionTableDouble.put(-40.0, -40.0);
        sConversionTableDouble.put(-273.0, -459.40);
    }

    /**
     * @param name
     */
    public TemperatureConverterTests(String name) {
        super(name);
    }

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for
     * {@link com.dtmilano.i2at.tc.TemperatureConverter#fahrenheitToCelsius(double)}
     * .
     */
    public final void testFahrenheitToCelsius() {
        for (double c : sConversionTableDouble.keySet()) {
            final double f = sConversionTableDouble.get(c);
            final double ca =
                    TemperatureConverter.fahrenheitToCelsius(f);
            final double delta = Math.abs(ca - c);
            assertTrue(delta < 0.005);
        }
    }

}
