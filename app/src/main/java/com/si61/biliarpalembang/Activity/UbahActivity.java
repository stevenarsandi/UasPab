package com.si61.biliarpalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.si61.biliarpalembang.API.APIRequestData;
import com.si61.biliarpalembang.API.RetroServer;
import com.si61.biliarpalembang.Model.ModelResponse;
import com.si61.biliarpalembang.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {

    private EditText etNama, etLokasi, etUrlmap, etjam, etnohp;
    private Button btnSimpan;
    private String nama, lokasi, urlmap, jam, nohp;
    private String yId, yNama, yLokasi, yUrlmap, yjam, ynohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etLokasi = findViewById(R.id.et_lokasi);
        etUrlmap = findViewById(R.id.et_urlmap);
        etjam = findViewById(R.id.et_jam);
        etnohp = findViewById(R.id.et_nohp);
        btnSimpan = findViewById(R.id.btn_simpan);

        Intent tangkap = getIntent();

        yId = tangkap.getStringExtra("xId");
        yNama = tangkap.getStringExtra("xNama");
        yLokasi = tangkap.getStringExtra("xLokasi");
        yUrlmap = tangkap.getStringExtra("xUrlmap");
        yjam = tangkap.getStringExtra("xJam");
        ynohp = tangkap.getStringExtra("xNoHp");

        etNama.setText(yNama);
        etLokasi.setText(yLokasi);
        etUrlmap.setText(yUrlmap);
        etjam.setText(yjam);
        etnohp.setText(ynohp);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                lokasi = etLokasi.getText().toString();
                urlmap = etUrlmap.getText().toString();
                jam = etjam.getText().toString();
                nohp = etnohp.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama Tempat Biliar harus diisi");
                }
                else if(lokasi.trim().isEmpty()){
                    etLokasi.setError("Lokasi Biliar harus diisi");
                }
                else if(urlmap.trim().isEmpty()){
                    etUrlmap.setError("Urlmap Biliar harus diisi");
                }
                else if(jam.trim().isEmpty()){
                    etjam.setError("Jam Operasional Biliar harus diisi");
                }
                else if(nohp.trim().isEmpty()) {
                    etnohp.setError("No Telepon Biliar harus diisi");
                }
                else{
                    prosesUbah();
                }
            }
        });
    }
    private void prosesUbah(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardUpdate(yId, nama, lokasi, urlmap, jam, nohp);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
