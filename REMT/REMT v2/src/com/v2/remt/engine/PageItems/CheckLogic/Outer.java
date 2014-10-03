package com.v2.remt.engine.PageItems.CheckLogic;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Outer implements CheckTypeLogic
{
	protected boolean IsCorrect;
	protected String msg; 
	protected TextView text;

	public boolean ReturnMsg(View owner)
	{
		if(IsCorrect)
		{
            owner.setBackgroundColor(Color.WHITE);
			if(text != null)
			{
				((LinearLayout)owner).removeView(text);
				text = null;
			}
            return true;
		}
		else
		{
			owner.setBackgroundColor(Color.RED);
			if(text != null)
				((LinearLayout)owner).removeView(text);
			text = new TextView(owner.getContext());
			text.setText("*" + msg);
			((LinearLayout)owner).addView(text);
            return false;
		}
	}
	
	@Override
	public void SetParams(Object[] params)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void DoCheck(String str)
	{
		// TODO Auto-generated method stub
		
	}
}

