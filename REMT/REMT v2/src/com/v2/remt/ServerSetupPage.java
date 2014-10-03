package com.v2.remt;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.v2.remt.engine.LocalDataStorage;
import com.v2.remt.engine.Style.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerSetupPage extends Activity implements OnClickListener
{
	LocalDataStorage storage;
	String[] serverList;
	ListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serversetuppage);

		Initialization();
	}

	private void Initialization()
	{
		storage = LocalDataStorage.GetLocalDataStorage(this);
		serverList = storage.GetStringArray("server_list");
		// filling server list //////////////////////////////////////////////////////////////////////////
		ListView list = (ListView) findViewById(R.id.listView1);
		if (serverList != null)
		{
			adapter = new ListAdapter(this, serverList);
			list.setAdapter(adapter);
		} else
		{
			serverList = new String[] {};
		}
        //creating context menu
		list.setOnCreateContextMenuListener(new OnCreateContextMenuListener()
		{
			@Override
			public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenu.ContextMenuInfo menuInfo)
			{
				//AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
				
				contextMenu.setHeaderTitle(R.string.context_menu_caption);
				contextMenu.add(0, 0, 0, R.string.edit);
				contextMenu.add(0, 1, 1, R.string.delete);
			}

		});
		// //////////////////////////////////////////////////////////////////////////////////////////

		Button btn = (Button) findViewById(R.id.button1_newsrv);
		btn.setOnClickListener(this);

	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item)        //handeling context menu
	{
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		
		switch (item.getItemId())
		{
		case 0:
			ContextEdit(info.position);        //edit
			return (true);
		case 1:
			ContextDelete(info.position);      //delete
			return (true);
		}

		return (super.onOptionsItemSelected(item));
	}

	private void ContextEdit(int i)     //just putting servers url to editting box
	{
		EditText editText = (EditText) findViewById(R.id.editText1_newsrv);
		editText.setText(adapter.getItem(i).toString());
	}

	private void ContextDelete(int i)   //not optimazed algorithm of deleting
	{
		List<String> tmp_list;
		tmp_list = new ArrayList<String>(Arrays.asList(serverList));
		tmp_list.remove(adapter.getItem(i));

		serverList = tmp_list.toArray(new String[tmp_list.size()]);

		storage.SetStringArray("server_list", serverList);

		ListView list = (ListView) findViewById(R.id.listView1);
		adapter = new ListAdapter(this, serverList);
		adapter.notifyDataSetChanged();
		list.setAdapter(adapter);
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button1_newsrv:      //not optimazed algorithm of adding new server

			EditText editText = (EditText) findViewById(R.id.editText1_newsrv);
			String newstring = editText.getText().toString();
			editText.setText("");

			List<String> tmp_list;
			tmp_list = new ArrayList<String>(Arrays.asList(serverList));
			tmp_list.add(newstring);

			serverList = tmp_list.toArray(new String[tmp_list.size()]);

			storage.SetStringArray("server_list", serverList);

			AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
			dlgAlert.setMessage(String.valueOf(serverList.length));
			dlgAlert.setCancelable(true);
			dlgAlert.create().show();

			ListView list = (ListView) findViewById(R.id.listView1);
			adapter = new ListAdapter(this, serverList);
			adapter.notifyDataSetChanged();
			list.setAdapter(adapter);

			break;
		}
	}
}
