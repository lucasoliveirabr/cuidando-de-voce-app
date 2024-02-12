package com.example.cuidandodevoce;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AiFragment extends Fragment {

  public AiFragment() {
    // Required empty public constructor
  }

  //private static final String TAG = "AiFragment";

  //@SuppressLint("SetTextI18n")
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View V = inflater.inflate(R.layout.fragment_ai, container, false);

    /*
    AtomicInteger counter = new AtomicInteger(3);
    Button buttonSendAiQuestion = V.findViewById(R.id.buttonSendAiQuestion);
    TextView textViewUseLimit = V.findViewById(R.id.textViewUseLimit);
    TextView textViewRespostaAi = V.findViewById(R.id.textViewRespostaAi);

    buttonSendAiQuestion.setOnClickListener(view -> {
      if (counter.intValue() != 0) {
        counter.getAndDecrement();
        textViewUseLimit.setText("Usos gratuitos: " + counter + "/3");

        // resposta da IA
        textViewRespostaAi.setText("Olá!\nOlá!\nOlá!\nOlá!\nOlá!\nOlá!\nOlá!\nOlá!\nOlá!");

        textViewRespostaAi.setVisibility(View.VISIBLE);
      } else {
        textViewUseLimit.setText("Limite de usos atingido.\nAdquira um plano para usar a IA de forma ilimitada.");
      }
    });
    */

    return V;
  }
}





/*
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://192.168.1.168:8800/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    PacientesApi pacientesApi = retrofit.create(PacientesApi.class);

    Call<List<PacientesResponse>> call = pacientesApi.getNomeCompleto();
    call.enqueue(new Callback<List<PacientesResponse>>() {
      @Override
      public void onResponse(Call<List<PacientesResponse>> call, Response<List<PacientesResponse>> response) {
        if (response.isSuccessful()) {
          String res = "";
          for(PacientesResponse pacientesResponse : response.body()) {
            res += pacientesResponse.getNomeCompleto() + "\n";
          }
          //PacientesResponse pacientesResponse = (PacientesResponse) response.body();
          if (!res.equals("")) {
            Log.d(TAG, "Nome Completo: " + res);
          }
        } else {
          Log.e(TAG, "Falha na requisição. Código: " + response.code());
        }
      }

      @Override
      public void onFailure(Call<List<PacientesResponse>> call, Throwable t) {
        Log.e(TAG, "Falha na requisição. Erro: " + t.getMessage());
      }
    });
*/