package com.example.donation_app;

import java.util.Locale;

import com.example.donation_app.R.string;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Details_disp extends Activity{
	SQLiteDatabase db;
	Cursor cur;
	ListView list;
	String[] array;
	int count=0;
	ArrayAdapter<String> det;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_details_disp);
	    list = (ListView)findViewById(R.id.listView2);
	    db = openOrCreateDatabase("UserDetails.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
	    String a="01";
	    String q1="select * from AmountDetails_Table1 where person_id ='"+a+"'";
		cur = db.rawQuery(q1,null );
	    cur.moveToFirst();
	    
	    while(!cur.isAfterLast()){
	    	count++;
	    	cur.moveToNext();
	    }
	    cur.moveToFirst();
	    array = new String[count];
	    int i = 0;
	    while(!cur.isAfterLast()){
	    	 String n = cur.getString(1).toString();
	    	String p = cur.getString(2).toString();
	    	array[i] = n+"    "+p;
	    	i++;
	    	cur.moveToNext();
	    }
	    det = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
	    det.notifyDataSetChanged();
	    list.setAdapter(det);
		db.close();
	}

}
