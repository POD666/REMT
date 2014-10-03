package com.v2.remt.engine.Style;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
                //to create your own font
public class FontCreator
{
	private static AssetManager mgr;
	
	public static void SetAssetManager(Activity activity)
	{
		mgr = activity.getAssets();
	}
	
	public static void SetTextStyle(View v, String fontname)
	{
		fontname = "Bookman_Old_Style.ttf";
		if(!fontname.contains("."))
		{
			fontname += ".ttf";
		}
		
		Typeface tf = Typeface.createFromAsset(mgr, fontname);
		TextView tv = (TextView)v;
		tv.setTypeface(tf);
	}
}
