package com.appdevgenie.smartpi.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.smartpi.R;
import com.appdevgenie.smartpi.models.SecurityModel;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<SecurityModel> list;
    private Context context;

    public InfoAdapter(Context context, List<SecurityModel> horizontalList) {
        this.list = horizontalList;
        context = context;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_info_button,
                        parent,
                        false);

        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final InfoViewHolder holder, final int position) {

        SecurityModel securityModel = list.get(holder.getAdapterPosition());

        holder.tvName.setText(securityModel.getName());
        if(securityModel.isStatus()){
            holder.tvStatus.setText("Safe");
            holder.tvStatus.setTextColor(Color.GREEN);
            ImageViewCompat.setImageTintList(holder.ivInfo, ColorStateList.valueOf(Color.GREEN));
        }else{
            holder.tvStatus.setText("Trigged");
            holder.tvStatus.setTextColor(Color.RED);
            ImageViewCompat.setImageTintList(holder.ivInfo, ColorStateList.valueOf(Color.RED));
        }
        holder.ivInfo.setImageResource(securityModel.getIcon());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvStatus;
        ImageView ivInfo;

        public InfoViewHolder(View view) {
            super(view);

            tvName = view.findViewById(R.id.tvInfo);
            tvStatus = view.findViewById(R.id.tvInfoStatus);
            ivInfo = view.findViewById(R.id.ivInfo);
        }
    }
}
