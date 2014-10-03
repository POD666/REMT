package com.v2.remt.engine.PageItems.CheckLogic.Children;

import com.v2.remt.engine.PageItems.CheckLogic.Outer;

public class CheckTextLogic extends Outer
{
	int minLenght;
	int maxLenght;
	
	public void SetParams(Object[] params)
	{
		if(params.length == 1)
		{
            minLenght = Integer.parseInt(params[0].toString());
            maxLenght = 0;
		}
		else if(params.length == 2)
		{
			maxLenght = Integer.parseInt(params[0].toString());
			minLenght = Integer.parseInt(params[1].toString());
		}
	}

	public void DoCheck(String str)
	{
		IsCorrect = false;

        if(maxLenght == 0)
        {
            if(str.length() >= minLenght)
                IsCorrect = true;
            else
            {
                msg = "Lenght of text must be more than " + minLenght + ".";
            }
        }
        else
        {
            if(str.length() >= minLenght && str.length() <= maxLenght)
		    	IsCorrect = true;
		    else
		    {
		    	msg = "Lenght of text must be between " + minLenght + " and " + maxLenght + ".";
		    }
        }
	}
}
