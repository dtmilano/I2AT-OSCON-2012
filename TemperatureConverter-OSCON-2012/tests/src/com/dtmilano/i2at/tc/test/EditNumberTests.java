/**
 * 
 */
package com.dtmilano.i2at.tc.test;

import android.test.AndroidTestCase;

import com.dtmilano.i2at.tc.EditNumber;

/**
 * @author diego
 *
 */
public class EditNumberTests extends AndroidTestCase {

    private EditNumber mEditNumber;

    public EditNumberTests() {
        this("EditNumberTests");
    }
    /**
     * @param name
     */
    public EditNumberTests(String name) {
        setName(name);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        
        mEditNumber = new EditNumber(getContext());
    }

    /* (non-Javadoc)
     * @see android.test.InstrumentationTestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#EditNumber(android.content.Context)}.
     */
    public final void testEditNumberContext() {
        assertNotNull(mEditNumber);
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#EditNumber(android.content.Context, android.util.AttributeSet)}.
     */
    public final void testEditNumberContextAttributeSet() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#EditNumber(android.content.Context, android.util.AttributeSet, int)}.
     */
    public final void testEditNumberContextAttributeSetInt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#clear()}.
     */
    public final void testClear() {
        final String value = "123.45";
        mEditNumber.setText(value);
        mEditNumber.clear();
        final String expected = "";
        final String actual =
             mEditNumber.getText().toString();
        assertEquals(expected, actual);
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#setNumber(double)}.
     */
    public final void testSetNumber() {
        final double d = 123.45;
        mEditNumber.setNumber(d);
        final String expected = Double.toString(d);
        final String actual = 
             mEditNumber.getText().toString();
        assertEquals(expected, actual);
    }

    /**
     * Test method for {@link com.dtmilano.i2at.tc.EditNumber#getNumber()}.
     */
    public final void testGetNumber() {
        final double expected = 123.45;
        mEditNumber.setNumber(expected);
        final double actual =
             mEditNumber.getNumber();
        assertEquals(expected, actual);
    }

}
