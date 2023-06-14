package com.isoft.mypreschool;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.isoft.mypreschool.R.layout;
import com.isoft.mypreschool.api.PickServiceGenerator;
import com.isoft.mypreschool.api.Pick_api;
import com.isoft.mypreschool.modelclass.Forgot_model;
import com.isoft.mypreschool.modelclass.Login_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
	private Button btnsubmit;
	private LinearLayout ltsub;
	private Context context;
	private static String strMobile;
	private TextView txtpin;
	ProgressDialog progressDialog;

	// private DbUtil dbUtil;
	String strUserId;
	String strPass;
	private String msgdetail;
	String password;
	private EditText edtPin;
	private static String utype;
	private static String sname;
	private static String pname;
final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS=124;
	private static String oldpin;
	private static String chdname;
/*	Integer[] pics = { R.drawable.logo_large332, R.drawable.wildwood,
			R.drawable.usa };*/
	Dialog dialog;

	//private LinearLayout horizontalOuterLayout;
	//private HorizontalScrollView horizontalScrollview;
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

//	private static ArrayList<String> arrdetails;
	Pick_api api;
ProgressDialog progressdlog;
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.login);
		context = this;

		ltsub = (LinearLayout) findViewById(R.id.t1);

		btnsubmit = (Button) findViewById(R.id.save);
		edtPin = (EditText) findViewById(R.id.p_name);
		edtPin.addTextChangedListener(tst);
		txtpin = (TextView) this.findViewById(R.id.txt_pin);

		//arrdetails = new ArrayList<String>();

	//	horizontalScrollview = (HorizontalScrollView) findViewById(R.id.horiztonal_scrollview_id);
	//	horizontalOuterLayout = (LinearLayout) findViewById(R.id.horiztonal_outer_layout_id);

		 MediaPlayer mediaPlayer = MediaPlayer.create(context,
		 R.raw.welschool);
		 mediaPlayer.start();

