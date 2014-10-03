package com.v2.remt.engine.PageInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import com.v2.remt.engine.PageItems.PageItem;

import java.util.ArrayList;
import java.util.List;

public final class PageInfo extends Fragment
{	
	public String Title;
	public String Id;
	private boolean CanReturnOnThisPage;
    private static boolean firstTime = true;
    private boolean ReEditable;
	
	List<PageItem> Items;

    public boolean CanLeave()
    {
        for(int i = 0; i < Items.size(); i++)
        {
                if(!Items.get(i).HaveValue())
                {
                    Log.i("CanLeave()", "false" );
                    return false;
                }
        }
        Log.i("CanLeave()", "true" );
        return true;
    }

    public boolean CanReturnOnThisPage()
    {
        boolean answer = CanReturnOnThisPage || firstTime;
        firstTime = false;
        return answer;
    }

    public boolean isReEditable()
    {
        return ReEditable;
    }



	public void AddItem(PageItem item)
	{
		if(Items == null) Items = new ArrayList<PageItem>();
		Items.add(item);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}

	private void DoSom(LinearLayout layout)
	{
		if(Items != null)
		for(int i=0; i < Items.size(); i++)
		{
            try
            {
			    layout.addView(Items.get(i).getView(getActivity()));
            }
            catch(Exception e)
            {}
		}
		
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		///////////////////////////////////////////////////////////
		ScrollView scroll = new ScrollView(getActivity());
		LinearLayout content = new LinearLayout(getActivity());
		content.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		content.setOrientation(LinearLayout.VERTICAL);
		DoSom(content);
		scroll.addView(content);
		layout.addView(scroll);
		///////////////////////////////////////////////////////////
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		return layout;
	}


    public void setCanReturnOnThisPage(boolean canReturnOnThisPage)
    {
        CanReturnOnThisPage = canReturnOnThisPage;
    }

    public void setReEditable(boolean reEditable)
    {
        ReEditable = reEditable;
    }
}
