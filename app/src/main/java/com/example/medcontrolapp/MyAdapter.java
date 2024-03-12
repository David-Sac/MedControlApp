package com.example.medcontrolapp;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> mData;
    private int mSelectedPosition = RecyclerView.NO_POSITION;

    public MyAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String item = mData.get(position);
        holder.textView.setText(item);
// Determinar el estilo del texto y el tamaño según el estado de selección
        if (mSelectedPosition == position) {
            // Si está seleccionado, establecer el texto en negrita y el tamaño a 22
            holder.textView.setTypeface(null, Typeface.BOLD);
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.Color_Principal));
        } else {
            // Si no está seleccionado, establecer el texto en estilo normal y el tamaño a 18
            holder.textView.setTypeface(null, Typeface.NORMAL);
            holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.transparent));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousSelectedPosition = mSelectedPosition;
                // Establecer la nueva posición seleccionada
                mSelectedPosition = holder.getAdapterPosition();
                // Notificar cambios en los elementos seleccionados
                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(mSelectedPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_item);
        }
    }
}
