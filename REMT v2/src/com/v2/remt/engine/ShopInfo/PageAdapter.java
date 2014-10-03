package com.v2.remt.engine.ShopInfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.v2.remt.engine.PageInfo.PageInfo;

public class PageAdapter extends FragmentPagerAdapter
{
	private static PageInfo[] CONTENT;
	
	public PageAdapter(FragmentManager fm, PageInfo[] content)
	{
		super(fm);
		CONTENT = content;
	}

	@Override
	public Fragment getItem(int position)
	{
		return CONTENT[position % CONTENT.length];
	}

	@Override
	public String getPageTitle(int position)
	{
		return CONTENT[position % CONTENT.length].Title;
	}

	@Override
	public int getCount()
	{
		return CONTENT.length;
	}
}