package com.isoft.mypreschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentication extends Activity{
	private EditText edtico;
	private Button btnSubmit;
	private  String PWD="x#reset123";
	Context context;
	SharedPreferences loge;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.authenticate);
	        context=this;
	        //Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
	       // FontManager.markAsIconContainer(findViewById(R.id.tkd), iconFont);
	        edtico=(EditText)findViewById(R.id.name);
	        btnSubmit=(Button)findViewById(R.id.bsubmit);
	        loge=getSharedPreferences("LOGG_STATUS", context.MODE_PRIVATE);
	      
	        String str=loge.getString("REG", "o");
	        //Log.e("str", ""+str);
	        if(str.contentEquals("Registered"))
	        {
	        	Intent inte = new Intent(getApplicationContext(), LoginActivity.class);
				

				startActivity(inte);
				finish();
	        }
	        
	       // edtico.setTypeface(FontManager.getTypeface(Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf")));
	
	 btnSubmit.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			String strpass=edtico.getText().toString().trim();
			if(strpass !=null && strpass.length()>0)
			{
				if(strpass.contentEquals(PWD))
				{
					loge=getSharedPreferences("LOGG_STATUS", context.MODE_PRIVATE);
					Editor edit=loge.edit();
					if(edit !=null)
					{
						edit.clear();
					}
					edit.putString("REG", "Registered");
					edit.commit();
					// Log.e("strr", "llllllll");
					Intent inte = new Intent(getApplicationContext(), LoginActivity.class);
					

					startActivity(inte);
					finish();
				}else
				{
					Toast.makeText(context, "Enter valid password", Toast.LENGTH_SHORT).show();
				}
				
			}else
			{
				Toast.makeText(context, "Please enter the password", Toast.LENGTH_SHORT).show();
			}
			
		}
	});
	 
	 }
}
