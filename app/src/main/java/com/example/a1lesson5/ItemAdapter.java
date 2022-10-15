package com.example.a1lesson5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    // первоначальные данные, которые можно будет заменить
    private String[] dataSource;

    public ItemAdapter(String[] dataSource) {
        this.dataSource = dataSource;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_fragment_recycler_view,parent,false); // связь с айтимом
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        //по позиции берем текст
        holder.getTextView().setText(dataSource[position]);
    }


    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {


        //textViewItemRecyclerView
        private final TextView textView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewItemRecyclerView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
