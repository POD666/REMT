package com.v2.remt.engine.PageItems.CheckLogic.Children;

import com.v2.remt.engine.PageItems.CheckLogic.Outer;

public class CheckDecimalLogic extends Outer
{
	int leftLenght;
	int rightLenght;
	
	int minValue;
	int maxValue;
	
	@Override
	public void SetParams(Object[] params)
	{
		if(params.length == 4)
		{
			leftLenght = Integer.parseInt(params[0].toString());
			rightLenght = Integer.parseInt(params[1].toString());
			
			maxValue = Integer.parseInt(params[2].toString());
			minValue = Integer.parseInt(params[3].toString());
		}		
	}

	@Override
	public void DoCheck(String str)
	{
		String[] splitted;
		
		try
		{
			Double d = Double.valueOf(str);
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
		
		if(str.contains("."))
		{
			splitted = str.split(".");
			if(str.lastIndexOf('.') != str.indexOf('.'))
			{
				IsCorrect = false;
				msg = "More than 1 point??";
				return;
			}
			
			int left = splitted[0].length();
			int right = splitted[1].length();
			
			if(left >= leftLenght || right >= rightLenght)
			{
				IsCorrect = false;
				msg = "Is it have less than "+leftLenght+" digits before point and less than "+rightLenght+"after point?";
				return;
			}			
		}
		else
		{
			if(str.length() >= leftLenght)
			{
				IsCorrect = false;
				msg = "Is it have less than "+leftLenght+" digits before point?";
				return;
			}
		}
		IsCorrect = true;
	}

}

