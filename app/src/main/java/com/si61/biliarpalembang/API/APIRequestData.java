package com.si61.biliarpalembang.API;

import com.si61.biliarpalembang.Model.ModelResponse;
import com.si61.biliarpalembang.R;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve ();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreate(
            @Field("nama") String nama,
            @Field("lokasi") String lokasi,
            @Field("urlmap") String urlmap,
            @Field("jam") String jam,
            @Field("nohp") String nohp
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdate(
            @Field("id") String id,
            @Field("nama") String nama,
            @Field("lokasi") String lokasi,
            @Field("urlmap") String urlmap,
            @Field("jam") String jam,
            @Field("nohp") String nohp
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDelete(
            @Field("id") String id
    );
}
