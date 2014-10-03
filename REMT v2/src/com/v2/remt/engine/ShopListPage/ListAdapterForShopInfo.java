package com.v2.remt.engine.ShopListPage;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.v2.remt.engine.ShopInfo.ShopInfo;
import com.v2.remt.engine.Style.FontCreator;

import java.util.List;

//for server spinner
public class ListAdapterForShopInfo extends BaseAdapter
{

	private LayoutInflater mInflater;
	List<ShopInfo> array;
	
    public ListAdapterForShopInfo(Context context, List<ShopInfo> ShopList) {
        mInflater = LayoutInflater.from(context);
        array = ShopList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) 
        {
            v = mInflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        
        TextView tv = (TextView) v.findViewById(android.R.id.text1);
        tv.setText(array.get(position).toString());
        FontCreator.SetTextStyle(tv, null);
        tv.setGravity(Gravity.CENTER);
        
        return v;
    }

	@Override
	public int getCount()
	{
		return array.size();
	}

	@Override
	public Object getItem(int arg0)
	{
		return array.get(arg0);
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}
}
