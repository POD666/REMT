package com.v2.remt.engine.Style;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//for server spinner
public class ListAdapter extends BaseAdapter 
{

	private LayoutInflater mInflater;
	String[] array;
	
    public ListAdapter(Context context, String[] arr) {
        mInflater = LayoutInflater.from(context);
        array = arr;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) 
        {
            v = mInflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        tv.setText(array[position]);
        FontCreator.SetTextStyle(tv, null);
        tv.setGravity(Gravity.CENTER);
        

        return v;
    }

	@Override
	public int getCount()
	{
		return array.length;
	}

	@Override
	public Object getItem(int arg0)
	{
		return array[arg0];
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}
}
