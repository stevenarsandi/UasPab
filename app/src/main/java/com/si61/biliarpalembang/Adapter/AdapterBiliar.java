package com.si61.biliarpalembang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.si61.biliarpalembang.Model.ModelBiliar;
import com.si61.biliarpalembang.R;

import java.util.List;

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


        }
    }
}
