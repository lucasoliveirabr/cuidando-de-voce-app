package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    TextView editTextUserEmail = findViewById(R.id.editTextUserEmail);
    TextView editTextUserPassword = findViewById(R.id.editTextUserPassword);
    Button buttonEnter = findViewById(R.id.buttonEnter);
    TextView textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
    TextView textViewRegisterPatient = findViewById(R.id.textViewRegisterPatient);
    TextView textViewRegisterDoc = findViewById(R.id.textViewRegisterDoc);

    buttonEnter.setOnClickListener(view -> {
      if (editTextUserEmail.getText().toString().equals("") && editTextUserPassword.getText().toString().equals("")) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
      } else {
        Toast.makeText(this, "Dados incorretos.\nTente novamente.", Toast.LENGTH_SHORT).show();
      }
    });

    textViewForgotPassword.setOnClickListener(view -> {
      Intent i = new Intent(this, ForgotPassword.class);
      startActivity(i);
    });

    textViewRegisterPatient.setOnClickListener(view -> {
      Intent i = new Intent(this, CadastroPaciente.class);
      startActivity(i);
    });

    textViewRegisterDoc.setOnClickListener(view -> {
      Intent i = new Intent(this, CadastroMedico.class);
      startActivity(i);
    });
  }
}