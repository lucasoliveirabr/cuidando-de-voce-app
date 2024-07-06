package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

public class AgendarConsulta extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agendar_consulta);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    CalendarView calendarView = findViewById(R.id.calendarView);
    AutoCompleteTextView aCTextViewLocal = findViewById(R.id.aCTextViewLocal);
    AutoCompleteTextView aCTextViewEspecialidade = findViewById(R.id.aCTextViewEspecialidade);
    Button buttonConfirmar = findViewById(R.id.buttonConfirmar);

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

    NTPTimeProvider ntpTimeProvider = new NTPTimeProvider();
    long ntpTime = ntpTimeProvider.getNTPTime();
    System.out.println(ntpTime);
    if (ntpTime == -1) {
      finishAffinity();
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(ntpTime);
    calendarView.setMinDate(ntpTime);
    calendar.add(Calendar.YEAR, 1);
    calendarView.setMaxDate(calendar.getTimeInMillis());

    Spinner spinnerHorario = findViewById(R.id.spinnerHorario);
    String[] horarios = {"13h", "14h", "15h", "16h", "17h", "18h", "19h", "20h"};
    spinnerHorario.setAdapter(new ArrayAdapter<>(
        this,
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
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      } else {
        Toast toast = Toast.makeText(AgendarConsulta.this, "Ocorreu um erro.\nTente novamente mais tarde.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_error);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      }
    });
  }

}