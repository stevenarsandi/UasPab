package com.si61.biliarpalembang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.si61.biliarpalembang.R;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNama1, tvLokasi1, tvUrlmap1, tvJam1, tvNoHp1;
    private String yId1, yNama1, yLokasi1, yUrlmap1, yJam1, yNoHp1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvNama1 = findViewById(R.id.tv_nama1);
        tvLokasi1 = findViewById(R.id.tv_lokasi1);
        tvUrlmap1 = findViewById(R.id.tv_urlmap1);
        tvJam1 = findViewById(R.id.tv_jam1);
        tvNoHp1 = findViewById(R.id.tv_nohp1);

        Intent tangkap = getIntent();

        yId1 = tangkap.getStringExtra("xId1");
        yNama1 = tangkap.getStringExtra("xNama1");
        yLokasi1 = tangkap.getStringExtra("xLokasi1");
        yUrlmap1 = tangkap.getStringExtra("xUrlmap1");
        yJam1 = tangkap.getStringExtra("xJam1");
        yNoHp1 = tangkap.getStringExtra("xNoHp1");

        tvNama1.setText(yNama1);
        tvLokasi1.setText(yLokasi1);
        tvUrlmap1.setText(yUrlmap1);
        tvJam1.setText(yJam1);
        tvNoHp1.setText(yNoHp1);
    }
}
