package com.example.donation_app;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class GetAmount extends Activity implements OnClickListener{

	SQLiteDatabase db;
	Cursor cur,cur1;
	EditText gid,gdate,gamount;
	String id,dat,amount,phone;
	String[] array;
	CheckBox check;
	int count = 0;
	Button submit;
	SmsManager smsManager;
	public void sendSMS() {
		smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phone, null, "Thank you for your donation!!", null, null);
	}
protected void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_getamount);
    gid = (EditText)findViewById(R.id.gid);
    gdate = (EditText)findViewById(R.id.gdate);
    gamount = (EditText)findViewById(R.id.gamount);
    check = (CheckBox)findViewById(R.id.gcheckBox);
    submit = (Button)findViewById(R.id.gsubmit);
    db = openOrCreateDatabase("UserDetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
    db.setVersion(1);
	db.setLocale(Locale.getDefault());
	String create = "Create table if not exists AmountDetails_Table1(person_id text,date text,amount text,FOREIGN KEY(person_id) REFERENCES UserDetails_Table1(person_id))";
	db.execSQL(create);
	db.execSQL("PRAGMA foreign_keys = ON;");
	check.setOnClickListener(this);
	submit.setOnClickListener(this);        
}

@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.gsubmit:
		if(check.isChecked()){
		id = gid.getText().toString();
		dat = gdate.getText().toString();
		amount = gamount.getText().toString();
		ContentValues content1 = new ContentValues();
		content1.put("person_id",id);
		content1.put("date",dat);
		content1.put("amount",amount);
		db.insert("AmountDetails_Table1", null, content1);
		String send = "select * from UserDetails_Table1 where person_id = '"+id+"'";
		cur = db.rawQuery(send, null);
		cur.moveToFirst();
	    
	    while(!cur.isAfterLast()){
	    	count++;
	    	cur.moveToNext();
	    }
	    cur.moveToFirst();
	    while(!cur.isAfterLast()){
	    	 phone= cur.getString(1).toString();
	    	cur.moveToNext();
	    }
	    //System.out.println(phone);
		sendSMS();
		finish();
		startActivity(new Intent(GetAmount.this,Existing_user.class));
		db.close();
		}
		else{
			Toast.makeText(this, "Plese confirm the amount by checking the check box", Toast.LENGTH_SHORT).show();
		}
		break;
	}
}
}
