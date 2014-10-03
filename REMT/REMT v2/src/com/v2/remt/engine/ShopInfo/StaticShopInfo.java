package com.v2.remt.engine.ShopInfo;

import com.v2.remt.engine.PageInfo.PageInfo;

import java.util.List;

public class StaticShopInfo
{
	List<PageInfo> Pages;
	String Id;
	String Title;
	
	public StaticShopInfo(String id, String title, List<PageInfo> pages)
	{
		Id=id;
		Title=title;
		Pages = pages;
	}
}
