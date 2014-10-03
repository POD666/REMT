package com.v2.remt.engine.PageItems.Control;

import android.content.Context;
import android.widget.TextView;
import com.v2.remt.engine.JSONgetter;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlBase;
import org.json.JSONObject;

public class MyLabel extends ControlBase
{
    public MyLabel(Context context, JSONObject content)
    {
        super(context, content);

        TextView view = new TextView(context);
        view.setText(JSONgetter.GetString(content, "Value"));

        SetMainChild(view);
    }
}
