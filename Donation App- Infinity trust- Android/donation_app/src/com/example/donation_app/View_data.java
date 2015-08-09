package com.example.donation_app;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class View_data extends Activity{
	int count;
	SQLiteDatabase db;
	Cursor cur;
	ListView list;
	String[] array;
	ArrayAdapter<String> det;
	public String n;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view);
    
    list = (ListView)findViewById(R.id.View1);
    db = openOrCreateDatabase("UserDetails.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
    db.setVersion(1);
    db.setLocale(Locale.getDefault());
    String str = "select * from UserDetails_Table1";
    cur = db.rawQuery(str, null);
    cur.moveToFirst();
    
    while(!cur.isAfterLast()){
    	count++;
    	cur.moveToNext();
    }
    cur.moveToFirst();
    array = new String[count];
    int i = 0;
    while(!cur.isAfterLast()){
    	 n = cur.getString(5).toString();
    	String p = cur.getString(0).toString();
    	array[i] = n+"  :  "+p;
    	i++;
    	cur.moveToNext();
    }
    det = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, array);
    det.notifyDataSetChanged();// android.R.layout.simple_list_item_1
    list.setAdapter(det);
    db.close();
    
    list.setOnItemClickListener(new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			startActivity(new Intent(View_data.this,Details_disp.class));
			String s = list.getItemAtPosition(arg2).toString();
			String[] spl=s.split("  : ");
			System.out.println(spl[0]);
			
		}   	
    });
    }
}