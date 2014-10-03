package com.v2.remt.engine.PageItems;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.v2.remt.engine.PageItems.Control.ControlBase.ControlBase;
import com.v2.remt.engine.PageItems.Control.*;
import org.json.JSONException;
import org.json.JSONObject;

public class PageItem
{
	JSONObject content;
	ControlBase item;

    public boolean HaveValue()
    {
        if(item == null)
            return true;
        else
            return item.HaveValue();
    }

	public PageItem(JSONObject info)
	{
		content = info;
	}
	
	private void Constructor(Context context) throws JSONException
	{
		switch(content.getInt("Type"))
		{
		case 1: item = new MyString(context, content);  break;						//string
		case 2:	item = new MyDemical(context, content);	break;						//demical
		case 3:	item = new MyBool(context, content);    break;						//bool
		case 4:	item = new MyDate(context, content);    break;                      //date
		case 5:			break;
		case 6:			break;
		case 7:			break;
		case 8:			break;
		case 9:			break;
		case 10:		break;
		case 11: item = new MyLabel(context, content);	break;						//label
		}
	}
	
	public View getView(Context context)
	{	
		try
		{
			Constructor(context);
		}
        catch (JSONException e)
		{
			e.printStackTrace();
		}
		if(item == null)
		{
			TextView text = new TextView(context);
			text.setText(content.toString());
			return text;
		}
		return item;
	}
	
	public String toString()
	{
		return content.toString();
	}

	/*
	private View CreateType1_String(Context context) throws JSONException
	{
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		EditText i = new EditText(context);
		if(content.getBoolean("IsPassword"))
			i.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		
		i.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(10) });
		i.setMaxLines(content.getInt("NumberOfLines"));
		if(content.getBoolean("IsReadOnly"))
			i.setKeyListener(null);
		if(!content.isNull("DefaultValue"))
			i.setText(content.getString("DefaultValue"), TextView.BufferType.EDITABLE);
		Id = content.getString("Id");
		IsMandatory = content.getBoolean("IsMandatory");
		
		
		TextChangedListner ChangeListner = new TextChangedListner(layout, TextChangedListner.CheckTypeText);
		ChangeListner.SetParams(new Object[]{ 10 });
		i.addTextChangedListener(ChangeListner);
		
		TextView title = new TextView(context);
		title.setText(content.getString("Title"));		
		
		layout.addView(title);
		layout.addView(i);		
		
		return layout;
	}
	
	private View CreateType2_Demical(Context context) throws JSONException
	{
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		EditText i = new EditText(context);
		i.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		i.setFilters(new InputFilter[] { new DecimalDigitsInputFilter(content.getInt("DigitsBeforeSeparator"), content.getInt("DigitsAfterSeparator")) });
		
		if(content.getBoolean("IsReadOnly"))
			i.setKeyListener(null);
		if(!content.isNull("DefaultValue"))
			i.setText(content.getString("DefaultValue"), TextView.BufferType.EDITABLE);
		Id = content.getString("Id");
		IsMandatory = content.getBoolean("IsMandatory");

        TextChangedListner ChangeListner = new TextChangedListner(layout, TextChangedListner.CheckTypeText);
        ChangeListner.SetParams(new Object[]{ 10 });
        i.addTextChangedListener(ChangeListner);

		TextView title = new TextView(context);
		title.setText(content.getString("Title"));

		layout.addView(title);
		layout.addView(i);		
		
		return layout;
	}
	
	private View new MyCreateType3_Bool(Context context) throws JSONException
	{
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        CheckBox i = new CheckBox(context);

        if(content.getBoolean("IsReadOnly"))
            i.setKeyListener(null);

        if(!content.isNull("DefaultValue"))
            i.setChecked(content.getBoolean("DefaultValue"));

        //CheckCheckBoxLogic ChangeListner = new CheckCheckBoxLogic();
        //ChangeListner.SetParams(new Object[]{ });
        //i.setOnCheckedChangeListener(ChangeListner);

        Id = content.getString("Id");
        IsMandatory = false;//content.getBoolean("IsMandatory");

        TextView title = new TextView(context);
        title.setText(content.getString("Title"));

        layout.addView(title);
        layout.addView(i);

		return layout;
	}
	
	private View CreateType4_Date(Context context) throws JSONException
	{
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        CheckBox i = new CheckBox(context);

        if(content.getBoolean("IsReadOnly"))
            i.setKeyListener(null);

        if(!content.isNull("DefaultValue"))
            i.setChecked(content.getBoolean("DefaultValue"));

        //CheckCheckBoxLogic ChangeListner = new CheckCheckBoxLogic();
        //ChangeListner.SetParams(new Object[]{ });
        //i.setOnCheckedChangeListener(ChangeListner);

        Id = content.getString("Id");
        IsMandatory = false;//content.getBoolean("IsMandatory");

        TextView title = new TextView(context);
        title.setText(content.getString("Title"));

        layout.addView(title);
        layout.addView(i);

        return layout;
	}
	private View CreateType11_TextView(Context context) throws JSONException
	{
		TextView i = new TextView(context);
		i.setText(content.getString("Value"));
        IsMandatory = false;
		return i;
	}
	*/
}
