package com.isoft.mypreschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Thank_you extends Activity{
	Context context;
	Intent inte;
	String path=null;
	ImageView ik;
	Bitmap mIcon_val;
	
	
	
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
//	private String[] imageNameArray = { "usa", "wildwood", "logo_large332",
//			"usa", "wildwood", "logo_large332" };
//	private String[] nameArray = { "usa", "wildwood", "logo_large332", "usa",
//			"wildwood", "logo_large332" };
	private int ij = 0;

	private static ArrayList<String> arrdetails;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thank);
		context = this;
		inte=getIntent();
		if(inte !=null)
		{
		path=inte.getStringExtra("img");
		Log.e("path", "p"+path);
		}else
		{
			path="http://pickmychild.org/GM/sponc/space.jpg";
		}
		ik=(ImageView)findViewById(R.id.imgk);
		
//		horizontalScrollview = (HorizontalScrollView) findViewById(R.id.horiztonal_scrollview_id);
//		horizontalOuterLayout = (LinearLayout) findViewById(R.id.horiztonal_outer_layout_id);
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

		
		 
		///////////////////////////////////////////////// 
		 
		 Thread th = new Thread() {
				public void run() {
					try {

						sleep(6000);
						

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						
							Intent mIntent = new Intent(Thank_you.this,
									LoginActivity.class);
							startActivity(mIntent);
					
						finish();

					}
				}
			};
			th.start(); 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 

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
//
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
//
//	/** Adds the images to view. */
//	public void addImagesToView() {
//		//Log.e("i-imageNameArray", "" + imageNameArray.toString());
//		for (int i = 0; i < imageNameArray.length; i++) {
//			ij++;
//			final Button imageButton = new Button(this);
//			int imageResourceId = getResources().getIdentifier(
//					imageNameArray[i], "drawable", getPackageName());
//
//			Drawable image = this.getResources().getDrawable(imageResourceId);
//			imageButton.setBackgroundDrawable(image);
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
//			imageButton.setLayoutParams(params);
//			horizontalOuterLayout.addView(imageButton);
//
//			//Log.e("i-val", "" + ij);
//
//		}
//
//	}

//	public Animation scaleFaceUpAnimation() {
//		Animation scaleFace = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		scaleFace.setDuration(500);
//		scaleFace.setFillAfter(true);
//		scaleFace.setInterpolator(new AccelerateInterpolator());
//		Animation.AnimationListener scaleFaceAnimationListener = new Animation.AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation arg0) {
//
//				String type = nameArray[(Integer) clickedButton.getTag()];
//				if (type.contentEquals("usa")) {
//					Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//							Uri.parse("http://usaprintings.com/"));
//					startActivity(browserIntent);
//				} else if (type.contentEquals("wildwood")) {
//					Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//							Uri.parse("http://www.wildwoodex.com/"));
//					startActivity(browserIntent);
//				} else {
//					Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//							Uri.parse("http://trucksoft.net/"));
//					startActivity(browserIntent);
//
//				}
//
//				isFaceDown = false;
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation arg0) {
//			}
//
//			@Override
//			public void onAnimationEnd(Animation arg0) {
//				if (faceTimer != null) {
//					faceTimer.cancel();
//					faceTimer = null;
//				}
//
//				faceTimer = new Timer();
//				if (faceAnimationSchedule != null) {
//					faceAnimationSchedule.cancel();
//					faceAnimationSchedule = null;
//				}
//				faceAnimationSchedule = new TimerTask() {
//					@Override
//					public void run() {
//						faceScaleHandler.sendEmptyMessage(0);
//					}
//				};
//
//				faceTimer.schedule(faceAnimationSchedule, 750);
//			}
//		};
//		scaleFace.setAnimationListener(scaleFaceAnimationListener);
//		return scaleFace;
//	}

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
	}
*/
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

}
