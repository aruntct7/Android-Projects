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
import android.widget.EditText;

public class Newuser extends Activity implements OnClickListener{

	SQLiteDatabase db;
	Cursor cur;
	EditText getname,getphone,getblood,getcity,getdob,getid,getemail;
	String name,phone,blood,city,dob,id,emailid;
	Button sub,bak;
	SmsManager smsManager;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        getname=(EditText)findViewById(R.id.getname);
        getphone=(EditText)findViewById(R.id.getphonenumber);
        getblood=(EditText)findViewById(R.id.getamount);/*bloodgroup*/
        getcity=(EditText)findViewById(R.id.getcity);
        getdob=(EditText)findViewById(R.id.editText1);
        getid=(EditText)findViewById(R.id.editText2);
        getemail=(EditText)findViewById(R.id.email);
        sub=(Button)findViewById(R.id.submitnewuser);
        bak=(Button)findViewById(R.id.back1);
        db = openOrCreateDatabase("UserDetails.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.setVersion(1);
		db.setLocale(Locale.getDefault());
		String create = "Create table if not exists UserDetails_Table1(person_name text,person_phone text,person_blood text,person_city text,person_dob text,person_id text Primary key,person_email text)";
		db.execSQL(create);
        bak.setOnClickListener(this);
        sub.setOnClickListener(this);        
        }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.submitnewuser:
			name=getname.getText().toString();
			phone=getphone.getText().toString();
			phone="+91"+phone;
			blood=getblood.getText().toString();
			city=getcity.getText().toString();
			dob=getdob.getText().toString();
			id=getid.getText().toString();
			emailid = getemail.getText().toString();
			ContentValues content = new ContentValues();
			content.put("person_name",name);
			content.put("person_phone",phone);
			content.put("person_blood",blood);
			content.put("person_city",city);
			content.put("person_dob", dob);
			content.put("person_id", id);
			content.put("person_email", emailid);
			db.insert("UserDetails_Table1", null, content);
	        startActivity(new Intent(Newuser.this,User_selection.class));
	        finish();
	        db.close();
	        break;
		case R.id.back1:
			startActivity(new Intent(Newuser.this,User_selection.class));
			db.close();
			finish();
		}
	}
}
