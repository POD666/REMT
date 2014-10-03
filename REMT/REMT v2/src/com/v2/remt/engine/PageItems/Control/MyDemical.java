package com.v2.remt.engine.PageItems.Control;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import com.v2.remt.engine.JSONgetter;
import com.v2.remt.engine.PageItems.CheckLogic.DecimalDigitsInputFilter;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlSimpleInputBase;
import com.v2.remt.engine.PageItems.TextChangedListner;
import org.json.JSONObject;

public class MyDemical extends ControlSimpleInputBase
{
    public MyDemical(Context context, JSONObject content)
    {
        super(context, content);

        EditText view = new EditText(context);
        view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        int DigitsBeforeSeparator = JSONgetter.GetInt(content, "DigitsBeforeSeparator");
        int DigitsAfterSeparator = JSONgetter.GetInt(content, "DigitsAfterSeparator");

        Double ValueMax = JSONgetter.GetDouble(content, "ValueMax");
        Double ValueMin = JSONgetter.GetDouble(content, "ValueMin");

        view.setFilters(new InputFilter[] { new DecimalDigitsInputFilter(DigitsBeforeSeparator, DigitsAfterSeparator) });

        if(IsReadOnly) view.setKeyListener(null);

        view.setText(DefaultValue, TextView.BufferType.EDITABLE);


        TextChangedListner ChangeListner = new TextChangedListner(this, TextChangedListner.CheckTypeDemical);
        ChangeListner.SetParams(new Object[]{ DigitsBeforeSeparator, DigitsAfterSeparator, ValueMax, ValueMin });
        view.addTextChangedListener(ChangeListner);

        SetMainChild(view);
    }

}
