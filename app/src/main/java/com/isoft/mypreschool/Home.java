package com.isoft.mypreschool;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.isoft.mypreschool.api.PickServiceGenerator;
import com.isoft.mypreschool.api.Pick_api;
import com.isoft.mypreschool.modelclass.Login_model;
import com.isoft.mypreschool.modelclass.Save_model;
import com.isoft.mypreschool.modelclass.User_model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Activity{
	  /*Integer[] pics ={ R.drawable.logo_large332, R.drawable.wildwood,
				R.drawable.usa };
		*/
	public static int cameraID = 1;
	private static String ab=""+0;
	  ProgressDialog progressDialog;

	  private Context context;
	  private int Status=0;
	  


		private TextView btnsubmit;
		Intent inte;
		public static String userid;
		private static String utype;
		private static String sig;
		private String sname;
		
		private static String paths;
		private String pname;
		private TextView txtsname;
		private TextView txtpname;
		
		
//		private LinearLayout horizontalOuterLayout;
//		private HorizontalScrollView horizontalScrollview;
		private int scrollMax;
		private int scrollPos = 0;
		private TimerTask clickSchedule;
		private TimerTask scrollerSchedule;
		private TimerTask faceAnimationSchedule;
		private Button clickedButton = null;
		private Timer scrollTimer = null;
		private Timer clickTimer = null;
		private Timer faceTimer = null;
		private Boolean isFaceDown = true;
		private String[] imageNameArray = { "usa", "wildwood", "logo_large332",
				"usa", "wildwood", "logo_large332" };
		private String[] nameArray = { "usa", "wildwood", "logo_large332", "usa",
				"wildwood", "logo_large332" };
		private int ij = 0;
		
		
		
		public static ImageView pphoto;
		private Preference pref;
		
		public static byte[] pikimg;
		public static byte[] dropimg;
		Pick_api api;
		private String str_rid="";

		private LinearLayout linchild;
	JSONArray jsonchildid = new JSONArray();
		
		
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.homes);
	        
	        context=this;


	        btnsubmit=findViewById(R.id.register);
	        txtsname=findViewById(R.id.tt1);
	        txtpname=findViewById(R.id.pt1);
	        pphoto=findViewById(R.id.master_photoview);
			linchild=findViewById(R.id.lin_child);
	        
	        pref=Preference.getInstance(context);
	        inte=getIntent();
	        
	        
	        
	        if(inte !=null)
	        {
	        	userid=inte.getStringExtra("userid");
	        	//pref.putString(Interfacemulty.userid, userid);
	        	//Log.e("userid", ""+userid);
	        	utype=inte.getStringExtra("utype");
	        	sname=inte.getStringExtra("sname");
	        	pname=inte.getStringExtra("pname");
				if(inte.hasExtra("id")) {
					str_rid = inte.getStringExtra("id");
				}
	        	txtsname.setText(sname);
	        	txtpname.setText("Welcome "+pname);
	        }

		 getuserdetails();

	        btnsubmit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(isConnected())
					{
						
					//	pref.putString(Interfacemulty.userid, userid);
					//	pref.putString(Interfacemulty.status, ab);
					//	pref.putString(Interfacemulty.utype, utype);
						View view = View.inflate(context, R.layout.drivermsghead, null);
						
						TextView btntv = (TextView) view.findViewById(R.id.btnone);
						
						final Dialog dialog = new Dialog(context,R.style.DialogTheme);
						//dialog = new Dialog(this, R.style.DialogTheme);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(view);
						
						
						Window window =dialog.getWindow();
					    Point size = new Point();

					    Display display = window.getWindowManager().getDefaultDisplay();
					    display.getSize(size);

					    int width = size.x;

					    window.setLayout((int) (width * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
					    window.setGravity(Gravity.CENTER);
					    dialog.show();
					
						btntv.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								Intent i = new Intent(Home.this,CameraView.class);
						        startActivityForResult(i, 999);
							}
						});
					
						
						  /*Thread th = new Thread() {
								public void run() {
									try {

										sleep(2000);
										

									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										
										pref.putString(Interfacemulty.signok, "0");
										
										
										Intent i = new Intent(Home.this,CameraView.class);
							        startActivityForResult(i, 999);
									}
								}
							};
							th.start(); */
						
						
						
						
						
						
						
						
						
						
						
					
						
						/* progressDialog = new ProgressDialog(Home.this,
									AlertDialog.THEME_HOLO_LIGHT);
							progressDialog.setMessage("Saving.....");
							progressDialog.setCancelable(false);
							progressDialog.show();
							
			                new HttpAsyncTask().execute("http://pickmychild.org/GM/app/pickinsert.php");*/
					
					}else
					{
						Toast.makeText(context, "Device not connect internet", Toast.LENGTH_SHORT).show();
					}
					
					
				}
			});
	        
	        
	        
