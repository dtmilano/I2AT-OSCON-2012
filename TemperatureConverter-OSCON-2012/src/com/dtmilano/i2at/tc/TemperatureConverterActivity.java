
package com.dtmilano.i2at.tc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

public class TemperatureConverterActivity extends Activity {

    /**
     * @author diego
     */
    public abstract class TemperatureChangeWatcher implements TextWatcher {
        private EditNumber mSource;
        private EditNumber mDest;

        /**
         * @param mSource
         * @param mDest
         */
        public TemperatureChangeWatcher(EditNumber mSource, EditNumber mDest) {
            super();
            this.mSource = mSource;
            this.mDest = mDest;
        }

        /*
         * (non-Javadoc)
         * @see android.text.TextWatcher#afterTextChanged(android.text.Editable)
         */
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see
         * android.text.TextWatcher#beforeTextChanged(java.lang.CharSequence,
         * int, int, int)
         */
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * @see android.text.TextWatcher#onTextChanged(java.lang.CharSequence,
         * int, int, int)
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!mDest.hasWindowFocus() || mDest.hasFocus() || s == null)
                return;
            final String str = s.toString();
            if ("".equals(str)) {
                mDest.setText("");
                return;
            }
            try {
                final double result = convert(Double.parseDouble(str));
                mDest.setNumber(result);
            } catch (NumberFormatException e) {
                // WARNING: this is thrown while a number is entered, for
                // example just a '-'
            } catch (Exception e) {
                mSource.setError("ERROR: " + e.getLocalizedMessage());
            }
        }

        protected abstract double convert(double parseDouble);

    }

    private EditNumber mCelsius;
    private EditNumber mFahrenheit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        mCelsius =
                (EditNumber) findViewById(R.id.celsius);
        mFahrenheit =
                (EditNumber) findViewById(R.id.fahrenheit);

        mCelsius.addTextChangedListener(
                new TemperatureChangeWatcher(mCelsius, mFahrenheit) {
                    @Override
                    protected double convert(double temp) {
                        return TemperatureConverter.celsiusToFahrenheit(temp);
                    }
                });
        mFahrenheit.addTextChangedListener(
                new TemperatureChangeWatcher(mFahrenheit, mCelsius) {
                    @Override
                    protected double convert(double temp) {
                        return TemperatureConverter.fahrenheitToCelsius(temp);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_temperature_converter, menu);
        return true;
    }

    /**
     * Handles settings menu item
     * @param item the item
     * @return true
     */
    public boolean settings(MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
        return true;
    }
}
