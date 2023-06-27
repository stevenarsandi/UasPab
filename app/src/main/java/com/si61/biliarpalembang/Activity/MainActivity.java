package com.si61.biliarpalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.si61.biliarpalembang.API.RetroServer;
import com.si61.biliarpalembang.API.APIRequestData;
import com.si61.biliarpalembang.Adapter.AdapterBiliar;
import com.si61.biliarpalembang.Model.ModelBiliar;
import com.si61.biliarpalembang.Model.ModelResponse;
import com.si61.biliarpalembang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvBiliar;
    private ProgressBar pbBiliar;
    private FloatingActionButton fabBiliar;
    private RecyclerView.Adapter adBiliar;
    private RecyclerView.LayoutManager lmBiliar;

    private List<ModelBiliar> listBiliar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBiliar = findViewById(R.id.rv_biliar);
        pbBiliar = findViewById(R.id.pb_biliar);
        fabBiliar = findViewById(R.id.fab_tambah);

        lmBiliar = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvBiliar.setLayoutManager(lmBiliar);

        fabBiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TambahActivity.class));
            }
        });
    }
    public void retrieveBiliar(){
        pbBiliar.setVisibility(View.VISIBLE);

        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listBiliar = response.body().getData();

                adBiliar =new AdapterBiliar(MainActivity.this,listBiliar);
                rvBiliar.setAdapter(adBiliar);
                adBiliar.notifyDataSetChanged();

                pbBiliar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error : Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbBiliar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveBiliar();
    }
}