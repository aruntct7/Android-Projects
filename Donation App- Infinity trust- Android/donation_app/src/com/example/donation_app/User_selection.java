package com.example.donation_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class User_selection extends Activity implements OnClickListener{
	
	Button newuser,view;
	Button existinguser;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_selection);
        newuser=(Button)findViewById(R.id.newuser);
        newuser.setOnClickListener(this);
        existinguser=(Button)findViewById(R.id.existinguser);
        existinguser.setOnClickListener(this);
        view = (Button)findViewById(R.id.viewuser);
        view.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.newuser:
			startActivity(new Intent(User_selection.this,Newuser.class));
			break;
		case R.id.existinguser:
			startActivity(new Intent(User_selection.this,Existing_user.class));
			break;
		case R.id.viewuser:
			startActivity(new Intent(User_selection.this,View_data.class));
			break;
		}
		
	}
	

}
