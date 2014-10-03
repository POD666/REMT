package com.v2.remt.engine;

import android.util.Log;
import com.v2.remt.engine.PageInfo.PageInfo;
import com.v2.remt.engine.PageItems.PageItem;
import com.v2.remt.engine.ShopInfo.ShopInfo;
import com.v2.remt.engine.ShopListPage.ShopListPage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//getting any data from NET
//TO DO here should be loading dialog


public class NetWorker
{
	
	static String[] LocalUrl = new String[]{ "ShopInfo", "PageInfo"  };
	static String[] WebUrl = new String[]{ "http://pod666.at.ua/forms-active.json", "http://pod666.at.ua/form-get-by-id.json"  };
	
	
	public static List<ShopInfo> GetShopList(boolean web)
	{
		List<ShopInfo> ShopList = new ArrayList<ShopInfo>();

		String jsonTXT = ReadJSON(0, web);
		try
		{
			JSONArray jsonArray = new JSONArray(jsonTXT);
			Log.i(ShopListPage.class.getName(), "Number of entries " + jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				ShopInfo shop = new ShopInfo();
				shop.SetShopInfo(jsonObject.getString("Id"), jsonObject.getString("Title"), jsonObject.getString("Pages"));
				ShopList.add(shop);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Log.i(ShopListPage.class.getName(), "end");
		return ShopList;
	}

	public static List<PageInfo> GetPagesList(boolean web)
	{
		List<PageInfo> PageList = new ArrayList<PageInfo>();
		String jsonTXT = ReadJSON(1, web);
		try
		{
			JSONArray jsonArray = (new JSONObject(jsonTXT)).getJSONArray("Pages");
/////////////////  setting item to the page  ////////////////////////////////////////////////////////////////////////////////////
			Log.i(ShopListPage.class.getName(), "Getting pages... Count: " + jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) // pages
			{
				Log.i(ShopListPage.class.getName(), "Page #" + String.valueOf(i + 1) + " in process");
				PageInfo page = new PageInfo();

				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isNull("Controls"))
				{
					JSONArray controls = jsonObject.getJSONArray("Controls");
					Log.i(ShopListPage.class.getName(), "Getting controls... Count: " + controls.length());
					for (int j = 0; j < controls.length(); j++) // controls
					{
						Log.i(ShopListPage.class.getName(), "Control #" + String.valueOf(j + 1) + " in process");
						page.AddItem(new PageItem(controls.getJSONObject(j)));
						Log.i(ShopListPage.class.getName(), "Control #" + String.valueOf(j + 1) + " is added");
					}
					Log.i(ShopListPage.class.getName(), "Controls ready");
				} else
				{
					Log.i(ShopListPage.class.getName(), "Controls are null");
				}
				
				page.Title = jsonObject.getString("Title");
				page.Id = jsonObject.getString("Id");
				page.setCanReturnOnThisPage(jsonObject.getBoolean("IsCanReturnOnThisPage"));
				page.setReEditable(jsonObject.getBoolean("IsReEditable"));
				
				PageList.add(page);
				Log.i(ShopListPage.class.getName(), "Page #" + String.valueOf(i + 1) + " is added");
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return PageList;
	}
	
	private static String GetJSON(String url)  //downloading json from url
	{
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try
		{
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200)
			{
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null)
				{
					builder.append(line);
				}
			} else
			{
				Log.e(ShopListPage.class.toString(), "Failed to download file");
			}
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	private static LocalDataStorage LocalData()
	{
		return LocalDataStorage.GetLocalDataStorage(null);
	}
	
	private static String GetLocal(String url)   //get json from local storage
	{
		return LocalData().GetString(url);
	}
	
	private static void SetLocal(String value, int type) //writting json after download to local storage
	{
		LocalData().SetString(LocalUrl[type], value);
	}
	
	public static String ReadJSON(int type, boolean web) //gettin json, deciding local or net
	{
		String json = GetLocal(LocalUrl[type]);
		if(web)
        {
            SetLocal("", 0);
            SetLocal("", 1);
            json = null;
        }
		
		if(json==null || json=="")
		{
			json = GetJSON(WebUrl[type]);
			Log.i(ShopListPage.class.getName(), "Loaded from web");
			SetLocal(json, type);			
			Log.i(ShopListPage.class.getName(), "Saved to local");
		}
		else
		{
			Log.i(ShopListPage.class.getName(), "Loaded from local");
		}
		
		return json;
	}
	
}
