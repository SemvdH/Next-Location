package com.a1.nextlocation.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