//
//	    	horizontalScrollview = (HorizontalScrollView) findViewById(R.id.horiztonal_scrollview_id);
//			horizontalOuterLayout = (LinearLayout) findViewById(R.id.horiztonal_outer_layout_id);
//			horizontalScrollview.setHorizontalScrollBarEnabled(false);
//			addImagesToView();
//
//			ViewTreeObserver vto = horizontalOuterLayout.getViewTreeObserver();
//			vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//				@Override
//				public void onGlobalLayout() {
//					horizontalOuterLayout.getViewTreeObserver()
//							.removeGlobalOnLayoutListener(this);
//					getScrollMaxAmount();
//					startAutoScrolling();
//				}
//			});


	        

	    }
	 
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	finish();
}

public boolean isConnected()
{

	boolean haveConnectedWifi = false;
	boolean haveConnectedMobile = false;

	ConnectivityManager cm = (ConnectivityManager) context
			.getSystemService(Context.CONNECTIVITY_SERVICE);
	NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	for (NetworkInfo ni : netInfo) {
		if (ni.getTypeName().equalsIgnoreCase("WIFI"))
			if (ni.isConnected())
				haveConnectedWifi = true;
		if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
			if (ni.isConnected())
				haveConnectedMobile = true;
	}
	return haveConnectedWifi || haveConnectedMobile;

}



//public void getScrollMaxAmount() {
//	int actualWidth = (horizontalOuterLayout.getMeasuredWidth() - 512);
//	scrollMax = actualWidth;
//}

//public void startAutoScrolling() {
//	//Log.e("scrollTimer", "" + scrollTimer);
//	if (scrollTimer == null) {
//		scrollTimer = new Timer();
//		final Runnable Timer_Tick = new Runnable() {
//			public void run() {
//				moveScrollView();
//			}
//		};
//
//		if (scrollerSchedule != null) {
//			scrollerSchedule.cancel();
//			scrollerSchedule = null;
//		}
//		scrollerSchedule = new TimerTask() {
//			@Override
//			public void run() {
//				runOnUiThread(Timer_Tick);
//			}
//		};
//
//		scrollTimer.schedule(scrollerSchedule, 40, 20);
//
//	}
//	if (ij == 6) {
//		// ij=0;
//		addImagesToView();
//	}
//}
//
//public void moveScrollView() {
//	scrollPos = (int) (horizontalScrollview.getScrollX() + 1.0);
//
//	if (scrollPos >= scrollMax) {
//		scrollPos = 0;
//
//	}
//	// Log.e("scrollPos", ""+scrollPos);
//	horizontalScrollview.scrollTo(scrollPos, 0);
//
//}

