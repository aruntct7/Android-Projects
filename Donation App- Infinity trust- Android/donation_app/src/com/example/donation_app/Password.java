package com.example.donation_app;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class Password extends Activity implements OnClickListener{

	Button go;
	Button change;
	EditText getpassword;
	String password;
	static Boolean initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initial=getSharedPreferences("PASSWORD", 0).getBoolean("initial",true);
        if(initial){
        	getSharedPreferences("PASSWORD",0).edit().putString("initpassword","password").commit();
        }
        password=getSharedPreferences("PASSWORD",0).getString("initpassword","");
        go=(Button)findViewById(R.id.go);
        change=(Button)findViewById(R.id.changepassword);
        getpassword=(EditText)findViewById(R.id.getpassword);
        go.setOnClickListener(this);
        change.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.go:
			if(password.equals(getpassword.getText().toString())){
				password = "";
				finish();
			startActivity(new Intent(Password.this,User_selection.class));
			}
			else{
				Toast.makeText(this, "Your password is not correct! Please, enter again",Toast.LENGTH_SHORT).show();
				getpassword.setText("");
			}
			break;
		case R.id.changepassword:
			finish();
			startActivity(new Intent(Password.this,Changepassword.class));
			break;
			
		}
		
	}
    
}
