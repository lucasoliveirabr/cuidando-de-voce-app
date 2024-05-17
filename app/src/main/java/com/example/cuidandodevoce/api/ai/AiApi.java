package com.example.cuidandodevoce.api.ai;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AiApi {
  @GET("api")
  Call<String> getPrompt(@Query("prompt") String userPrompt);
}