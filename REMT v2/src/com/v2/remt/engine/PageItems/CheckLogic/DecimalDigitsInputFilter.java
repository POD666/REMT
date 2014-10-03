package com.v2.remt.engine.PageItems.CheckLogic;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter
{

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero)
    {
        if(digitsAfterZero == 0)
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero) + "}");
        else
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero) + "}+((\\.[0-9]{0," + (digitsAfterZero) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
    {
        String str = dest.toString() + source.toString();
        Log.i("DecimalDigitsInputFilter", str);
        Matcher matcher = mPattern.matcher(str);
        if(!matcher.matches())
            return "";
        return null;
    }

}