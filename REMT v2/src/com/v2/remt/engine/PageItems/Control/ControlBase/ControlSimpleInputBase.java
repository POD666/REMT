package com.v2.remt.engine.PageItems.Control.ControlBase;

import android.content.Context;
import com.v2.remt.engine.JSONgetter;
import org.json.JSONObject;

public class ControlSimpleInputBase extends ControlSimpleBase
{
    protected String DefaultValue;
    protected boolean IsReadOnly;

    public ControlSimpleInputBase(Context context, JSONObject content)
    {
        super(context, content);

        if(content.isNull("DefaultValue"))
            DefaultValue = "";
        else
            DefaultValue = JSONgetter.GetString(content, "DefaultValue");

        IsReadOnly = JSONgetter.GetBool(content, "IsReadOnly");
    }
}
