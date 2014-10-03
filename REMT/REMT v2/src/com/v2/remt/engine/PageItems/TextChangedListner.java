package com.v2.remt.engine.PageItems;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.v2.remt.engine.PageItems.CheckLogic.Children.CheckDecimalLogic;
import com.v2.remt.engine.PageItems.CheckLogic.Children.CheckTextLogic;
import com.v2.remt.engine.PageItems.CheckLogic.Outer;

public class TextChangedListner implements TextWatcher
{
	public static final int CheckTypeText = 1;
	public static final int CheckTypeDemical = 2;
	public static final int CheckTypeNumber = 3;

	private Outer Checker;
	View Owner;

    private boolean correct;

	public TextChangedListner(View owner, int type)
	{
		Owner = owner;
        correct = false;
		switch(type)
		{
		case CheckTypeText:
			Checker = new CheckTextLogic();
			break;
		case CheckTypeDemical:
			Checker = new CheckDecimalLogic();
			break;
		case CheckTypeNumber:
			Checker = new CheckTextLogic();
			break;
		}
	}
	
	public boolean Correct()
    {
        return correct;
    }


	public void SetParams(Object[] params)
	{
		Checker.SetParams(params);
	}
	
	public void afterTextChanged(Editable s)
	{
		Checker.DoCheck(s.toString());
        correct = Checker.ReturnMsg(Owner);
	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	}

	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
	}
}






























