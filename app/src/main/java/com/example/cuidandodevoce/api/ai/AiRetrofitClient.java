package com.example.cuidandodevoce.api.ai;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AiRetrofitClient {
  public static Retrofit retrofit;

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl("https://gemini-apis.vercel.app/")
          .addConverterFactory(ScalarsConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}