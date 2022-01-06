package com.example.webratingsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Activity activity;
    ArrayList<Site> arraySite;

    public MainAdapter(Activity activity, ArrayList<Site> arraySite) {
        this.activity = activity;
        this.arraySite = arraySite;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.website_ratings, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Site model = arraySite.get(position);
        holder.tvName.setText(model.getUrl());
        holder.tvNumber.setText("note " + model.getRating() + "/5");
    }

    @Override
    public int getItemCount() {
        return arraySite.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_url);
            tvNumber = itemView.findViewById(R.id.tv_rating);
        }
    }
}
