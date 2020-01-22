package com.appdevgenie.smartpi.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appdevgenie.smartpi.R;
import com.appdevgenie.smartpi.models.Input;
import com.appdevgenie.smartpi.utils.FormatDate;

import java.util.ArrayList;

public class InputChannelAdapter extends RecyclerView.Adapter<InputChannelAdapter.InputChannelViewHolder> {

    private Context context;
    private ArrayList<Input> inputArrayList = new ArrayList<>();

    public InputChannelAdapter(Context context, ArrayList<Input> inputArrayList) {
        this.context = context;
        this.inputArrayList = inputArrayList;
    }

    @NonNull
    @Override
    public InputChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_input_channel, parent, false);
        return new InputChannelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputChannelViewHolder holder, int position) {

        Input input = inputArrayList.get(holder.getAdapterPosition());
        if (input.isEnabled()) {
            holder.tvEnabled.setText("Enabled");
            holder.ivEnabled.setBackgroundResource(R.drawable.led_green);
        } else {
            holder.tvEnabled.setText("Disabled");
            holder.ivEnabled.setBackgroundResource(R.drawable.led_off);
        }
        holder.tvChannelNumber.setText(TextUtils.concat("Channel ", input.getChannel()));
        holder.tvDescription.setText(input.getDescription());
        if (input.isStatus()) {
            holder.ivStatus.setBackgroundResource(R.drawable.led_green);
        } else {
            holder.ivStatus.setBackgroundResource(R.drawable.led_red);
        }

        if (input.getTimestampOpened() > 0) {
            holder.tvTimeOpened.setText(FormatDate.getFormattedDate(input.getTimestampOpened()));
        }

        if (input.getTimestampClosed() > 0) {
            holder.tvTimeClosed.setText(FormatDate.getFormattedDate(input.getTimestampClosed()));
        }

    }

    @Override
    public int getItemCount() {
        return inputArrayList.size();
    }

    public class InputChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvEnabled, tvChannelNumber, tvDescription, tvStatus, tvTimeOpened, tvTimeClosed;
        private ImageView ivEnabled, ivStatus;

        public InputChannelViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEnabled = itemView.findViewById(R.id.tvEnabled);
            tvChannelNumber = itemView.findViewById(R.id.tvChannelNumber);
            tvDescription = itemView.findViewById(R.id.tvInputDescription);
            tvStatus = itemView.findViewById(R.id.tvInputStatus);
            tvTimeOpened = itemView.findViewById(R.id.tvInputOpenTime);
            tvTimeClosed = itemView.findViewById(R.id.tvInputClosedTime);
            ivEnabled = itemView.findViewById(R.id.ivEnabled);
            ivStatus = itemView.findViewById(R.id.ivInputStatus);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
