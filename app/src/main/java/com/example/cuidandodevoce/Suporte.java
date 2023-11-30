package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Suporte extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_suporte);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    Button buttonEnviarSuporte = (Button) findViewById(R.id.buttonEnviarSuporte);

    Spinner spinnerSuporte = findViewById(R.id.spinnerSuporte);
    String[] tiposSuporte = {"Suporte", "Sugestão", "Feedback"};
    spinnerSuporte.setAdapter(new ArrayAdapter<>(
        getApplicationContext(),
        com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
        tiposSuporte
    ));

    buttonEnviarSuporte.setOnClickListener(view -> {
      if (true /*código de verificação*/) {
        Intent i = new Intent(Suporte.this, Inicio.class);
        startActivity(i);
        finish();

        Toast toast = Toast.makeText(Suporte.this, "Mensagem enviada.\nRetornaremos em breve.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_success);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      } else {
        Toast toast = Toast.makeText(Suporte.this, "Ocorreu um erro.\nTente novamente mais tarde.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_error);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      }
    });

  }
}