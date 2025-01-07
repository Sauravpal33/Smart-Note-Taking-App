package com.example.notesapp;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView t1,t2;
    RelativeLayout cardView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        t1 = (TextView)itemView.findViewById(R.id.itemTitle);
        t2 = (TextView)itemView.findViewById(R.id.itemDesc);
        cardView = (RelativeLayout) itemView.findViewById(R.id.cardView);
    }
}