/** Adds the images to view. */
//public void addImagesToView() {
//	//Log.e("i-imageNameArray", "" + imageNameArray.toString());
//	for (int i = 0; i < imageNameArray.length; i++) {
//		ij++;
//		final Button imageButton = new Button(this);
//		int imageResourceId = getResources().getIdentifier(
//				imageNameArray[i], "drawable", getPackageName());
//
//		Drawable image = this.getResources().getDrawable(imageResourceId);
//		imageButton.setBackgroundDrawable(image);
//		imageButton.setTag(i);
//		/*
//		 * LinearLayout.LayoutParams params = new
//		 * LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
//		 * LayoutParams.WRAP_CONTENT); imageButton.setLayoutParams(params);
//		 */
//		imageButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				if (isFaceDown) {
//					if (clickTimer != null) {
//						clickTimer.cancel();
//						clickTimer = null;
//					}
//					clickedButton = (Button) arg0;
//					stopAutoScrolling();
//					clickedButton.startAnimation(scaleFaceUpAnimation());
//					clickedButton.setSelected(true);
//					clickTimer = new Timer();
//
//					if (clickSchedule != null) {
//						clickSchedule.cancel();
//						clickSchedule = null;
//					}
//
//					clickSchedule = new TimerTask() {
//						public void run() {
//							startAutoScrolling();
//						}
//					};
//
//					clickTimer.schedule(clickSchedule, 1500);
//				}
//			}
//		});
//
//		/*
//		 * LinearLayout.LayoutParams params = new
//		 * LinearLayout.LayoutParams(256,256); params.setMargins(0, 25, 0,
//		 * 25);
//		 */
//		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		params.leftMargin = 25;
//		params.rightMargin=25;
//
//		imageButton.setLayoutParams(params);
//		horizontalOuterLayout.addView(imageButton);
//
//		//Log.e("i-val", "" + ij);
//
//	}
//
//}

public Animation scaleFaceUpAnimation() {
	Animation scaleFace = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
			Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
			0.5f);
	scaleFace.setDuration(500);
	scaleFace.setFillAfter(true);
	scaleFace.setInterpolator(new AccelerateInterpolator());
	Animation.AnimationListener scaleFaceAnimationListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationStart(Animation arg0) {
			
			String type = nameArray[(Integer) clickedButton.getTag()];
			if (type.contentEquals("usa")) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://usaprintings.com/"));
			
				startActivity(browserIntent);
				
				
			} else if (type.contentEquals("wildwood")) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://www.wildwoodex.com/"));
				
				startActivity(browserIntent);
			} else {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://trucksoft.net/"));
				
				startActivity(browserIntent);

			}

			isFaceDown = false;

		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
		}

		@Override
		public void onAnimationEnd(Animation arg0) {
			if (faceTimer != null) {
				faceTimer.cancel();
				faceTimer = null;
			}

			faceTimer = new Timer();
			if (faceAnimationSchedule != null) {
				faceAnimationSchedule.cancel();
				faceAnimationSchedule = null;
			}
			faceAnimationSchedule = new TimerTask() {
				@Override
				public void run() {
					faceScaleHandler.sendEmptyMessage(0);
				}
			};

			faceTimer.schedule(faceAnimationSchedule, 750);
		}
	};
	scaleFace.setAnimationListener(scaleFaceAnimationListener);
	return scaleFace;
}

private Handler faceScaleHandler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
		if (clickedButton.isSelected() == true)
			clickedButton.startAnimation(scaleFaceDownAnimation(500));
	}
};

public Animation scaleFaceDownAnimation(int duration) {
	Animation scaleFace = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f,
			Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
			0.5f);
	scaleFace.setDuration(duration);
	scaleFace.setFillAfter(true);
	scaleFace.setInterpolator(new AccelerateInterpolator());
	Animation.AnimationListener scaleFaceAnimationListener = new Animation.AnimationListener() {
		@Override
		public void onAnimationStart(Animation arg0) {
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
		}

		@Override
		public void onAnimationEnd(Animation arg0) {

			isFaceDown = true;
		}
	};
	scaleFace.setAnimationListener(scaleFaceAnimationListener);
	return scaleFace;
}

public void stopAutoScrolling() {
	//Log.e("stop", "stop scroll");
	if (scrollTimer != null) {
		scrollTimer.cancel();
		scrollTimer = null;
	}
}



/*public void onPause() {
	super.onPause();
	finish();
}*/

public void onDestroy() {
	clearTimerTaks(clickSchedule);
	clearTimerTaks(scrollerSchedule);
	clearTimerTaks(faceAnimationSchedule);
	clearTimers(scrollTimer);
	clearTimers(clickTimer);
	clearTimers(faceTimer);

	clickSchedule = null;
	scrollerSchedule = null;
	faceAnimationSchedule = null;
	scrollTimer = null;
	clickTimer = null;
	faceTimer = null;
	super.onDestroy();
}

private void clearTimers(Timer timer) {
	if (timer != null) {
		timer.cancel();
		timer = null;
	}
}

private void clearTimerTaks(TimerTask timerTask) {
	if (timerTask != null) {
		timerTask.cancel();
		timerTask = null;
	}
}






