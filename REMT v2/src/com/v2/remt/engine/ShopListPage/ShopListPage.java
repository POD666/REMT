package com.v2.remt.engine.ShopListPage;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.v2.remt.R;
import com.v2.remt.engine.LocalDataStorage;
import com.v2.remt.engine.NetWorker;
import com.v2.remt.engine.ShopInfo.ShopInfo;
import com.v2.remt.engine.Static;

import java.util.List;

public class ShopListPage extends Activity implements OnClickListener,
        OnItemClickListener
{
	private LocalDataStorage storage;
	private ProgressDialog mProgressDialog;
	public static final int DIALOG_DOWNLOAD_JSON_PROGRESS = 0;
	private List<ShopInfo> ShopList;
	private boolean web;
	private ListAdapterForShopInfo adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoplistpage);
		Initialize();
	}

	private void Initialize()
	{
		storage = LocalDataStorage.GetLocalDataStorage(this);
		Button btn;
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		btn = (Button) findViewById(R.id.button2);
		btn.setOnClickListener(this);

		web = false;
		new DownloadJSONFileAsync().execute();
	}

	private void Refresh()
	{
		Log.i("", "refresh");


		ShopList = NetWorker.GetShopList(web);
        web = false;
		adapter = new ListAdapterForShopInfo(this, ShopList);
		
	}
	
	private void ShowContent()
	{
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button1: // sendall

			break;
		case R.id.button2: // refresh
			web = true;
			new DownloadJSONFileAsync().execute();
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Static.staticShopInfo = ((ShopInfo) parent.getAdapter().getItem(position)).GetStaticShopInfo();
		Intent i = new Intent(this, ShopInfo.class);
		startActivity(i);

		String item = ((TextView) view).getText().toString();
		Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();

	}

	@Override
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

	class DownloadJSONFileAsync extends AsyncTask<String, Void, Void>
	{
		protected void onPreExecute()
		{
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
		}

		@Override
		protected Void doInBackground(String... params)
		{
			NetWorker.ReadJSON(0, web);		
			return null;
		}

		protected void onPostExecute(Void unused)
		{
			Refresh();
			ShowContent();
			dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
			removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
		}
	}
}
