package com.song;



import org.apache.commons.codec.binary.Base64;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Bundle;
import android.os.Environment;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.content.Context;
//import android.util.Base64;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

//import com.androidcodemonkey.tutorial.base64.Base64.InputStream;

public class MainActivity extends Activity implements OnClickListener 
{ 
	
    private static final String LOG_TAG = "MainActivity";
    String En;
    private static String mFileName = null;
    HttpClient httpclient;
    Button b;
	List<NameValuePair> nameValuePairs;
    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private PlayButton   mPlayButton = null;
    private MediaPlayer   mPlayer = null;
   
    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    class RecordButton extends Button {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    setText("Stop recording");
                } else {
                    setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    class PlayButton extends Button {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };
  

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
        }
    }

    public MainActivity() throws IOException {
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/MainActivity.mp4";
        System.out.println("Dir : "+mFileName);
      
        
       
    }
	private String encodedFile(String fileName)
			throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;

	}
	private  byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
}




        
    
    
   
 
	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
      //  run();
        b.setOnClickListener(this);
        LinearLayout ll = new LinearLayout(this);
        mRecordButton = new RecordButton(this);
        ll.addView(mRecordButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        mPlayButton = new PlayButton(this);
        ll.addView(mPlayButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        setContentView(ll);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
       
    }

    
    	 
    void mysqlinsert() {
		//SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Profile.this);
		///SharedPreferences.Editor editor = pref.edit();
		//String deviceid= pref.getString("MEM2", "");
		 //city=pref.getString("city","");
//String ImgUrl="http://192.168.1.52/nbcu/nbImages/nbDefaultUserPhoto.jpg";
		/*Bitmap bitmap = photo;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // compress to
																// which format
																// you want.
		byte[] byte_arr = stream.toByteArray();
		String image_str = Base64.encodeToString(byte_arr, 0);
*/
				httpclient = new DefaultHttpClient();
		// add your data
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
				6);
	//	nameValuePairs.add(new BasicNameValuePair("image", ImgUrl));

		//nameValuePairs.add(new BasicNameValuePair("nbDeviceId", nbDeviceId));

		nameValuePairs.add(new BasicNameValuePair("name","tec"));
		nameValuePairs.add(new BasicNameValuePair("Song", En));

		//nameValuePairs.add(new BasicNameValuePair("memberid", memberid));
		//nameValuePairs.add(new BasicNameValuePair("chaptername", chaptername));
		//nameValuePairs.add(new BasicNameValuePair("zone", zone));

//		nameValuePairs.add(new BasicNameValuePair("city", city));
		sendData2(nameValuePairs);
	}

	// executing the HttpPost
	private void sendData2(ArrayList<NameValuePair> data) {
		// TODO Auto-generated method stub
		// 1) Connect via HTTP. 2) Encode data. 3) Send data.
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://192.168.1.103/ievent/.php");
			httppost.setEntity(new UrlEncodedFormEntity(data));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			 //HttpResponse response = httpclient.execute(httppost);
			final String response = httpclient.execute(httppost,
					responseHandler);
		System.out.println("Response : " + response);

			// ------------------if registered moving to another
			// acitivity-----//
			//final Intent home = new Intent(Profile.this, Home.class);

			if (response.equals("registered")) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(getApplicationContext(), "Registered",
								Toast.LENGTH_SHORT).show();
			//	SavePreferences("city", city)		;
						//afterprofile.putExtra("nbDeviceId", )
						//startActivity(home);

					//	dialog.dismiss();

					}
				});

			}

			else {
				//dialog.dismiss();
				Toast.makeText(getApplicationContext(), "Retry"+response,
						Toast.LENGTH_SHORT).show();

			}

		}

		catch (Exception e) {
			// dialog.dismiss();
			// Log.e("log_tag", "Error:  "+e.toString());
		}

	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		
		case R.id.b1:
			 En=encodedFile(mFileName);
		        System.out.println(En);
		        mysqlinsert();

			
		}
		
	}
	}

/*Thread myThread = new Thread(new Runnable(){
    @Override
    public void run()
    {
        // Do Stuff
    }
});

myThread.start();*/

/*private void sendData(ArrayList<NameValuePair> data) {
	// TODO Auto-generated method stub
	System.out.println("enters send data");
	// 1) Connect via HTTP. 2) Encode data. 3) Send data.
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost("http://192.168.1.52/ievent/audio.php");
	
	try {
		httppost.setEntity(new UrlEncodedFormEntity(data));
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		}
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = "";
		try {
			System.out.println("enters try");
		response = httpclient.execute(httppost, responseHandler);
		System.out.println(response);
		} catch (ClientProtocolException e) {
		e.printStackTrace();
		System.out.println("client");
		//Toast.makeText(getApplicationContext(), "client",Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
		e.printStackTrace();
		System.out.println("io");
		//Toast.makeText(getApplicationContext(), "io"+response, Toast.LENGTH_SHORT)
		//.show();
		}
		/*httppost.setEntity(new UrlEncodedFormEntity(data));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		 //HttpResponse response = httpclient.execute(httppost);
		final String response = httpclient.execute(httppost,
				responseHandler);
		//System.out.println("Response : " + response);

		// ------------------if registered moving to another
		// acitivity-----//
	//	final Intent afterprofile = new Intent(Profile.this, Home.class);*/

	/*	if (response.equals("registered")) {
			/*runOnUiThread(new Runnable() {
				public void run() {*/
		//	System.out.println("regit");
				//	Toast.makeText(getApplicationContext(), "registered",
				//			Toast.LENGTH_SHORT).show();
					
					//afterprofile.putExtra("deviceid", )
//					startActivity(afterprofile);

					//dialog.dismiss();

				//}
			//});

		

	/*	else {
			System.out.println("else part");
			//dialog.dismiss();
			//Toast.makeText(getApplicationContext(), "Retry",
			//		Toast.LENGTH_SHORT).show();

		}

	

}
}*/