package com.v2.remt.engine.PageItems.Control;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.*;
import com.v2.remt.engine.JSONgetter;
import com.v2.remt.engine.PageItems.Control.BaseSubclasses.MyDatePickerDialog;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlSimpleInputBase;
import org.json.JSONObject;

public class MyDate  extends ControlSimpleInputBase implements View.OnClickListener, DatePickerDialog.OnDateSetListener
{
    TextView label;

    String MinDate;
    String MaxDate;

    public MyDate(Context context, JSONObject content)
    {
        super(context, content);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        label = new TextView(context);
        label.setText(DefaultValue);

        Button btn = new Button(context);
        btn.setText("->.<-");
        if(!IsReadOnly)
            btn.setOnClickListener(this);

        layout.addView(label);
        layout.addView(btn);

        MinDate = JSONgetter.GetString(content, "DateMin");
        MaxDate = JSONgetter.GetString(content, "DateMax");


        SetMainChild(layout);
    }

    @Override
    public boolean HaveValue()
    {
            return true;
    }

    private int GetDateValue(int i)
    {
        String[] answer = label.getText().toString().split("\\.", 3);
        return Integer.parseInt(answer[i]);
    }

    @Override
    public void onClick(View view)
    {
        MyDatePickerDialog d = new MyDatePickerDialog(view.getContext(), this, GetDateValue(2), GetDateValue(1), GetDateValue(0));
        String tmp = label.getText().toString();
        label.setText(MaxDate);
        d.SetMaxDate(GetDateValue(2), GetDateValue(1), GetDateValue(0));
        label.setText(MinDate);
        d.SetMinDate(GetDateValue(2), GetDateValue(1), GetDateValue(0));
        label.setText(tmp);
        d.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day)
    {
        String sday = String.valueOf(day);
        sday = (sday.length() == 1)?"0"+sday:sday;
        String smonth = String.valueOf(month);
        smonth = (smonth.length() == 1)?"0"+smonth:smonth;

        label.setText( sday + "." + smonth + "." + String.valueOf(year));
    }
}
