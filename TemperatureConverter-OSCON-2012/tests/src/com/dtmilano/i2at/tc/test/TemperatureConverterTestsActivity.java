/**
 * 
 */

package com.dtmilano.i2at.tc.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.InstrumentationInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author diego
 */
public class TemperatureConverterTestsActivity extends Activity {
    private static final String TAG =
            "TemperatureConverterTestsActivity";

    private static final int TIMEOUT = 15000;

    private TextView mResults;
    private LogcatAsyncTask mLogcat;

    private class LogcatAsyncTask extends AsyncTask<Integer, String, Void> {
        // TestRunner and silence others
        private static final String CMD = "logcat -v time TestRunner:I *:S";
        private BufferedReader mReader;
        private Process mProc;

        public LogcatAsyncTask() {
            try {
                mProc = Runtime.getRuntime().exec(CMD);
                mReader = new BufferedReader(new InputStreamReader(
                        mProc.getInputStream()));
            } catch (Exception e) {
                Log.e(TAG, "Creating proc", e);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if (!TextUtils.isEmpty(values[0])) {
                mResults.append("\n" + values[0]);
            }
        }

        @Override
        protected Void doInBackground(Integer... params) {
            final long timeout = System.currentTimeMillis() + params[0];
            try {
                do {
                    Thread.sleep(50);
                    publishProgress(mReader.readLine());
                } while (System.currentTimeMillis() < timeout);
            } catch (Exception e) {
                publishProgress("ERROR: " + e);
            } finally {
                publishProgress("END");
                mProc.destroy();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter_tests);
        mResults = (TextView) findViewById(R.id.results);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLogcat != null) {
            mLogcat.cancel(true);
        }
    }

    private InstrumentationInfo getInstrumentationInfo(final String packageName) {
        final List<InstrumentationInfo> list =
                getPackageManager()
                        .queryInstrumentation(packageName, 0);
        return (!list.isEmpty()) ? list.get(0) : null;
    }

    public void runTests(View v) {
        final String pn = getPackageName().replaceFirst(".test$", "");
        final InstrumentationInfo info = getInstrumentationInfo(pn);
        if (info != null) {
            final ComponentName cn = new ComponentName(info.packageName,
                    info.name);
            if (startInstrumentation(cn, null, null)) {
                mLogcat = new LogcatAsyncTask();
                mLogcat.execute(TIMEOUT);
            }
        } else {
            Toast.makeText(this,
                    "Cannot find instrumentation for " + pn, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
