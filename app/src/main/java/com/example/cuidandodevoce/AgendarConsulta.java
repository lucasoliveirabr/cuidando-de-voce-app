package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
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
        "Outro"};
    aCTextViewEspecialidade.setAdapter(new ArrayAdapter<>(
        this,
        android.R.layout.select_dialog_item,
        especialidades
    ));

    Spinner spinnerHorario = (Spinner) findViewById(R.id.spinnerHorario);
    String[] horarios = {"13hrs", "14hrs", "15hrs", "16hrs", "17hrs", "18hrs", "19hrs", "20hrs"};
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
        Toast.makeText(AgendarConsulta.this, "Consulta agendada com sucesso.", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(AgendarConsulta.this, "Ocorreu um erro.\nTente novamente.", Toast.LENGTH_SHORT).show();
      }
    });
  }


}