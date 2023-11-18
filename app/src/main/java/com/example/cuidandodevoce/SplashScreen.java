package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    Objects.requireNonNull(getSupportActionBar()).hide();

    new Handler().postDelayed(() -> {
      startActivity(new Intent(getBaseContext(), MainActivity.class));
      finish();
    }, 1000);
  }
}