//		horizontalScrollview.setHorizontalScrollBarEnabled(false);
//		addImagesToView();
//
//		ViewTreeObserver vto = horizontalOuterLayout.getViewTreeObserver();
//		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
//			@Override
//			public void onGlobalLayout() {
//				horizontalOuterLayout.getViewTreeObserver()
//						.removeGlobalOnLayoutListener(this);
//				getScrollMaxAmount();
//				startAutoScrolling();
//			}
//		});

		btnsubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (isConnected()) {
					if (edtPin.getText().toString().trim().length() > 0) {

						/*
						 * progressDialog = ProgressDialog.show(context, "",
						 * "Login...");
						 */
						getloginwithpin();
//						new HttpAsyncTask()
//								.execute("http://pickmychild.org/GM/app/login.php");
					} else {
						// edtPin.setError("Enter a valid pin");
						Toast.makeText(getBaseContext(),
								"Enter valid pin number", Toast.LENGTH_LONG)
								.show();
					}
					// }
				} else {
					Toast.makeText(context, "Device not connect internet",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			insertDummyContactWrapper();
		}
	}
	private void getloginwithpin() {
		if (edtPin.getText().toString().trim() != null && edtPin.getText().toString().trim().length() > 0
		)
		{
			if (progressdlog != null && progressdlog.isShowing()) {

			} else {
				progressdlog = new ProgressDialog(context,
						AlertDialog.THEME_HOLO_LIGHT);
				progressdlog.setMessage("Please wait...");
				progressdlog.setCancelable(false);
				progressdlog.show();
			}
			api = PickServiceGenerator.createService(Pick_api.class, context);
			Call<Login_model> call = api.getQLogin(edtPin.getText().toString().trim());

			call.enqueue(new Callback<Login_model>() {
				@Override
				public void onResponse(Call<Login_model> call, Response<Login_model> response) {
					Log.e(" Responsev"," "+response.toString());
					Log.e(" Responsesskk"," "+String.valueOf(response.code()));
					if(response.isSuccessful()) {

						cancelprogresssdialog();
						//  Log.e(" Responsecqevv","z "+response.body());
						if (response.body() != null) {
							if(response.body().status==1) {
								Toast.makeText(getBaseContext(), "Login successfully",
										Toast.LENGTH_LONG).show();
								MediaPlayer mediaPlayer = MediaPlayer.create(context,
										R.raw.welcomeaccessgranted);
								mediaPlayer.start();
								Intent inte = new Intent(getApplicationContext(), Home.class);
								inte.putExtra("userid", "" + edtPin.getText().toString());
								inte.putExtra("utype", "" + response.body().user_type);
								inte.putExtra("sname", "" +  response.body().child_name);

								inte.putExtra("pname", "" +  response.body().guardian_name);

								startActivity(inte);
								finish();
							}
						}
					}
				}

				@Override
				public void onFailure(Call<Login_model> call, Throwable t) {
					Log.e("tttt", " Response Error " + t.getMessage());
					cancelprogresssdialog();
					Toast.makeText(context, "Login credential wrong please try again ", Toast.LENGTH_SHORT).show();
				}
			});
		}else{

			Toast.makeText(context, "Please enter all fields ", Toast.LENGTH_SHORT).show();

		}
	}

	private void cancelprogresssdialog()
	{
		try {
			if ((progressdlog != null) && progressdlog.isShowing()) {
				progressdlog.dismiss();
			}
		} catch (final IllegalArgumentException e) {
			// Log.e("err1.........",""+e.toString());
			// Handle or log or ignore
		} catch (final Exception e) {
			// Log.e("err2........",""+e.toString());
			// Handle or log or ignore
		} finally {
			progressdlog = null;
		}
	}
	private void getforgotpassword() {

			if (progressdlog != null && progressdlog.isShowing()) {

			} else {
				progressdlog = new ProgressDialog(context,
						AlertDialog.THEME_HOLO_LIGHT);
				progressdlog.setMessage("Please wait...");
				progressdlog.setCancelable(false);
				progressdlog.show();
			}
			api = PickServiceGenerator.createService(Pick_api.class, context);
			Call<Forgot_model> call = api.getForgotpassword(edtPin.getText().toString().trim());

			call.enqueue(new Callback<Forgot_model>() {
				@Override
				public void onResponse(Call<Forgot_model> call, Response<Forgot_model> response) {
					Log.e(" Responsev"," "+response.toString());
					Log.e(" Responsesskk"," "+String.valueOf(response.code()));
					if(response.isSuccessful()) {

						cancelprogresssdialog();
						//  Log.e(" Responsecqevv","z "+response.body());
						if (response.body() != null) {
							Toast.makeText(LoginActivity.this, "Please Check your Registered Email-Id", Toast.LENGTH_SHORT).show();
							//if(response.body().status.contentEquals("1")) {
//								JSONArray detarray = null;
//								try {
//									detarray = new JSONArray(response.body().child_det);
//								} catch (JSONException e) {
//									e.printStackTrace();
//								}
//
//								//Log.e("resultdet", "detarray"+detarray.toString());
//								arrdetails = new ArrayList<String>();
//								for (int id = 0; id < detarray.length(); id++) {
//									JSONObject det = null;
//									try {
//										det = detarray.getJSONObject(id);
//
//									//Log.e("detdet", "det"+det.toString());
//
//									if (det != null) {
//										if (det.length() > 0) {
//
//											//for (int j = 0; j < det.length(); j++) {
//											if(det.has("pin"))
//											{
//												oldpin=det.getString("pin");
//											}else if(det.has("guardian_name"))
//											{
//												oldpin=det.getString("guardian_name");
//											}
//
//
//											if(det.has("childname"))
//											{
//												chdname=det.getString("childname");
//											}else
//											{
//												chdname="-";
//											}
//
//											arrdetails.add(oldpin+">>"+chdname);
//											//}
//										}
//									}
//									} catch (JSONException e) {
//										e.printStackTrace();
//									}
//								}
						//	}
						}
					}
				}

				@Override
				public void onFailure(Call<Forgot_model> call, Throwable t) {
					Log.e("tttt", " Response Error " + t.getMessage());
					cancelprogresssdialog();
					Toast.makeText(LoginActivity.this, "Please Check your Registered Email-Id", Toast.LENGTH_SHORT).show();

					//Toast.makeText(context, "Login credential wrong please try again ", Toast.LENGTH_SHORT).show();
				}
			});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	public boolean isConnected() {

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





	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	public void myMethod(View view) {
		// TODO Auto-generated method stub
		// Log.e("pin", "Pin clicked");
		View view1 = View.inflate(context, layout.forgot_password, null);
		final EditText edtSMS = (EditText) view1.findViewById(R.id.edtsms);

		Button positive = (Button) view1.findViewById(R.id.positive);
		Button negative = (Button) view1.findViewById(R.id.negative);
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view1);
		dialog.setCancelable(false);
		dialog.show();

		positive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				strMobile = edtSMS.getText().toString().trim();
				if (strMobile != null && strMobile.length() > 0) {





getforgotpassword();


				} else {
					Toast.makeText(context, "Please enter E-mail ID",
							Toast.LENGTH_SHORT).show();
				}
				}
		});
		negative.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

	}

	TextWatcher tst = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

			String ab = edtPin.getText().toString().trim();
			String msg = null;
			if (ab != null && ab.length() > 0) {
				msg = null;
			}
			edtPin.setError(msg);

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};

	public int getsize() {
		int ik = 2;
		int screenSize = getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;

		switch (screenSize) {
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			ik = 1;
			break;
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			ik = 2;
			break;
		case Configuration.SCREENLAYOUT_SIZE_SMALL:

			ik = 3;
			break;
		default:
			ik = 2;
		}

		return ik;
	}




