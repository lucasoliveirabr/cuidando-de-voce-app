package com.example.cuidandodevoce;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.example.cuidandodevoce.api.ai.AiApi;
import com.example.cuidandodevoce.api.ai.AiRetrofitClient;

import java.io.IOException;
import java.util.Objects;

import io.noties.markwon.Markwon;
import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ai extends AppCompatActivity {

  private static final String TAG = "Ai";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ai);

    Objects.requireNonNull(getSupportActionBar()).hide();

    getWindow().setStatusBarColor(getResources().getColor(R.color.soft_pink));

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Aviso");
    builder.setMessage("A \"Doutora Estela\" é uma inteligência artificial baseada no modelo de IA Gemini, fornecido pela empresa Google. Por conta disso, é possível que ela gere respostas imprecisas.");
    builder.setPositiveButton("OK", null);
    AlertDialog alertDialog = builder.create();

    ImageView imageViewInfoAi = findViewById(R.id.imageViewInfoAi);
    imageViewInfoAi.setOnClickListener(view -> alertDialog.show());

    TextView textViewAiQuestion = findViewById(R.id.textViewAiQuestion);
    TextView textViewAiAnswer = findViewById(R.id.textViewAiAnswer);
    EditText editTextAiUserField = findViewById(R.id.editTextAiUserField);
    ImageButton imageButtonSendAi = findViewById(R.id.imageButtonSendAi);

    editTextAiUserField.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty()) {
          imageButtonSendAi.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(Ai.this, R.color.beige_gray)));
        } else {
          imageButtonSendAi.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(Ai.this, R.color.red)));
        }
      }
    });

    imageButtonSendAi.setOnClickListener(view -> {
      if (editTextAiUserField.getText().toString().isEmpty()) {
        Toast toast = Toast.makeText(this, "A mensagem não pode ser tão curta.", Toast.LENGTH_SHORT);
        view = toast.getView();
        view.setBackgroundResource(R.drawable.toast_border_warning);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
      } else {
        editTextAiUserField.setEnabled(false);
        editTextAiUserField.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(Ai.this, R.color.beige_gray)));

        textViewAiQuestion.setText(editTextAiUserField.getText());

        AiApi aiApi = AiRetrofitClient.getRetrofitInstance().create(AiApi.class);

        String fullMessage = "Interprete a personagem \"Doutora Estela\", uma IA que assume o papel de médica para auxiliar seu paciente, mas apenas responda as dúvidas de saúde que tiver:\n" + editTextAiUserField.getText();
        Log.d(TAG, fullMessage);
        Call<String> call = aiApi.getPrompt(fullMessage);

        Request request = call.request();
        Log.d(TAG, "==========");
        Log.d(TAG, "Request to the API: " + request);
        Log.d(TAG, "==========");

        editTextAiUserField.setText("");
        textViewAiQuestion.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<String>() {
          @Override
          public void onResponse(Call<String> call, Response<String> response) {
            Log.i(TAG, "Conexão feita com a API.");

            if (response.isSuccessful()) {
              Log.i(TAG, "Sucesso no envio de dados à API.");
              Log.d(TAG, "==========");
              Log.d(TAG, "API response: " + response.body());
              Log.d(TAG, "==========");

              Markwon markwon = Markwon.create(Ai.this);
              markwon.setMarkdown(textViewAiAnswer, response.body());
              textViewAiAnswer.setVisibility(View.VISIBLE);
              editTextAiUserField.setEnabled(true);
              editTextAiUserField.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(Ai.this, R.color.white)));
            } else {
              Log.e(TAG, "Falha na requisição. Código: " + response.code());
              Log.e(TAG, "Erro da API: " + response.errorBody().toString());
              editTextAiUserField.setText("");
              textViewAiAnswer.setVisibility(View.VISIBLE);
              textViewAiAnswer.setText("Falha na requisição.\nTente novamente mais tarde.");
              textViewAiAnswer.setTextColor(getResources().getColor(R.color.pink));
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            Log.e(TAG, "Falha na requisição. Erro: " + t.getMessage());
            editTextAiUserField.setText("");
            textViewAiAnswer.setVisibility(View.VISIBLE);
            textViewAiAnswer.setText("Falha na requisição. Tente novamente mais tarde.");
          }
        });
      }
    });

  }
}