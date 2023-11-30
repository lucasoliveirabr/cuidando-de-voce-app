package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class AgendarConsulta extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agendar_consulta);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    // Button buttonTelemedicina = (Button) findViewById(R.id.buttonTelemedicina);
    AutoCompleteTextView aCTextViewLocal = (AutoCompleteTextView) findViewById(R.id.aCTextViewLocal);
    AutoCompleteTextView aCTextViewEspecialidade = (AutoCompleteTextView) findViewById(R.id.aCTextViewEspecialidade);
    Button buttonConfirmar = (Button) findViewById(R.id.buttonConfirmar);

    /*
    if (buttonTelemedicina.isFocused()) {
      Toast.makeText(AgendarConsulta.this, "Funcionando.", Toast.LENGTH_SHORT).show();
      aCTextViewLocal.setEnabled(false);
    }
    */

    String[] locais = {"São Paulo", "Barueri", "Osasco", "Jandira", "Carapicuíba", "Itapevi"};
    aCTextViewLocal.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.select_dialog_item,
        locais
    ));

    String[] especialidades = {
        "Clínica Médica",
        "Pediatria",
        "Ginecologia e Obstetrícia",
        "Medicina do Trabalho",
        "Ortopedia e Traumatologia",
        "Cardiologia",
        "Oftalmologia",
        "Hepatologia",
        "Neurologia",
        "Outro"};
    aCTextViewEspecialidade.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.select_dialog_item,
        especialidades
    ));

    Spinner spinnerHorario = findViewById(R.id.spinnerHorario);
    String[] horarios = {"13h", "14h", "15h", "16h", "17h", "18h", "19h", "20h"};
    spinnerHorario.setAdapter(new ArrayAdapter<>(
        getApplicationContext(),
        com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
        horarios
    ));

    buttonConfirmar.setOnClickListener(view -> {
      if (true /*código de verificação*/) {
        Intent i = new Intent(AgendarConsulta.this, Inicio.class);
        startActivity(i);
        finish();

        Toast toast = Toast.makeText(AgendarConsulta.this, "Consulta agendada com sucesso.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_success);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      } else {
        Toast toast = Toast.makeText(AgendarConsulta.this, "Ocorreu um erro.\nTente novamente mais tarde.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_error);
        TextView text = (TextView) view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      }
    });
  }


}