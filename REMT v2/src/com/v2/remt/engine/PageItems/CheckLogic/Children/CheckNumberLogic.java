package com.v2.remt.engine.PageItems.CheckLogic.Children;

import com.v2.remt.engine.PageItems.CheckLogic.Outer;

public class CheckNumberLogic extends Outer
{
	int Lenght;
	
	int minValue;
	int maxValue;

	@Override
	public void SetParams(Object[] params)
	{
		if(params.length == 4)
		{
			Lenght = Integer.parseInt(params[0].toString());
			
			maxValue = Integer.parseInt(params[1].toString());
			minValue = Integer.parseInt(params[2].toString());
		}		
	}

	@Override
	public void DoCheck(String str)
	{
		String[] splitted;
		
		try
		{
			int d = Integer.valueOf(str);
			if(minValue <= d || d >= maxValue)
			{
				IsCorrect = false;
				msg = "Is it less than "+maxValue+" and bigger than "+minValue+"?";
				return;
			}
		}
		catch(Exception e)
		{
			IsCorrect = false;
			msg = e.getMessage();
			return;
		}
		
		if(str.length() >= Lenght)
		{
			IsCorrect = false;
			msg = "Is it have less than "+Lenght+" digits?";
			return;
		}
		IsCorrect = true;
	}
}
