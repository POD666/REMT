package com.v2.remt.engine.PageItems.Control;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import com.v2.remt.engine.JSONgetter;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlSimpleInputBase;
import com.v2.remt.engine.PageItems.TextChangedListner;
import org.json.JSONObject;

public class MyString extends ControlSimpleInputBase
{
    TextChangedListner ChangeListner;

    public MyString(Context context, JSONObject content)
    {
        super(context, content);

        EditText view = new EditText(context);

        if(IsReadOnly) view.setKeyListener(null);
        if(JSONgetter.GetBool(content, "IsPassword"))
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        view.setText(DefaultValue, TextView.BufferType.EDITABLE);

        view.setMaxLines(JSONgetter.GetInt(content, "NumberOfLines"));
        int maxLenght = JSONgetter.GetInt(content, "LengthMax");
        int minLenght = JSONgetter.GetInt(content, "LengthMin");

        ChangeListner = new TextChangedListner(this, TextChangedListner.CheckTypeText);
        if(maxLenght != 0)
        {
            view.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(maxLenght) });
            ChangeListner.SetParams(new Object[]{ maxLenght, minLenght });
        }
        else
        {
            ChangeListner.SetParams(new Object[]{ minLenght });
        }
        view.addTextChangedListener(ChangeListner);

        SetMainChild(view);
    }

    @Override
    public boolean HaveValue()
    {
        if(IsMandatory)
            return ChangeListner.Correct();
        else
            return true;
    }



}
