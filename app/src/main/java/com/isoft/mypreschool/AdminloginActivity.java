package com.isoft.mypreschool;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.isoft.mypreschool.api.PickServiceGenerator;
import com.isoft.mypreschool.api.Pick_api;
import com.isoft.mypreschool.modelclass.LoginAdmin_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminloginActivity extends AppCompatActivity {
    private EditText edtemail,edtpassword;
    private TextView btnlogin;
    private Context context;
    Pick_api api;
    ProgressDialog progressdlog;
    Preference pref;

    private ImageView imgview,imgvisiblepw;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        getSupportActionBar().hide();
        context=this;
        pref=Preference.getInstance(context);
        edtemail=findViewById(R.id.edt_email);
        edtpassword=findViewById(R.id.edt_password);
        btnlogin=findViewById(R.id.btn_login);
        imgview=findViewById(R.id.img_pw);
        imgvisiblepw=findViewById(R.id.img_visiblepw);
        if(pref.getString(Constant.REGISTERED) !=null && pref.getString(Constant.REGISTERED).contentEquals("yes"))
        {
            Intent inte = new Intent(getApplicationContext(), LoginActivity.class);


            startActivity(inte);
            finish();
        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlogin();
            }
        });
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                imgvisiblepw.setVisibility(View.VISIBLE);
                imgview.setVisibility(View.GONE);
            }
        });

        imgvisiblepw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgview.setVisibility(View.VISIBLE);
                imgvisiblepw.setVisibility(View.GONE);
            }
        });
    }

    private void getlogin() {
        if (edtemail.getText().toString().trim() != null && edtemail.getText().toString().trim().length() > 0 &&
                edtpassword.getText().toString().trim() != null && edtpassword.getText().toString().trim().length() > 0
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
            Call<LoginAdmin_model> call = api.getadminLogin(edtemail.getText().toString().trim(),edtpassword.getText().toString().trim());

            call.enqueue(new Callback<LoginAdmin_model>() {
                @Override
                public void onResponse(Call<LoginAdmin_model> call, Response<LoginAdmin_model> response) {
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
                                pref.putString(Constant.REGISTERED,"yes");
                                pref.putString(Constant.SCHOOL_NAME,response.body().school_name);
                                pref.putString(Constant.SCHOOL_ID,response.body().id);
                                Intent inte = new Intent(getApplicationContext(), LoginActivity.class);


                                startActivity(inte);
                                finish();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginAdmin_model> call, Throwable t) {
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
}
