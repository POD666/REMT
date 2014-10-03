package com.v2.remt.engine.PageItems.Control;

import android.content.Context;
import android.widget.CheckBox;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlSimpleInputBase;
import org.json.JSONObject;

public class MyBool extends ControlSimpleInputBase
{
    public MyBool(Context context, JSONObject content)
    {
        super(context, content);

        CheckBox view = new CheckBox(context);

        if(IsReadOnly) view.setKeyListener(null);

        view.setChecked(DefaultValue.equals("true"));

        SetMainChild(view);
    }

    @Override
    public boolean HaveValue()
    {
        if(IsMandatory)
            return ((CheckBox)GetMainChild()).isChecked();
        else
            return true;
    }
}
