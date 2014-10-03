package com.v2.remt.engine.ShopInfo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import com.v2.remt.R;
import com.v2.remt.engine.NetWorker;
import com.v2.remt.engine.PageInfo.PageInfo;
import com.v2.remt.engine.Static;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

public class ShopInfo extends FragmentActivity implements ViewPager.OnPageChangeListener
{
	private ProgressDialog mProgressDialog;
	public static final int DIALOG_DOWNLOAD_JSON_PROGRESS = 0;
	
	List<PageInfo> Pages;
	String Id;
	String Title;

    NonSwipeableViewPager pager;
    private static PageInfo Current;

	public void SetShopInfo(String id, String title, String pages)
	{
		Id=id;
		Title=title;
		//Pages=pages;
	}
	
	public StaticShopInfo GetStaticShopInfo()
	{
		return new StaticShopInfo(Id, Title, Pages);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tabs);
		SetShopInfo(Static.staticShopInfo.Id, Static.staticShopInfo.Title, " ololo "/*Static.staticShopInfo.Pages*/);
		
		showDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
		
		Pages = NetWorker.GetPagesList(false);
		
		PageInfo[] array = new PageInfo[Pages.size()];
        Current = Pages.get(0);
        FragmentPagerAdapter adapter = new PageAdapter(getSupportFragmentManager(), Pages.toArray(array));

        pager = (NonSwipeableViewPager)findViewById(R.id.pager);
		pager.setAdapter(adapter);
        pager.setOnPageChangeListener(this);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(this);

		dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
		removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
	}

	@Override
	public String toString()
	{
		return Title;
	}

	protected Dialog onCreateDialog(int id)
	{
		switch (id)
		{
		case DIALOG_DOWNLOAD_JSON_PROGRESS:
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setMessage("Downloading.....");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}

    private boolean CheckFor_MandatoryItems(PageInfo trg)
    {
        return trg.CanLeave();
    }

    private boolean CheckIfPage_IsReEditable(PageInfo trg)
    {
        return true;
    }

    private boolean CheckIf_CanReturnOnThisPage(PageInfo trg)
    {
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) { }


    private boolean force = false;
    private void MsgShow(String str)
    {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int i)
    {
        if(force)
        {
            force = false;
            pager.setCurrentItem(pager.getAdapter().getItemPosition(Current));
        }
        else
        {
            if(!Current.CanLeave())
            {
                force = true;
                pager.setCurrentItem(pager.getAdapter().getItemPosition(Current) );
                MsgShow("Not all mandatory items are completed.");
            }
            else
            {
                if(Pages.get(i).CanReturnOnThisPage())
                {
                    pager.setCurrentItem(i);
                    pager.getChildAt(i).setEnabled(Pages.get(i).isReEditable());
                    Current = Pages.get(i);
                }
                else
                {
                    force = true;
                    pager.setCurrentItem(pager.getAdapter().getItemPosition(Current));
                    MsgShow("You cannt return on this page.");
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) { }
}
