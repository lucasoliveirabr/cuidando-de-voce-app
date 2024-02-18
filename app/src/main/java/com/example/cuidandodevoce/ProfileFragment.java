package com.example.cuidandodevoce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ProfileFragment extends Fragment {

  public ProfileFragment() {
    // Required empty public constructor
  }

  private GoogleSignInClient mGoogleSignInClient;

  public void successfulRevoke() {
    Toast toast = Toast.makeText(getContext(), "Desconectado com sucesso.", Toast.LENGTH_SHORT);
    toast.getView().setBackgroundResource(R.drawable.toast_border_success);
    TextView text = toast.getView().findViewById(android.R.id.message);
    text.setTextColor(Color.parseColor("#000000"));
    text.setGravity(Gravity.CENTER);
    toast.show();
  }

  public void errorRevoke() {
    Toast toast = Toast.makeText(getContext(), "Erro na revogação de acesso.", Toast.LENGTH_LONG);
    toast.getView().setBackgroundResource(R.drawable.toast_border_error);
    TextView text = toast.getView().findViewById(android.R.id.message);
    text.setTextColor(Color.parseColor("#000000"));
    text.setGravity(Gravity.CENTER);
    toast.show();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View V = inflater.inflate(R.layout.fragment_profile, container, false);

    ImageView imageViewProfilePhoto = V.findViewById(R.id.imageViewProfilePhoto);
    TextView textViewUserName = V.findViewById(R.id.textViewUserName);
    TextView textViewUserEmail = V.findViewById(R.id.textViewUserEmail);
    TextView textViewGoogleId = V.findViewById(R.id.textViewGoogleId);
    Button buttonExit = V.findViewById(R.id.buttonExit);



    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

    mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());

    if (account != null) {
      String personName = account.getDisplayName();
      String personEmail = account.getEmail();
      Uri personPhoto = account.getPhotoUrl();
      String personId = account.getId();
      //String personGivenName = account.getGivenName();
      //String personFamilyName = account.getFamilyName();

      Picasso.get()
          .load(personPhoto)
          .transform(new CropCircleTransformation())
          .into(imageViewProfilePhoto);
      textViewUserName.setText(personName);
      textViewUserEmail.setText(personEmail);
      textViewGoogleId.setVisibility(View.VISIBLE);
      textViewGoogleId.setText("Google ID: " + personId);
    }



    buttonExit.setOnClickListener(view -> {
      //signOut();
      //revokeAccess();
      logout();
    });

    return V;
  }

  /*
  private void signOut() {
    mGoogleSignInClient.signOut().addOnCompleteListener((Activity) getContext(), task -> {
      if (task.isSuccessful()) {
        // Logout bem-sucedido, você pode realizar ações adicionais aqui
        // Exemplo: Navegar para a tela de login ou atualizar a interface do usuário

        // Por exemplo, navegar para a tela de login:
        // Intent loginIntent = new Intent(YourCurrentActivity.this, LoginActivity.class);
        // startActivity(loginIntent);

        // Ou, se estiver usando um fragmento:
        // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.replace(R.id.fragment_container, new LoginFragment());
        // transaction.commit();

        Toast.makeText(getContext(), "Sucesso no logout", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(getContext(), "Falha no logout", Toast.LENGTH_SHORT).show();
      }
    });
  }
  */

  private void revokeAccess() {
    mGoogleSignInClient.revokeAccess().addOnCompleteListener((Activity) getContext(), task -> {
      if (task.isSuccessful()) {

        successfulRevoke();

        // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.replace(R.id.fragment_container, new LoginFragment());
        // transaction.commit();
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        getActivity().finish();
      } else {
        errorRevoke();
      }
    });
  }

  private void logout() {
    // Lógica de logout específica do aplicativo, como limpar dados locais, redefinir estado, etc.
    // ...

    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
    if (account != null) {
      revokeAccess();
    }

    successfulRevoke();

    Intent i = new Intent(getContext(), MainActivity.class);
    startActivity(i);
    getActivity().finish();
  }

}