package com.si61.biliarpalembang.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity{

    private EditText etNama, etLokasi, etUrlmap, etJam, etNoHp;
    private Button btnSimpan;
    private String nama, lokasi, urlmap, jam, nohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etLokasi = findViewById(R.id.et_lokasi);
        etUrlmap = findViewById(R.id.et_urlmap);
        etJam = findViewById(R.id.et_jam);
        etNoHp = findViewById(R.id.et_nohp);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                lokasi = etLokasi.getText().toString();
                urlmap = etUrlmap.getText().toString();
                jam = etJam.getText().toString();
                nohp = etNoHp.getText().toString();

                if (nama.trim().isEmpty()){
                    etNama.setError("Nama hotel harus diisi");
                }
                else if(lokasi.trim().isEmpty()){
                    etLokasi.setError("Alamat hotel harus diisi");
                }
                else if(urlmap.trim().isEmpty()){
                    etUrlmap.setError("Urlmap hotel harus diisi");
                }
                else if(jam.trim().isEmpty()){
                    etJam.setError("Telepon hotel harus diisi");
                }
                else if(nohp.trim().isEmpty()){
                    etNoHp.setError("Bintang hotel harus diisi");
                }
                else{
                    prosesSimpan();
                }
            }
        });
    }

    private void prosesSimpan(){
        APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = API.ardCreate(nama, lokasi, urlmap, jam, nohp);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