public void ipload()
{

	getsaveddata();

}
public static String ecode64(byte[] image) {
	Bitmap bitmap = decodeSampledBitmap(image);
	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	bitmap.compress(CompressFormat.JPEG, 70, stream);
	return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
}
public byte[] convertStringToByte(String strPhoto) {
	byte[] buffer = null;
	if (strPhoto.length() > 0) {
		byte[] logobyte = strPhoto.getBytes();
		byte[] img = Base64.decode(logobyte, Base64.DEFAULT);
		final int buffSize = img.length;
		byte[] tempBuffer = new byte[buffSize];
		ByteArrayInputStream memstream = new ByteArrayInputStream(img);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int size = 0;
		while ((size = memstream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
			stream.write(tempBuffer, 0, size);
		}
		buffer = stream.toByteArray();
		stream = null;
		memstream = null;
		logobyte = null;
		img = null;
		tempBuffer = null;
	}
	return buffer;
}
private static int calculateInSampleSize(Options options, int reqWidth,
		int reqHeight) {
	// Raw height and width of image
	final int height = options.outHeight;
	final int width = options.outWidth;
	int inSampleSize = 1;

	if (height > reqHeight || width > reqWidth) {

		// Calculate ratios of height and width to requested height and
		// width
		final int heightRatio = Math.round((float) height
				/ (float) reqHeight);
		final int widthRatio = Math.round((float) width / (float) reqWidth);

		// Choose the smallest ratio as inSampleSize value, this will
		// guarantee
		// a final image with both dimensions larger than or equal to the
		// requested height and width.
		inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	}

	return inSampleSize;
}

private static Bitmap decodeSampledBitmap(byte[] image) {
	int reqWidth = 200;
	int reqHeight = 200;

	// First decode with inJustDecodeBounds=true to check dimensions
	final Options options = new Options();
	options.inJustDecodeBounds = true;
	BitmapFactory.decodeByteArray(image, 0, image.length, options);

	// Calculate inSampleSize
	options.inSampleSize = calculateInSampleSize(options, reqWidth,
			reqHeight);

	// Decode bitmap with inSampleSize set
	options.inJustDecodeBounds = false;
	return BitmapFactory.decodeByteArray(image, 0, image.length, options);
}





@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	
	//Log.e("useridfdf", userid);
	//Log.e("ab", ab);
	//Log.e("utype", utype);
	//ab=pref.getString(Interfacemulty.status);
	//userid=pref.getString(Interfacemulty.userid);
	//=pref.getString(Interfacemulty.utype);
	sig=pref.getString(Interfacemulty.signok);
	//Log.e("useridfdf", pref.getString(Interfacemulty.userid));
	//Log.e("sigsig", pref.getString(Interfacemulty.signok));
	if(sig.contentEquals("1"))
	{
		ipload();
	}

}
	private void getsaveddata() {

			if (progressDialog != null && progressDialog.isShowing()) {

			} else {
				progressDialog = new ProgressDialog(context,
						AlertDialog.THEME_HOLO_LIGHT);
				progressDialog.setMessage("Please wait...");
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		byte[] image=pikimg;
		String strPhoto = "";
		if (image != null) {
			if (image.length > 0) {
				try {

					strPhoto = ecode64(image);

				} catch (Exception e) {
					Log.e("Image Exception", e.getMessage());
				}
			}
		} else {
			strPhoto = null;
		}
			api = PickServiceGenerator.createService(Pick_api.class, context);
			Call<Save_model> call = api.savedata_pick(ab,userid,utype,strPhoto,jsonchildid.toString());

			call.enqueue(new Callback<Save_model>() {
				@Override
				public void onResponse(Call<Save_model> call, Response<Save_model> response) {
					Log.e(" Responsev"," "+response.toString());
					Log.e(" Responsesskk"," "+String.valueOf(response.code()));
					if(response.isSuccessful()) {

						cancelprogresssdialog();
						//  Log.e(" Responsecqevv","z "+response.body());
						if (response.body() != null) {
//							if(response.body().status.contentEquals("1")) {


									MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.thanks);
									mediaPlayer.start();
									Toast.makeText(getBaseContext(), "Data saved successfully", Toast.LENGTH_LONG).show();
									Intent inte=new Intent(getApplicationContext(), Thank_you.class);
									//Log.e("pth", "kk"+paths);
									if(paths !=null)
									{
										inte.putExtra("img", ""+paths);
									}
									startActivity(inte);
									finish();

//							}else
//							{
//
//
//								Toast.makeText(getBaseContext(), "Data not saved", Toast.LENGTH_LONG).show();
//							}
						}
					}
				}

				@Override
				public void onFailure(Call<Save_model> call, Throwable t) {
					Log.e("tttt", " Response Error " + t.getMessage());
					cancelprogresssdialog();
					Toast.makeText(context, "Login credential wrong please try again ", Toast.LENGTH_SHORT).show();
				}
			});

	}

	private void cancelprogresssdialog()
	{
		try {
			if ((progressDialog != null) && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
		} catch (final IllegalArgumentException e) {
			// Log.e("err1.........",""+e.toString());
			// Handle or log or ignore
		} catch (final Exception e) {
			// Log.e("err2........",""+e.toString());
			// Handle or log or ignore
		} finally {
			progressDialog = null;
		}
	}

	private void getuserdetails() {

			if (progressDialog != null && progressDialog.isShowing()) {

			} else {
				progressDialog = new ProgressDialog(context,
						AlertDialog.THEME_HOLO_LIGHT);
				progressDialog.setMessage("Please wait...");
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
		Log.e(" urlll","https://mypreschool.net/app/child_details.php?pid="+str_rid);
			api = PickServiceGenerator.createService(Pick_api.class, context);
			Call<JsonObject> call = api.getUserinformation(str_rid);

			call.enqueue(new Callback<JsonObject>() {
				@Override
				public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
					Log.e(" Responsev"," "+response.toString());
					Log.e(" Responsesskk"," "+String.valueOf(response.code()));
					if(response.isSuccessful()) {

						cancelprogresssdialog();
						Log.e(" Responsecqevv","z "+response.body().toString());
						if (response.body() != null) {
							String strresponse = response.body().toString();
							try {


								if (strresponse != null) {
									JSONObject jsonresponse = new JSONObject(strresponse);
									//Log.e("responsez","@"+response.toString());
									String status = jsonresponse
											.getString("status");
									Log.e("status","@"+status);
									String ids = jsonresponse
											.getString("id");
									Log.e("ids","@"+ids);
									JSONArray jsonArray = new JSONArray(ids);
									String chnames = jsonresponse
											.getString("child_name");
									JSONArray jsonArrayname = new JSONArray(chnames);
if(jsonArray !=null && jsonArray.length()>0)
{
	for(int k=0;k<jsonArray.length();k++)
	{
		String vzk=jsonArray.getString(k);
		String vzkname=jsonArrayname.getString(k);
		Log.e("vzk","@"+vzk);
		addLayout(vzkname,vzk);
	}
}

								}
							}catch (Exception e)
							{

							}
						}
					}
				}

				@Override
				public void onFailure(Call<JsonObject> call, Throwable t) {
					Log.e("tttt", " Response Error " + t.getMessage());
					cancelprogresssdialog();
					Toast.makeText(context, "Login credential wrong please try again ", Toast.LENGTH_SHORT).show();
				}
			});

	}

	private void addLayout(String strname, String strid) {
		View layout2 = LayoutInflater.from(this).inflate(R.layout.childlist, linchild, false);

		TextView txtname = layout2.findViewById(R.id.txtname);
		TextView txtid = layout2.findViewById(R.id.txtid);
		RadioGroup rg1=layout2.findViewById(R.id.rgroup1);
		RadioButton pickup=layout2.findViewById(R.id.pickup);
		RadioButton dropoff=layout2.findViewById(R.id.drop);
		txtname.setText(""+strname);
		txtid.setText(""+strid);
		jsonchildid.put(""+strid);
		rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup arg0, int arg1) {

				if (pickup.isChecked()) {

					Status=1;
					ab=String.valueOf(Status);

				} else if (dropoff.isChecked()) {
					Status=0;
					ab=String.valueOf(Status);

				}
			}
		});


		linchild.addView(layout2);
	}

}