//	public void getScrollMaxAmount() {
//		int actualWidth = (horizontalOuterLayout.getMeasuredWidth() - 512);
//		scrollMax = actualWidth;
//	}
//
//	public void startAutoScrolling() {
//		//Log.e("scrollTimer", "" + scrollTimer);
//		if (scrollTimer == null) {
//			scrollTimer = new Timer();
//			final Runnable Timer_Tick = new Runnable() {
//				public void run() {
//					moveScrollView();
//				}
//			};
//
//			if (scrollerSchedule != null) {
//				scrollerSchedule.cancel();
//				scrollerSchedule = null;
//			}
//			scrollerSchedule = new TimerTask() {
//				@Override
//				public void run() {
//					runOnUiThread(Timer_Tick);
//				}
//			};
//
//			scrollTimer.schedule(scrollerSchedule, 40, 20);
//
//		}
//		if (ij == 6) {
//			// ij=0;
//			addImagesToView();
//		}
//	}

//	public void moveScrollView() {
//		scrollPos = (int) (horizontalScrollview.getScrollX() + 1.0);
//
//		if (scrollPos >= scrollMax) {
//			scrollPos = 0;
//
//		}
//		// Log.e("scrollPos", ""+scrollPos);
//		horizontalScrollview.scrollTo(scrollPos, 0);
//
//	}

	/** Adds the images to view. */
//	public void addImagesToView() {
//		//scrolling
//		//Log.e("i-imageNameArray", "" + imageNameArray.toString());
//		for (int i = 0; i < imageNameArray.length; i++) {
//			ij++;
//			final Button imageButton = new Button(this);
//			int imageResourceId = getResources().getIdentifier(
//					imageNameArray[i], "drawable", getPackageName());
//
//
//			imageButton.setBackgroundColor(Color.parseColor("#4CB8FB"));
//			Drawable image = this.getResources().getDrawable(imageResourceId);
//			imageButton.setBackgroundDrawable(image);
//
//			final int sdk = android.os.Build.VERSION.SDK_INT;
//			if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//				imageButton.setBackgroundDrawable(image);
//			} else {
//				imageButton.setBackground(image);
//			}
//			imageButton.setTag(i);
//			/*
//			 * LinearLayout.LayoutParams params = new
//			 * LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
//			 * LayoutParams.WRAP_CONTENT); imageButton.setLayoutParams(params);
//			 */
//			imageButton.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if (isFaceDown) {
//						if (clickTimer != null) {
//							clickTimer.cancel();
//							clickTimer = null;
//						}
//						clickedButton = (Button) arg0;
//						stopAutoScrolling();
//						clickedButton.startAnimation(scaleFaceUpAnimation());
//						clickedButton.setSelected(true);
//						clickTimer = new Timer();
//
//						if (clickSchedule != null) {
//							clickSchedule.cancel();
//							clickSchedule = null;
//						}
//
//						clickSchedule = new TimerTask() {
//							public void run() {
//								startAutoScrolling();
//							}
//						};
//
//						clickTimer.schedule(clickSchedule, 1500);
//					}
//				}
//			});
//
//			/*
//			 * LinearLayout.LayoutParams params = new
//			 * LinearLayout.LayoutParams(256,256); params.setMargins(0, 25, 0,
//			 * 25);
//			 */
//			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//			params.leftMargin = 25;
//			params.rightMargin=25;
//
//
//			imageButton.setLayoutParams(params);
//			horizontalOuterLayout.addView(imageButton);
//
//			//Log.e("i-val", "" + ij);
//
//		}
//
//	}

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
							Uri.parse("http://www.usaprintings.com/"));
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

	public void onBackPressed() {
		super.onBackPressed();
		finish();
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
	public void openWebPage(String url) {
	    Uri webpage = Uri.parse(url);
	    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
	    if (intent.resolveActivity(getPackageManager()) != null) {
	        startActivity(intent);
	    }
	}


	private void insertDummyContactWrapper() {
		List<String> permissionsNeeded = new ArrayList<>();

		final List<String> permissionsList = new ArrayList<String>();
		if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
			permissionsNeeded.add("GPS");
		if (!addPermission(permissionsList, Manifest.permission.CAMERA))
			permissionsNeeded.add("Read Contacts");
		if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
			permissionsNeeded.add("Write Contacts");
//		if (!addPermission(permissionsList, Manifest.permission.READ_PHONE_STATE))
//			permissionsNeeded.add("Read phone state");
//		if (!addPermission(permissionsList, Manifest.permission.SEND_SMS))
//			permissionsNeeded.add("Send sms");
//		if (!addPermission(permissionsList, Manifest.permission.RECORD_AUDIO))
//			permissionsNeeded.add(Manifest.permission.RECORD_AUDIO);
		if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
			permissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);

		if (permissionsList.size() > 0) {
			if (permissionsNeeded.size() > 0) {
				// Need Rationale
				String message = "You need to grant access to " + permissionsNeeded.get(0);
				for (int i = 1; i < permissionsNeeded.size(); i++)
					message = message + ", " + permissionsNeeded.get(i);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
							REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
				}
				return;
			}
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
						REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
			}
			return;
		}

	}
	private boolean addPermission(List<String> permissionsList, String permission) {
		boolean bool=false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
				permissionsList.add(permission);
				// Check for Rationale Option
				if (!shouldShowRequestPermissionRationale(permission))
					bool= false;
			}
			bool=true;
		}
		return bool;
	}
}
