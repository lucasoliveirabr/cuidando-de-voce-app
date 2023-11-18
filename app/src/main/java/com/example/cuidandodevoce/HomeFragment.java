package com.example.cuidandodevoce;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;
public class HomeFragment extends Fragment {

  public HomeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View V = inflater.inflate(R.layout.fragment_home, container, false);

    TextView dicas = V.findViewById(R.id.textViewDicasTopo);
    String[] frases = {
        "Mantenha uma alimentação saudável e equilibrada.",
        "Pratique exercícios físicos regularmente.",
        "Consulte um médico regularmente.",
        "Use protetor solar em dias muito quentes ou ao ir à praia.",
        "Durma 8 horas por dia para um sono saudável.",
        "Beba água frequentemente. A média recomendada é de 2 litros por dia.",
        "Não fume, preserve o seu pulmão e o de quem está próximo de você.",
        "Diminua o estresse. Respire fundo e relaxe."
    };

    int n = new Random().nextInt(frases.length);
    dicas.setText(frases[n]);

    View viewAgendarConsultas = V.findViewById(R.id.viewAgendarConsultas);
    viewAgendarConsultas.setOnClickListener(view -> {
      Intent i = new Intent(getActivity(), AgendarConsulta.class);
      startActivity(i);
    });

    return V;
  }

}