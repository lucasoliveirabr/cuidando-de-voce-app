package com.example.cuidandodevoce;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    Objects.requireNonNull(getSupportActionBar()).hide();



    Context context = getApplicationContext();
    try {
      PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
      String versionName = pInfo.versionName;

      TextView textView = findViewById(R.id.textViewVersionSplashScreen);
      textView.setText("Versão: " + versionName);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    new Handler().postDelayed(() -> {
      startActivity(new Intent(getBaseContext(), MainActivity.class));
      finish();
    }, 1000);
  }
}