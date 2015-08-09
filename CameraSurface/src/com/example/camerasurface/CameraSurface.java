package com.example.camerasurface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraSurface extends Activity implements SurfaceHolder.Callback{
	
	SurfaceHolder surfaceHolder;
	Camera camera; 
	
	SurfaceView surface;
	ImageView image;
	Button click;


// Preview mPreview; //Global variable

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		surface = (SurfaceView) findViewById(R.id.surfaceView1);
		image = (ImageView) findViewById(R.id.captureimageView1);
		click = (Button) findViewById(R.id.capturebutton1);
	//	mPreview = new Preview(this); //onCreate()
	  //  setContentView(mPreview); //on

		surfaceHolder = surface.getHolder();
		camera =Camera.open();
		surfaceHolder.addCallback(this);
		surface.setClickable(true);
		surfaceCreated(surfaceHolder);
 click.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				camera.takePicture(null, null, pict);
				
			}
		});
		
	
	}
	
	PictureCallback pict=new PictureCallback()
	{

		@SuppressLint("SdCardPath")
		public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
			// TODO Auto-generated method stub
			Date date=new Date();
			SimpleDateFormat am=new SimpleDateFormat("yyyymmddhhmm");
			String s=am.format(date);
			String photoFile = "Picture_" + s + ".jpg";
			String file = "/sdcard/"+ File.separator + photoFile;
			File pi = new File(file);
			try
			{
				FileOutputStream outStream = new FileOutputStream(pi);				
				outStream.write(data);
				outStream.flush();
				outStream.close();				
				Toast.makeText(CameraSurface.this,"Photo Saved Successfully!" ,Toast.LENGTH_SHORT).show();
				Bitmap bm=BitmapFactory.decodeFile(file);
				image.setImageBitmap(bm);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			camera.startPreview();
		}

	};
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (camera != null){
			try {
				camera.setPreviewDisplay(surfaceHolder);
				camera.startPreview();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
		public void surfaceCreated(SurfaceHolder holder) {
		camera = Camera.open();

	}
/*	 @Override
	 public void surfaceCreated(SurfaceHolder holder) {
	     if (camera == null) {
	         camera = Camera.open();
	         System.out.println("open the camera");
	         try {
	             camera.setPreviewDisplay(holder);

	             // TODO test how much setPreviewCallbackWithBuffer is faster
	           //  camera.setPreviewCallback(this);
	         } catch (IOException e) {
	             camera.release();
	             camera = null;
	         }
	     }
	 }*/
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
		camera = null;
		finish();

	}
}
