package com.example.cuidandodevoce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuidandodevoce.api.CadastroApi;
import com.example.cuidandodevoce.api.CadastroRequest;
import com.example.cuidandodevoce.api.RetrofitClient;
import com.example.cuidandodevoce.api.SuportesApi;
import com.example.cuidandodevoce.api.SuportesRequest;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroPaciente extends AppCompatActivity {

  private static final String TAG = "CadastroPaciente";

  public void successfulToast() {
    Toast toast = Toast.makeText(this, "Conta criada com sucesso.", Toast.LENGTH_SHORT);
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro_paciente);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    EditText editTextFullName = findViewById(R.id.editTextFullName);
    EditText editTextBirthDate = findViewById(R.id.editTextBirthDate);
    EditText editTextCPF = findViewById(R.id.editTextCPF);
    EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);
    EditText editTextEmail = findViewById(R.id.editTextEmail);
    EditText editTextSenha = findViewById(R.id.editTextSenha);
    Button buttonFinishRegistration = findViewById(R.id.buttonFinishRegistration);

    /*
    buttonFinishRegistration.setOnClickListener(view -> {
      if (editTextFullName.getText().toString().length() <= 3) {
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
      }





      CadastroApi cadastroApi = RetrofitClient.getRetrofitInstance().create(CadastroApi.class);

      CadastroRequest cadastroRequest = new CadastroRequest();
      cadastroRequest.setFullName(String.valueOf(editTextFullName.getText()));
      cadastroRequest.setBirthDate(String.valueOf(editTextBirthDate.getText()));
      cadastroRequest.setCpf(String.valueOf(editTextCPF.getText()));
      cadastroRequest.setContactNumber(String.valueOf(editTextContactNumber.getText()));
      cadastroRequest.setEmail(String.valueOf(editTextEmail.getText()));
      cadastroRequest.setSenha(String.valueOf(editTextSenha.getText()));

      Call<CadastroRequest> call = cadastroApi.addPaciente(cadastroRequest);

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

      call.enqueue(new Callback<CadastroRequest>() {
        @Override
        public void onResponse(Call<CadastroRequest> call, Response<CadastroRequest> response) {
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
        public void onFailure(Call<CadastroRequest> call, Throwable t) {
          Log.e(TAG, "Falha na requisição. Erro: " + t.getMessage());
          errorToast();
        }
      });
    });
    */
  }
}