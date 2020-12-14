package com.a1.nextlocation.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder>{

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
