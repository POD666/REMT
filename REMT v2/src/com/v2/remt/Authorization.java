package com.v2.remt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import com.v2.remt.engine.LocalDataStorage;
import com.v2.remt.engine.ShopListPage.ShopListPage;
import com.v2.remt.engine.Style.ListAdapter;

public class Authorization extends Activity implements OnClickListener
{
	String str_login;
	String str_password;

	String str_server;      //url

	String str_token;       //connection token
	LocalDataStorage storage;
	ListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authorization);
		Initialize();
	}

	private void Initialize()
	{
		storage = LocalDataStorage.GetLocalDataStorage(this);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);   //combobox
		String[] array = storage.GetStringArray("server_list");    //getting servers list from data storage
		if(array == null)                                          //it can be first running of programm so server list can be null
			adapter = new ListAdapter(this, new String[]{"(null)"});
		else
			adapter = new ListAdapter(this, array);

		spinner.setAdapter(adapter);
		// ...
		Button btn = (Button) findViewById(R.id.button1_enter);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);      //showing menu with servers list editting tools
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.menu_settings:    //going to settings
			Intent i = new Intent(this, ServerSetupPage.class);
			startActivity(i);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button1_enter:
            //to do
            //sending login&&pass to server
            //getting token

			Intent i = new Intent(this, ShopListPage.class); //going to tasks list
			startActivity(i);
			break;
		}
	}
}
