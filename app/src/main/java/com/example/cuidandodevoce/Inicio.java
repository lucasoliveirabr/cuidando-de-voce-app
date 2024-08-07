package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Inicio extends AppCompatActivity {

  private void replaceFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frame_layout, fragment);
    fragmentTransaction.commit();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicio);

    Objects.requireNonNull(getSupportActionBar()).hide();

    BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
    navigationView.setBackground(null);
    replaceFragment(new HomeFragment());

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(v -> {
      Intent i = new Intent(getApplicationContext(), Ai.class);
      startActivity(i);
      overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    });

    navigationView.setOnItemSelectedListener(item -> {
      if (item.getItemId() == R.id.menu) {
        replaceFragment(new HomeFragment());
      } else if (item.getItemId() == R.id.perfil) {
        replaceFragment(new ProfileFragment());
      }

      return true;
    });

  }

}