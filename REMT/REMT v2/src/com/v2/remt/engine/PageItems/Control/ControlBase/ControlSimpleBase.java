package com.v2.remt.engine.PageItems.Control.ControlBase;

import android.content.Context;
import android.widget.TextView;
import com.v2.remt.engine.JSONgetter;
import org.json.JSONObject;

public class ControlSimpleBase extends ControlBase
{
    protected String Id;
    protected String Title;
    protected boolean IsMandatory;

    public ControlSimpleBase(Context context, JSONObject content)
    {
        super(context, content);

        Id = JSONgetter.GetString(content, "Id");
        SetTitle(context, content);
        IsMandatory = JSONgetter.GetBool(content, "IsMandatory");
    }

    private void SetTitle(Context context, JSONObject content)
    {
        TextView title = new TextView(context);
        title.setText(JSONgetter.GetString(content, "Title"));
        this.addView(title);
    }
}
