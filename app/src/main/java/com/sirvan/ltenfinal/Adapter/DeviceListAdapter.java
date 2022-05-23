package com.sirvan.ltenfinal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirvan.ltenfinal.App.G;
import com.sirvan.ltenfinal.Model.DeviceListModel;
import com.sirvan.ltenfinal.R;

import java.util.List;

public class DeviceListAdapter  extends RecyclerView.Adapter<DeviceListAdapter.MyHolder> {

    Context context;
    List<DeviceListModel> data;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DeviceListModel item);
    }

    public DeviceListAdapter(Context context, List<DeviceListModel> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_device_list, parent, false);
        return new DeviceListAdapter.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.txtName.setText(data.get(position).getName());
        holder.txtName.setTypeface(G.faceBold);
        holder.txtNumber.setText(data.get(position).getNumber());
        holder.txtNumber.setTypeface(G.face);
        holder.bind(data.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtNumber;
        ImageView imgDelete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            imgDelete = itemView.findViewById(R.id.rvImageViewDelete);
            txtName = itemView.findViewById(R.id.rvTextViewName);
            txtNumber = itemView.findViewById(R.id.rvTextViewNumber);
        }

        public void bind(final DeviceListModel item, final DeviceListAdapter.OnItemClickListener listener) {
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
