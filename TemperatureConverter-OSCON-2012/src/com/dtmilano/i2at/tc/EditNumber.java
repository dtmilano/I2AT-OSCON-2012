/**
 * 
 */

package com.dtmilano.i2at.tc;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @author diego
 */
public class EditNumber extends EditText {

    private static final int DEFAULT_DECIMAL_PLACES = 0;
    private int mDecimalPlaces;

    /**
     * @param context
     */
    public EditNumber(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, -1);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public EditNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = 
                context.obtainStyledAttributes(attrs, R.styleable.i2at);
            setDecimalPlaces(a.getInteger(
                 R.styleable.i2at_decimalPlaces, DEFAULT_DECIMAL_PLACES));
            a.recycle();

    }

    public void clear() {
        setText(null);
    }

    public void setNumber(double d) {
        final String str = 
                String.format("%." + mDecimalPlaces + "f", d);
        setText(str);
    }

    public double getNumber() {
        final String s = getText().toString();
        if ("".equals(s)) {
            return Double.NaN;
        }
        return Double.valueOf(s);
    }

    public void setDecimalPlaces(int places) {
        mDecimalPlaces = places;
    }
        
    public int getDecimalPlaces() {
        return mDecimalPlaces;
    }
}
