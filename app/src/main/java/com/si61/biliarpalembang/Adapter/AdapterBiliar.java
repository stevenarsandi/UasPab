package com.si61.biliarpalembang.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.si61.biliarpalembang.API.APIRequestData;
import com.si61.biliarpalembang.API.RetroServer;
import com.si61.biliarpalembang.Activity.MainActivity;
import com.si61.biliarpalembang.Activity.DetailActivity;
import com.si61.biliarpalembang.Activity.UbahActivity;
import com.si61.biliarpalembang.Activity.TambahActivity;
import com.si61.biliarpalembang.Model.ModelBiliar;
import com.si61.biliarpalembang.Model.ModelResponse;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.biliarpalembang.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBiliar extends RecyclerView.Adapter<AdapterBiliar.VHBiliar>{
    private Context ctx;
    private List<ModelBiliar> listbiliar;

    public AdapterBiliar(Context ctx, List<ModelBiliar> listbiliar) {
        this.ctx = ctx;
        this.listbiliar = listbiliar;
    }

    @NonNull
    @Override
    public VHBiliar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varview = LayoutInflater.from(ctx).inflate(R.layout.list_item_biliar,parent,false);
        return new VHBiliar(varview);
    }

    @Override
    public void onBindViewHolder(@NonNull VHBiliar holder, int position) {
        ModelBiliar MB = listbiliar.get(position);
        holder.tvId.setText(MB.getId());
        holder.tvNama.setText(MB.getNama());
        holder.tvLokasi.setText(MB.getLokasi());
        holder.tvUrlmap.setText(MB.getUrlmap());
        holder.tvJam.setText(MB.getJam());
        holder.tvNoHp.setText(MB.getNohp());
    }

    @Override
    public int getItemCount() {
        return listbiliar.size();
    }

    public class VHBiliar extends RecyclerView.ViewHolder{
        TextView tvId, tvNama, tvLokasi,tvUrlmap, tvJam ,tvNoHp;
        public VHBiliar(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvLokasi = itemView.findViewById(R.id.tv_lokasi);
            tvUrlmap = itemView.findViewById(R.id.tv_urlmap);
            tvJam = itemView.findViewById(R.id.tv_jam_operasional);
            tvNoHp = itemView.findViewById(R.id.tv_nohp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent kirim = new Intent(ctx, DetailActivity.class);
                    kirim.putExtra("xId1", tvId.getText().toString());
                    kirim.putExtra("xNama1", tvNama.getText().toString());
                    kirim.putExtra("xLokasi1", tvLokasi.getText().toString());
                    kirim.putExtra("xUrlmap1", tvUrlmap.getText().toString());
                    kirim.putExtra("xJam", tvJam.getText().toString());
                    kirim.putExtra("xNoHp1", tvNoHp.getText().toString());
                    ctx.startActivity(kirim);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda memilih "+ tvNama.getText().toString() +", Operasi apa yang akan dilakukan?");

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent kirim = new Intent(ctx, UbahActivity.class);
                            kirim.putExtra("xId", tvId.getText().toString());
                            kirim.putExtra("xNama", tvNama.getText().toString());
                            kirim.putExtra("xLokasi", tvLokasi.getText().toString());
                            kirim.putExtra("xUrlmap", tvUrlmap.getText().toString());
                            kirim.putExtra("xJam", tvJam.getText().toString());
                            kirim.putExtra("xNoHp", tvNoHp.getText().toString());
                            ctx.startActivity(kirim);
                        }
                    });

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            prosesHapus(tvId.getText().toString());
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        void prosesHapus(String id){
            APIRequestData API = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = API.ardDelete(id);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveBiliar();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server! : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
