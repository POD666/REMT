package com.v2.remt.engine.PageItems.Control.ControlBase;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.v2.remt.engine.JSONgetter;
import org.json.JSONObject;

public class ControlBase extends LinearLayout
{
    protected View MainChild;
    protected int type;

    public ControlBase(Context context, JSONObject content)
    {
        super(context);
        setOrientation(LinearLayout.VERTICAL);

        type = JSONgetter.GetInt(content, "Type");
    }

    public boolean HaveValue()
    {
        return true;
    }

    protected void SetMainChild(View view)
    {
        MainChild = view;
        this.addView(MainChild);
    }

    protected View GetMainChild()
    {
        return MainChild;
    }
}
