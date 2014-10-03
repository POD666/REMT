package com.v2.remt.engine;

import android.app.Activity;
import android.content.SharedPreferences;
import com.v2.remt.engine.Style.FontCreator;

//this class created to work with local storage

public class LocalDataStorage
{
	private static LocalDataStorage localDataStorage;
	private SharedPreferences sharedPreferences;
	
	private LocalDataStorage(Activity source)
	{
		sharedPreferences = source.getSharedPreferences("data", 0);
		FontCreator.SetAssetManager(source);
	}
	
	public static LocalDataStorage GetLocalDataStorage(Activity source)  //singlton
	{
		if(localDataStorage == null)
			localDataStorage = new LocalDataStorage(source);
		
		return localDataStorage;
	}
	
	public String GetString(String name)
	{
		return sharedPreferences.getString(name, null);
	}

	public void SetString(String name, String value)
	{
		sharedPreferences.edit().putString(name, value).commit();
	}

	public String[] GetStringArray(String name)
	{
		String str = sharedPreferences.getString(name, null);
		if(str == null || str == "")
			return null;
		
		return str.split(";");
	}

	public void SetStringArray(String name, String[] value)
	{
		if(value.length > 0)
		{
			String tmp = value[0];
			for(int i = 1; i < value.length; i++)
				tmp += ";" + value[i];
			sharedPreferences.edit().putString(name, tmp).commit();
		}
		else
		{
			sharedPreferences.edit().putString(name, "").commit();
		}
		
	}

}
