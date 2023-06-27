package com.si61.biliarpalembang.API;

import com.si61.biliarpalembang.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve ();
}
