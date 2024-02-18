package com.example.cuidandodevoce;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  private static final int RC_SIGN_IN = 123;

  private GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Objects.requireNonNull(getSupportActionBar()).hide();
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    TextView editTextUserEmail = findViewById(R.id.editTextUserEmail);
    TextView editTextUserPassword = findViewById(R.id.editTextUserPassword);
    Button buttonEnter = findViewById(R.id.buttonEnter);
    TextView textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
    Button sign_in_button = findViewById(R.id.sign_in_button);
    TextView textViewRegisterPatient = findViewById(R.id.textViewRegisterPatient);
    TextView textViewRegisterDoc = findViewById(R.id.textViewRegisterDoc);


    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    sign_in_button.setOnClickListener(view -> getResult.launch(mGoogleSignInClient.getSignInIntent()));


    buttonEnter.setOnClickListener(view -> {
      if (editTextUserEmail.getText().toString().equals("") && editTextUserPassword.getText().toString().equals("")) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
      } else {
        Toast.makeText(this, "Dados incorretos.\nTente novamente.", Toast.LENGTH_SHORT).show();
      }
    });

    textViewForgotPassword.setOnClickListener(view -> {
      Intent i = new Intent(this, ForgotPassword.class);
      startActivity(i);
    });

    textViewRegisterPatient.setOnClickListener(view -> {
      Intent i = new Intent(this, CadastroPaciente.class);
      startActivity(i);
    });

    textViewRegisterDoc.setOnClickListener(view -> {
      Intent i = new Intent(this, CadastroMedico.class);
      startActivity(i);
    });
  }

  @Override
  protected void onStart() {
    super.onStart();

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      Intent i = new Intent(this, Inicio.class);
      startActivity(i);
      finish();
    }
  }

  private final ActivityResultLauncher<Intent> getResult = registerForActivityResult(
      new ActivityResultContracts.StartActivityForResult(),
      result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
          Intent data = result.getData();
          if (data != null) {
            Intent i = new Intent(this, Inicio.class);
            startActivity(i);
            finish();
          }
        }
      }
  );

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
      handleSignInResult(task);
    }
  }

  private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
      GoogleSignInAccount account = completedTask.getResult(ApiException.class);
      if (account != null) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
      }
    } catch (ApiException e) {
      Log.w("Login", "signInResult:failed code=" + e.getStatusCode());

      GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
      if (account != null) {
        Intent i = new Intent(this, Inicio.class);
        startActivity(i);
        finish();
      }
    }
  }

}