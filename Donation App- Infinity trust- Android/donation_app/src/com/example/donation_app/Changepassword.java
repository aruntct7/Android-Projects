package com.example.donation_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Changepassword extends Activity implements OnClickListener{
	
	Button change,back;
	EditText old_password;
	EditText new_password;
	EditText confirm_password;
	String existing_password;
	String old = "";
	String newpass = "";
	String retype = "";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chagepassword);
        change=(Button)findViewById(R.id.confirmpasswordbutton);
        old_password=(EditText)findViewById(R.id.oldpassword);
        new_password=(EditText)findViewById(R.id.newpassword);
        confirm_password=(EditText)findViewById(R.id.confirmpassword);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        change.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		boolean flag=false;
		boolean flag1=false;
		switch (v.getId()) {
		case R.id.confirmpasswordbutton:
			old=old_password.getText().toString();
			newpass=new_password.getText().toString();
			retype=confirm_password.getText().toString();
			existing_password=getSharedPreferences("PASSWORD",0).getString("initpassword","");
			if(old.equals(existing_password)){
				flag1=true;
				if(newpass.equals(retype)){
					flag=true;
					getSharedPreferences("PASSWORD",0).edit().putString("initpassword",newpass).commit();
					getSharedPreferences("PASSWORD",0).edit().putBoolean("initial",false).commit();
					existing_password=getSharedPreferences("PASSWORD",0).getString("initpassword","");
					Toast.makeText(this, "Password has been changed", Toast.LENGTH_SHORT).show();
				}
			}
			if(flag1==false){
				Toast.makeText(this, "Incorrect old password", Toast.LENGTH_SHORT).show();
			}
			if((flag1==true)&&(flag==false)){
				Toast.makeText(this, "Incorrect confirmation password", Toast.LENGTH_SHORT).show();
			}
			if(flag==true){
				finish();
				startActivity(new Intent(Changepassword.this,Password.class));
			}
			else{
				old_password.setText("");
				new_password.setText("");
				confirm_password.setText("");
			}
			break;
		case R.id.back:
			finish();
			startActivity(new Intent(Changepassword.this,Password.class));
		}
	}
}
