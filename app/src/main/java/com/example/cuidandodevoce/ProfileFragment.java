package com.example.cuidandodevoce;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ProfileFragment extends Fragment {

  public ProfileFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View V = inflater.inflate(R.layout.fragment_profile, container, false);

    Button buttonExit = V.findViewById(R.id.buttonExit);

    buttonExit.setOnClickListener(view -> {
      Intent i = new Intent(getContext(), MainActivity.class);
      startActivity(i);
      getActivity().finish();
    });

    return V;
  }
}