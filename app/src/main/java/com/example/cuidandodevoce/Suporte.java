package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuidandodevoce.api.RetrofitClient;
import com.example.cuidandodevoce.api.SuportesApi;
import com.example.cuidandodevoce.api.SuportesRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Suporte extends AppCompatActivity {

  private static final String TAG = "Suporte";

  public void successfulToast() {
    Toast toast = Toast.makeText(this, "Mensagem enviada.\nRetornaremos em breve.", Toast.LENGTH_SHORT);
    toast.getView().setBackgroundResource(R.drawable.toast_border_success);
    TextView text = toast.getView().findViewById(android.R.id.message);
    text.setTextColor(Color.parseColor("#000000"));
    text.setGravity(Gravity.CENTER);
    toast.show();
  }

  public void errorToast() {
    Toast toast = Toast.makeText(this, "O sistema está indisponível no momento.\nLogo retornaremos.\nTente novamente mais tarde.", Toast.LENGTH_LONG);
    toast.getView().setBackgroundResource(R.drawable.toast_border_error);
    TextView text = toast.getView().findViewById(android.R.id.message);
    text.setTextColor(Color.parseColor("#000000"));
    text.setGravity(Gravity.CENTER);
    toast.show();
  }

  public static String getCurrentTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault());
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    return sdf.format(new Date());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_suporte);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    Spinner spinnerSuporte = findViewById(R.id.spinnerSuporte);
    EditText editTextCampoSuporte = findViewById(R.id.editTextCampoSuporte);
    Button buttonEnviarSuporte = findViewById(R.id.buttonEnviarSuporte);

    String[] tiposSuporte = {"Suporte", "Sugestão", "Feedback"};
    spinnerSuporte.setAdapter(new ArrayAdapter<>(
        this,
        com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
        tiposSuporte
    ));

    buttonEnviarSuporte.setOnClickListener(view -> {
      if (editTextCampoSuporte.getText().toString().length() <= 3) {
        Toast toast = Toast.makeText(this, "A mensagem não pode ser tão curta.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_warning);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      } else {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();


        SuportesApi suportesApi = RetrofitClient.getRetrofitInstance().create(SuportesApi.class);

        SuportesRequest suportesRequest = new SuportesRequest();
        suportesRequest.setTipoSuporte((String) spinnerSuporte.getSelectedItem());
        suportesRequest.setTimestamp(getCurrentTimestamp());
        suportesRequest.setMensagemUsuario(String.valueOf(editTextCampoSuporte.getText()));

        Call<SuportesRequest> call = suportesApi.addSupportChat(suportesRequest);

        Request request = call.request();
        Buffer buffer = new Buffer();
        try {
          request.body().writeTo(buffer);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        Log.d(TAG, "==========");
        Log.d(TAG, "Request to the API: " + request);
        Log.d(TAG, "JSON sent to the API: " + buffer.readUtf8());
        Log.d(TAG, "==========");

        call.enqueue(new Callback<SuportesRequest>() {
          @Override
          public void onResponse(Call<SuportesRequest> call, Response<SuportesRequest> response) {
            Log.i(TAG, "Conexão feita com a API.");

            if (response.isSuccessful()) {
              Log.i(TAG, "Sucesso no envio de dados à API.");
              successfulToast();
            } else {
              Log.e(TAG, "Falha na requisição. Código: " + response.code());
              Log.e(TAG, "Erro da API: " + response.errorBody().toString());
              errorToast();
            }
          }

          @Override
          public void onFailure(Call<SuportesRequest> call, Throwable t) {
            Log.e(TAG, "Falha na requisição. Erro: " + t.getMessage());
            errorToast();
          }
        });
      }
    });

  }
}