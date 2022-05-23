package com.sirvan.ltenfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sirvan.ltenfinal.Model.SmsModels;
import com.sirvan.ltenfinal.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SmsModels> smsList;
    private Context context;
    private SmsModels sms;

    public HistoryAdapter(Context context, List<SmsModels> smsList) {

        this.smsList = smsList;
        this.context = context;
    }

    public int getItemViewType(int position) {
        final SmsModels sms = smsList.get(position);
        return sms.getLayoutID();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sms_inbox, parent, false);
            return new contentViewHolderInbox(contentView);
        } else {
            View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_sms_outbox, parent, false);
            return new contentViewHolderOutbox(contentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SmsModels sms = smsList.get(position);
        if (holder instanceof contentViewHolderOutbox) {
            ((contentViewHolderOutbox) holder).outbox_body.setText(sms.getMsg());
            ((contentViewHolderOutbox) holder).outbox_time.setText(sms.getTime());
            ((contentViewHolderOutbox) holder).outbox_date.setText(sms.getDate());
            ((contentViewHolderOutbox) holder).outbox_img_delivered.setImageResource(sms.getImage());
            // ((contentViewHolderOutbox) holder).outbox_body.setTypeface(G.faceBold);
            // ((contentViewHolderOutbox) holder).outbox_time.setTypeface(G.face);
            // ((contentViewHolderOutbox) holder).outbox_date.setTypeface(G.face);

        } else if (holder instanceof contentViewHolderInbox) {

            //  final SmsModels item = smsList.get(position);
            ((contentViewHolderInbox) holder).inbox_body.setText(sms.getMsg());
            ((contentViewHolderInbox) holder).inbox_time.setText(sms.getTime());
            ((contentViewHolderInbox) holder).inbox_date.setText(sms.getDate());
            ((contentViewHolderInbox) holder).inbox_img_delivered.setImageResource(sms.getImage());

            //  ((contentViewHolderInbox) holder).inbox_body.setTypeface(G.faceBold);
            //  ((contentViewHolderInbox) holder).inbox_time.setTypeface(G.faceBold);
            //   ((contentViewHolderInbox) holder).inbox_date.setTypeface(G.faceBold);
        }
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }


    private class contentViewHolderOutbox extends RecyclerView.ViewHolder {

        private TextView outbox_body;
        private TextView outbox_time;
        private TextView outbox_date;
        private ImageView outbox_img_delivered;

        private contentViewHolderOutbox(View itemView) {
            super(itemView);
            outbox_body = itemView.findViewById(R.id.txtOutboxBody);
            outbox_time = itemView.findViewById(R.id.txtOutboxTime);
            outbox_date = itemView.findViewById(R.id.txtOutboxDate);
            outbox_img_delivered = itemView.findViewById(R.id.imgOutboxDelivered);
        }

    }

    private class contentViewHolderInbox extends RecyclerView.ViewHolder {
        private TextView inbox_body;
        private TextView inbox_time;
        private TextView inbox_date;
        private ImageView inbox_img_delivered;

        private contentViewHolderInbox(View itemView) {
            super(itemView);
            inbox_body = itemView.findViewById(R.id.txtInboxBody);
            inbox_date = itemView.findViewById(R.id.txtInboxDate);
            inbox_time = itemView.findViewById(R.id.txtInboxTime);
            inbox_img_delivered = itemView.findViewById(R.id.imgInboxDelivered);
        }

    }
}

