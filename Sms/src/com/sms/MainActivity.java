package com.sms;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class MainActivity extends Activity {
 
	Button Send;
	EditText Phone, Msg;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Phone = (EditText) findViewById(R.id.editText1);
        Msg = (EditText) findViewById(R.id.editText2);
        
        Send = (Button) findViewById(R.id.button1);
        Send.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                sendSMS(Phone.getText().toString(), Msg.getText().toString());
            }
        });
    }
 
    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }    
}