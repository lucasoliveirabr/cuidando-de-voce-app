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

    TextView usuario = findViewById(R.id.editTextUsuario);
    TextView senha = findViewById(R.id.editTextSenha);
    Button botaoLogin = findViewById(R.id.buttonEntrar);
    TextView cadastroPaciente = findViewById(R.id.textViewCadastresePaciente);
    TextView cadastroMedico = findViewById(R.id.textViewCadastreseMedico);

    botaoLogin.setOnClickListener(view -> {
      if (usuario.getText().toString().equals("") && senha.getText().toString().equals("")) {
        Intent i = new Intent(MainActivity.this, Inicio.class);
        startActivity(i);
        finish();
      } else {
        Toast.makeText(MainActivity.this, "Dados incorretos.\nTente novamente.", Toast.LENGTH_SHORT).show();
      }
    });

    cadastroPaciente.setOnClickListener(view -> {
      Intent i = new Intent(MainActivity.this, CadastroPaciente.class);
      startActivity(i);
    });

    cadastroMedico.setOnClickListener(view -> {
      Intent i = new Intent(MainActivity.this, CadastroMedico.class);
      startActivity(i);
    });
  }
}