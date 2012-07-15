/**
 * 
 */

package com.dtmilano.i2at.tc.test;

import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.IntentFilter;
import android.test.InstrumentationTestCase;
import android.view.KeyEvent;

import com.dtmilano.i2at.tc.SettingsActivity;
import com.dtmilano.i2at.tc.TemperatureConverterActivity;

/**
 * @author diego
 */
public class InstrumentationTests extends InstrumentationTestCase {

    public void testSettings() {
        Instrumentation inst = getInstrumentation();
        // launch main Activity
        assertNotNull(launchActivity(inst.getTargetContext()
                .getPackageName(),
                TemperatureConverterActivity.class, null));
        // set monitor
        ActivityMonitor monitor = new ActivityMonitor((IntentFilter) null, null, false);
        inst.addMonitor(monitor);
        // launch settings
        sendKeys(KeyEvent.KEYCODE_MENU);
        sendKeys(KeyEvent.KEYCODE_ENTER);
        // check
        assertTrue(inst.waitForMonitorWithTimeout(monitor, 5) instanceof SettingsActivity);
        assertEquals(1, monitor.getHits());
    }
}
