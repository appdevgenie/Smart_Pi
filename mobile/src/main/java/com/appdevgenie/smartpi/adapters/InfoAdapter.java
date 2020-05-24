package com.appdevgenie.smartpi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.smartpi.R;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private List<String> list;

    public InfoAdapter(List<String> horizontalList) {
        this.list = horizontalList;
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

        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public InfoViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.tvInfo);
        }
    }
}
