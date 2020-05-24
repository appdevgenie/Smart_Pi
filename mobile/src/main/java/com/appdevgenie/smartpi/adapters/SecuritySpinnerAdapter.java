package com.appdevgenie.smartpi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appdevgenie.smartpi.R;

public class SecuritySpinnerAdapter extends BaseAdapter {

    private Context context;
    private int[] icons;
    private String[] names;

    public SecuritySpinnerAdapter(Context context, int[] icons, String[] names) {
        this.context = context;
        this.icons = icons;
        this.names = names;
    }



    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.spinner_item_icon_name, viewGroup, false);

        ImageView icon = view.findViewById(R.id.ivIcon);
        TextView name = view.findViewById(R.id.tvName);
        icon.setImageResource(icons[i]);
        name.setText(names[i]);
        return view;
    }
}
