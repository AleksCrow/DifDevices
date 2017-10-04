package com.alexvoronkov.difdevices.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexvoronkov.difdevices.ObjectClass.AndroidVersion;
import com.alexvoronkov.difdevices.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<AndroidVersion> android;
    private Listener listener;

    //интерфейс слушателя для обработки нажатий
    public static interface Listener {
        public void onClick(int position/*, ArrayList<AndroidVersion> android*/);
    }

    //слушатель для фрагментов и активностей
    public void setListener(Listener listener){
        this.listener = listener;
    }

    public DataAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, final int position) {
        final AndroidVersion androidVersion = android.get(position);
        viewHolder.tv_name.setText(androidVersion.getName());
        viewHolder.tv_version.setText(androidVersion.getVer());
        viewHolder.tv_api_level.setText(androidVersion.getApi());

        //реализация Listener для обработки нажатий и выбора нужного варианта
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = viewHolder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    listener.onClick(pos/*, android*/);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.tv_version);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }
}
